package com.example.florist.models;

public class Basket {
    private String name, imageUrl;
    private Integer price, quantity, totalPrice;

    public Basket(String name, Integer price, Integer quantity, Integer totalPrice, String imageUrl){
        this.name=name;
        this.price=price;
        this.imageUrl=imageUrl;
        this.quantity=quantity;
        this.totalPrice=totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
