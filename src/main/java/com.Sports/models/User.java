package com.Sports.models;

public class User{
    private String role;
    private String password;

    public User(String role, String password) {
        this.role = role;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setUser_name(String user_name) {
        this.role = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean Login(){ return true;
    }

    public boolean Logout(){return true;}
}