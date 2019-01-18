package org.emptybit.luppy;

public class ProductModel {
    private String id;
    private String name;
    private String category;
    private String sub_category;
    private int price;
    private String photo;

    public ProductModel() {
    }

    public ProductModel(String id, String name, String category, String sub_category, int price, String photo) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.sub_category = sub_category;
        this.price = price;
        this.photo = photo;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
