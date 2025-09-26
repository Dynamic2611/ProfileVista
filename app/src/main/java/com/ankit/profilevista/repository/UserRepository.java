package com.ankit.profilevista.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ankit.profilevista.data.AppDatabase;
import com.ankit.profilevista.data.UserDao;
import com.ankit.profilevista.model.User;
import com.ankit.profilevista.network.ApiService;
import com.ankit.profilevista.network.RetrofitClient;
import com.ankit.profilevista.network.UsersResponse;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static final String TAG = "UserRepository";
    private final UserDao userDao;
    private final ApiService api;
    private final ExecutorService executor;
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> error = new MutableLiveData<>(null);

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
        api = RetrofitClient.getRetrofit().create(ApiService.class);
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    public LiveData<User> getUserById(int id) {
        return userDao.getUserById(id);
    }

    public LiveData<Boolean> getLoading() {
        return isLoading;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void fetchUsersFromNetwork() {
        isLoading.postValue(true);
        error.postValue(null);
        api.getUsers().enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                isLoading.postValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    List<User> list = response.body().users;
                    executor.execute(() -> {
                        try {
                            userDao.insertUsers(list);
                        } catch (Exception e) {
                            Log.e(TAG, "DB insert error: " + e.getMessage(), e);
                        }
                    });
                } else {
                    error.postValue("Server returned error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                isLoading.postValue(false);
                error.postValue("Network error: " + t.getMessage());
            }
        });
    }
}
