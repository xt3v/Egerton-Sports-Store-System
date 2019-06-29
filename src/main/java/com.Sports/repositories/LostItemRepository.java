package com.Sports.repositories;

import com.Sports.models.BorrowedItem;
import com.Sports.models.LostItem;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class LostItemRepository implements Repository<Integer, LostItem> {

    @Override
    public Optional<LostItem> getById(Integer id) {
        LostItem lostItem = null;
        try{
            String sql = "Select * FROM lostitems WHERE lostItemId = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                lostItem = new LostItem(rs.getInt("itemId"), rs.getDate("borrowedDate").toLocalDate(),rs.getInt("lostItemId"),rs.getInt("quantity"),rs.getString("borrowerId"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "  borrowedItemRepo");
        }
        Optional<LostItem> optionalLostItem = Optional.ofNullable(lostItem);
        return optionalLostItem;
    }

    @Override
    public ArrayList<LostItem> getAll() {
        ResultSet rs = db.executeQuery("SELECT * FROM lostitems");
        return createList(rs);
    }

    private ArrayList<LostItem> createList(ResultSet rs) {
        ArrayList<LostItem> list = new ArrayList<>();
        try{
            while (rs.next()){
                LostItem lostItem = new LostItem(rs.getInt("itemId"), rs.getDate("borrowedDate").toLocalDate(),rs.getInt("lostItemId"),rs.getInt("quantity"),rs.getString("borrowerId"));
                list.add(lostItem);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" create  lost item list");
        }
        return list;
    }

    @Override
    public ArrayList<LostItem> queryList(PreparedStatement stmt) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            return createList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"lost tems query list");
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
            String sql = "DELETE FROM lostitems WHERE lostItemId = ?";
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
    public boolean save(LostItem entry) {
        LostItem lostItem = entry;

        Optional<LostItem> optionalLostItem = getById(lostItem.getLostItemId());

        if(optionalLostItem.isPresent()){
            try{
                String sql = "UPDATE lostitems SET itemId = ? , borrowedDate = ? ,quantity =  ? , borrowerId = ? WHERE lostItemId = ?";
                PreparedStatement stmt = db.getConn().prepareStatement(sql);
                stmt.setInt(1,lostItem.getItemId());
                stmt.setInt(5,lostItem.getLostItemId());
                stmt.setDate(2, Date.valueOf(lostItem.getDate()));
                stmt.setInt(3,lostItem.getQuantity());
                stmt.setString(4,lostItem.getBorrowerId());
                int rs = stmt.executeUpdate();
                if(rs == 1)return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        try{
            String sql = "INSERT INTO lostitems(borrowedDate,itemId,quantity,borrowerId) VALUES(?,?,?,?)";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(2,lostItem.getItemId());
            stmt.setDate(1, Date.valueOf(entry.getDate()));
            stmt.setInt(3,lostItem.getQuantity());
            stmt.setString(4,lostItem.getBorrowerId());
            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public ArrayList<LostItem> getByBorrowerId(String id) {
        try{
            String sql = "SELECT * FROM lostitems WHERE borrowerId = ?";
            PreparedStatement statement = db.getConn().prepareStatement(sql);
            statement.setString(1,id);
            return createList(statement.executeQuery());
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "get bowworeditems by student reg");
        }
        return new ArrayList<>();
    }

    public ArrayList<LostItem> getByItemId(int id){
        try{
            String sql = "SELECT * FROM lostitems WHERE itemId = ?";
            PreparedStatement statement = db.getConn().prepareStatement(sql);
            statement.setInt(1,id);
            return createList(statement.executeQuery());
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "get lostitems by item id");
        }
        return new ArrayList<>();
    }
}
