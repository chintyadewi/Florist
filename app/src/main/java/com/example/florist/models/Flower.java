package com.example.florist.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flower {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private Integer price;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("img_url")
    @Expose
    private String imageUrl;

    public Flower(Integer id, String name, Integer price, String category, String imageUrl){
        this.id=id;
        this.name=name;
        this.price=price;
        this.category=category;
        this.imageUrl=imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
