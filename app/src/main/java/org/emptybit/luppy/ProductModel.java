package org.emptybit.luppy;

public class ProductModel {
    private int id;
    private int path;
    private int price;

    public ProductModel() {
    }

    public ProductModel(int id, int path, int price) {
        this.id = id;
        this.path = path;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
