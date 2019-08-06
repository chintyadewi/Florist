package com.example.florist.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Basket {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("id_user")
    @Expose
    private String idUser;

    @SerializedName("id_product")
    @Expose
    private String idProduct;

    @SerializedName("quantity")
    @Expose
    private Integer quantity;

    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;

    public Basket(String id, String idUser, String idProduct, Integer quantity, Integer totalPrice) {
        this.id = id;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
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