package com.pouya.digim;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    int charge;
    String password;
    String username;

    public User() {

    }

    public User(String username, String password) {
        this.name = "نام شما";
        this.charge = 10000;
        this.password = password;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
