package org.emptybit.luppy.Models;

import java.util.ArrayList;

public class OrderModel {
    private String id;
    private String email;
    private String comment;
    private String orderDate;
    private ArrayList<CartModel> cartModels;

    public OrderModel() {
    }

    public OrderModel(String id, String email, String comment, String orderDate, ArrayList<CartModel> cartModels) {
        this.id = id;
        this.email = email;
        this.comment = comment;
        this.orderDate = orderDate;
        this.cartModels = cartModels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<CartModel> getCartModels() {
        return cartModels;
    }

    public void setCartModels(ArrayList<CartModel> cartModels) {
        this.cartModels = cartModels;
    }

    //    private String name;
    //    private String phone;
//    private String address;
    //    private String cardNumber;
//    private String cardExpireDate;
//    private String cardCVC;

}
