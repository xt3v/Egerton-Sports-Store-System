package com.Sports.models;

import com.Sports.services.DatabaseService;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestedItems {
    private int id;
    private int gamedId;
    private String items;

    private DatabaseService db = DatabaseService.getInstance();

    public RequestedItems(int id, int gamedId, String items) {
        this.id = id;
        this.gamedId = gamedId;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGamedId() {
        return gamedId;
    }

    public void setGamedId(int gamedId) {
        this.gamedId = gamedId;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",this.id);
        jsonObject.put("gameId",this.gamedId);
        jsonObject.put("game",new JSONObject(db.getGameRepository().getById(this.gamedId).get().toString()));
        JSONArray jsonArray = new JSONArray(this.items);
        JSONArray f = new JSONArray();
        for(int i =0;i < jsonArray.length();i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            System.out.println(obj.toString());
            int id = obj.getInt("itemId");
            Item item = db.getItemRepository().getById(id).get();
            obj.put("name",item.getName());
            f.put(obj);
        }
        jsonObject.put("items",f);
        return jsonObject.toString();
    }
}
