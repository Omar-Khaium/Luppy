package org.emptybit.luppy.Models;

public class CartModel {
    private String id;
    private String cardNumber;
    private String cardCVC;
    private String cardExpireDate;
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

    public CartModel(String id, String cardNumber, String cardCVC, String cardExpireDate, ProductModel product, int size, int quantity) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardCVC = cardCVC;
        this.cardExpireDate = cardExpireDate;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCVC() {
        return cardCVC;
    }

    public void setCardCVC(String cardCVC) {
        this.cardCVC = cardCVC;
    }

    public String getCardExpireDate() {
        return cardExpireDate;
    }

    public void setCardExpireDate(String cardExpireDate) {
        this.cardExpireDate = cardExpireDate;
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
