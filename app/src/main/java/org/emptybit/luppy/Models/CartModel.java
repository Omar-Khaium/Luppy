package org.emptybit.luppy.Models;

public class CartModel {
    private ProductModel product;
    private int quantity;

    public CartModel() {
    }

    public CartModel(ProductModel product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
