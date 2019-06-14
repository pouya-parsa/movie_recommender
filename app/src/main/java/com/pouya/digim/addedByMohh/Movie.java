package com.pouya.digim.addedByMohh;

public class Movie {
    private String name;
    private int Rating;

    public Movie(String name, int rating) {
        this.name = name;
        Rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }
}
