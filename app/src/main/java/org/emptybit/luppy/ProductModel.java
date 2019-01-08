package org.emptybit.luppy;

public class ProductModel {
    private int path;
    private int price;

    public ProductModel() {
    }

    public ProductModel(int path, int price) {
        this.path = path;
        this.price = price;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
