package com.example.florist.services;

import com.example.florist.models.Basket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BasketService {
    @GET("/florist-service/basket.php/{id}")
    Call<List<Basket>> getBasketItem(@Path("id") int id);

    @POST("/florist-service/api.php/basket")
    Call<Basket> addBasketItem(@Body Basket basketItem);
}
