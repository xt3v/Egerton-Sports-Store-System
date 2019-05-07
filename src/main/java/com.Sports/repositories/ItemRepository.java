package com.Sports.repositories;

import com.Sports.models.Game;
import com.Sports.models.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class ItemRepository implements Repository<Integer, Item> {


    @Override
    public Optional<Item> getById(Integer id) {
        Item item = null;
        try{
            String sql = "Select * FROM items WHERE itemId  = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                item = new Item(rs.getInt("sportId"),rs.getInt("quantity"),rs.getInt("itemId"),rs.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "  item repo");
        }
        Optional<Item> optionalItem = Optional.ofNullable(item);
        return optionalItem;
    }

    @Override
    public ArrayList<Item> getAll() {
        ResultSet rs = db.executeQuery("SELECT * FROM items");
        return createList(rs);
    }

    private ArrayList<Item> createList(ResultSet rs) {
        ArrayList<Item> list = new ArrayList<>();
        try{
            while (rs.next()){
                Item item = new Item(rs.getInt("sportId"),rs.getInt("quantity"),rs.getInt("itemId"),rs.getString("name"));
                list.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" create item list");
        }
        return list;
    }

    @Override
    public ArrayList<Item> queryList(PreparedStatement stmt) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            return createList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" item query list");
        }
        return null;
    }

    @Override
    public ResultSet querySet(PreparedStatement statement) {

        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer identifier) {
        try {
            String sql = "DELETE FROM items WHERE itemId = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1,identifier);
            int rs = stmt.executeUpdate();
            if(rs == 1)return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean save(Item entry) {
        Item item = entry;

        Optional<Item> optionalItem = getById(item.getItemId());

        if(optionalItem.isPresent()){
            try{
                String sql = "UPDATE items SET sportId = ? , name = ? , quantity = ?   WHERE itemId = ?";
                PreparedStatement stmt = db.getConn().prepareStatement(sql);
                stmt.setInt(1,item.getSportId());
                stmt.setString(2,item.getName());
                stmt.setInt(3,item.getQuantity());
                stmt.setInt(4,item.getItemId());
                int rs = stmt.executeUpdate();
                if(rs == 1)return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        try{
            String sql = "INSERT INTO items(name,sportId,quantity) VALUES(?,?,?)";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1,item.getName());
            stmt.setInt(2,item.getSportId());
            stmt.setInt(3,item.getQuantity());

            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }


}
