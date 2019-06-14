package com.pouya.digim;

public class MovieModel {
    private String name;
    private int rate;
    private String image;


    public MovieModel() {

    }

    public MovieModel(String name, int rate, String image) {
        this.name = name;
        this.rate = rate;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
