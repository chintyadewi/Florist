package com.example.florist.services;

import com.example.florist.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LoginService {
    @GET("/florist-service/api.php/users")
    Call<List<User>> getUsers();
}

