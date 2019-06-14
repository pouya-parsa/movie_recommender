package com.pouya.digim;

import java.io.Serializable;

public class MovieModel  implements Serializable {
    private String name;
    private float rate;
    private String image;
    private Boolean ratedBy;


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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
