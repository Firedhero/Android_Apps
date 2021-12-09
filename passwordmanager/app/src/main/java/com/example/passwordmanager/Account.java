package com.example.passwordmanager;

public class Account {

    private String password;
    private String location;
    //Constructor
    public Account(){
        password = "";
        location = "";
    }
    public Account(String l, String p){
        location = l;
        password = p;
    }
    //Getters
    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }
    //Setters
    public void setPassword(String password) {
        this.password = password;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}