package org.emptybit.luppy;

public class ProductModel {
    private String photo;
    private int price;

    public ProductModel() {
    }

    public ProductModel(String photo, int price) {
        this.photo = photo;
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
