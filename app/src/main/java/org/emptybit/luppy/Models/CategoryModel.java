package org.emptybit.luppy.Models;

import java.util.ArrayList;

public class CategoryModel {
    private String name;
    private ArrayList<ProductModel> productModels;

    public CategoryModel() {
    }

    public CategoryModel(String name, ArrayList<ProductModel> productModels) {
        this.name = name;
        this.productModels = productModels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(ArrayList<ProductModel> productModels) {
        this.productModels = productModels;
    }

    @Override
    public String toString() {
        return name;
    }
}
