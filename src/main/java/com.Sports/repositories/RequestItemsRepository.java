package com.Sports.repositories;

import com.Sports.models.RequestedItems;
import com.Sports.services.DatabaseHandler;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RequestItemsRepository  {
    DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

    public boolean save(RequestedItems requestedItems){
        try {

            String sql = "INSERT INTO requestedItems (gameId,items) VALUES (?,?)";
            PreparedStatement stmt = databaseHandler.getConn().prepareStatement(sql);
            stmt.setInt(1,requestedItems.getGamedId());
            stmt.setString(2,requestedItems.getItems());
            int rs = stmt.executeUpdate();
            if(rs == 1)return true;
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }


    public ArrayList<RequestedItems> getAll() {
        try{
            String sql = "SELECT * FROM requestedItems";
            PreparedStatement stmt = databaseHandler.getConn().prepareStatement(sql);
            return createList(stmt.executeQuery());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    private ArrayList<RequestedItems> createList(ResultSet rs) throws Exception {
        ArrayList arrayList = new ArrayList<RequestedItems>();
        while (rs.next()){
            RequestedItems requestedItems = new RequestedItems(rs.getInt("id"),rs.getInt("gameId"),rs.getString("items"));
            arrayList.add(requestedItems);

        }
        return arrayList;
    }
}
