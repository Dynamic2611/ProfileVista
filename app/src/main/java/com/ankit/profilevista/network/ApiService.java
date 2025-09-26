package com.ankit.profilevista.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    Call<UsersResponse> getUsers();
}
