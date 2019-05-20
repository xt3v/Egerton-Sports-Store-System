package com.Sports.models;

import com.Sports.services.DatabaseService;
import org.json.JSONObject;

public class Item {
    private static DatabaseService db = DatabaseService.getInstance();
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

    public Item(JSONObject itemJSON){
        this.sportId = itemJSON.getInt("sportId");
        this.quantity = itemJSON.getInt("quantity");
        this.itemId = itemJSON.getInt("itemId");
        this.name = itemJSON.getString("name");
    }

    public static boolean checkIfExists(Item item) {
       return db.getItemRepository().getByNameInSport(item.getName(),item.getSportId()).isPresent();
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