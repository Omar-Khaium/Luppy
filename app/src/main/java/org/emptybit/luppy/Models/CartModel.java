package org.emptybit.luppy.Models;

public class CartModel {
    private ProductModel product;
    private int size;

    public CartModel() {
    }

    public CartModel(ProductModel product, int size) {
        this.product = product;
        this.size = size;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public int getsize() {
        return size;
    }

    public void setsize(int size) {
        this.size = size;
    }
}
