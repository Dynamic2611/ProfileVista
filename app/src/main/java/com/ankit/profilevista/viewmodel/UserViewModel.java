package com.ankit.profilevista.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ankit.profilevista.model.User;
import com.ankit.profilevista.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository repo;
    private final LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repo = new UserRepository(application);
        allUsers = repo.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<User> getUserById(int id) {
        return repo.getUserById(id);
    }

    public LiveData<Boolean> getLoading() { return repo.getLoading(); }
    public LiveData<String> getError() { return repo.getError(); }

    public void refreshFromNetwork() {
        repo.fetchUsersFromNetwork();
    }
}
