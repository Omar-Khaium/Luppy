package org.emptybit.luppy.Models;

public class CartModel {
    private String id;
    //    private String cardNumber;
//    private String cardCVC;
//    private String cardExpireDate;
    private ProductModel product;
    private int size;
    private int quantity;

    public CartModel() {
    }

    public CartModel(ProductModel product, int size, int quantity) {
        this.product = product;
        this.size = size;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
