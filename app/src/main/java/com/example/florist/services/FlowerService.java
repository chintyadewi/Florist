package com.example.florist.services;

import com.example.florist.models.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FlowerService {
    @GET("/florist-service/api.php/products")
    Call<List<Flower>> getFlower();

    @GET("/florist-service/flower.php/{id}")
    Call<List<Flower>> getFlowerDetail(@Path("id") int id);

    @POST("/florist-service/api.php/products")
    Call<Flower> addFlower(@Body Flower flower);
}
