package org.emptybit.luppy.Models;

import java.util.ArrayList;

public class OrderModel {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String comment;
    private String cardNumber;
    private String cardExpireDate;
    private String cardCVC;
    private String orderDate;
    private ArrayList<CartModel> cartModels;

    public OrderModel(String id, String name, String email, String phone, String address, String comment,
                      String cardNumber, String cardExpireDate, String cardCVC, String orderDate,
                      ArrayList<CartModel> cartModels) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.comment = comment;
        this.cardNumber = cardNumber;
        this.cardExpireDate = cardExpireDate;
        this.cardCVC = cardCVC;
        this.orderDate = orderDate;
        this.cartModels = cartModels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpireDate() {
        return cardExpireDate;
    }

    public void setCardExpireDate(String cardExpireDate) {
        this.cardExpireDate = cardExpireDate;
    }

    public String getCardCVC() {
        return cardCVC;
    }

    public void setCardCVC(String cardCVC) {
        this.cardCVC = cardCVC;
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
}
