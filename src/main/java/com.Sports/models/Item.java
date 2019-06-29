package com.Sports.models;

import com.Sports.services.DatabaseService;
import org.json.JSONObject;

import java.sql.PreparedStatement;

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

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",this.name);
        jsonObject.put("itemId",this.itemId);
        jsonObject.put("quantity",this.quantity);
        jsonObject.put("sportId",this.sportId);
        jsonObject.put("lostAmount",this.getLostAmount());
        jsonObject.put("borrowedAmount",this.getBorrowedAmount());
        return jsonObject.toString();
    }

    public int getLostAmount() {
        return db.getLostItemRepository().getByItemId(this.itemId).size();
    }

    public int getBorrowedAmount(){
       return db.getBorrowedItemRepository().getByItemId(this.itemId).size();
    }
}