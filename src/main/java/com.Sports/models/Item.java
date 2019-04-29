package com.Sports.models;

public class Item {
    private int sportId;
    private int quantity;
    private int itemId;
    private String name;

    public Item(int sportId, int quantity, int itemId, String name) {
        this.sportId = sportId;
        this.quantity = quantity;
        this.itemId = itemId;
        this.name = name;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}