package com.Sports.models;

public class SessionItem {

    private String value;
    private String role;
    private String userId;
    private int id;

    public SessionItem(int id,String value, String role, String userId) {
        this.value = value;
        this.role = role;
        this.userId = userId;
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
