package com.pouya.digim;

public class Product {
    private String title;
    private int price;
    private String image;


    public Product() {

    }

    public Product(String title, int price, String image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
