package com.pouya.digim;

public class Product {
    private String title;
    private int price;
    private String image;
    private String key;
    private String category;
    private String short_dis;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShort_dis() {
        return short_dis;
    }

    public void setShort_dis(String short_disc) {
        this.short_dis = short_disc;
    }
}
