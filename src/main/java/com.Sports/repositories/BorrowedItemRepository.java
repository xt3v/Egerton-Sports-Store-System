package com.Sports.repositories;

import com.Sports.models.BorrowedItem;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class BorrowedItemRepository implements Repository<Integer,BorrowedItem> {

    @Override
    public Optional<BorrowedItem> getById(Integer id) {
        BorrowedItem borrowedItem = null;
        try{
            String sql = "Select * FROM borroweditems WHERE borrowedItemId = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                borrowedItem = new BorrowedItem(rs.getInt("borrowedItemId"), rs.getDate("borrowedDate").toLocalDate(),rs.getInt("itemId"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "  borrowedItemRepo");
        }
        Optional<BorrowedItem> optionalBorrowedItem = Optional.ofNullable(borrowedItem);
        return optionalBorrowedItem;
    }

    @Override
    public ArrayList<BorrowedItem> getAll() {
        ResultSet rs = db.executeQuery("SELECT * FROM borroweditems");
        return createList(rs);
    }

    private ArrayList<BorrowedItem> createList(ResultSet rs) {
        ArrayList<BorrowedItem> list = new ArrayList<>();
        try{
            while (rs.next()){
                BorrowedItem borrowedItem = new BorrowedItem(rs.getInt("borrowedItemId"), rs.getDate("borrowedDate").toLocalDate(),rs.getInt("itemId"));
               list.add(borrowedItem);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" create borroweditemlist");
        }
        return list;
    }

    @Override
    public ArrayList<BorrowedItem> queryList(PreparedStatement stmt) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            return createList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" borroweditems query list");
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
            String sql = "DELETE FROM borroweditems WHERE borrowedItemId = ?";
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
    public boolean save(BorrowedItem entry) {
        BorrowedItem borrowedItem = entry;

        Optional<BorrowedItem> optionalBorrowedItem = getById(borrowedItem.getBorrowedItemId());

        if(optionalBorrowedItem.isPresent()){
            try{
                String sql = "UPDATE borroweditems SET itemId = ? , borrowedDate = ? WHERE borrowedItemId = ?";
                PreparedStatement stmt = db.getConn().prepareStatement(sql);
                stmt.setInt(1,borrowedItem.getItemId());
                stmt.setInt(3,borrowedItem.getBorrowedItemId());
                stmt.setDate(2,Date.valueOf(borrowedItem.getDate()));
                int rs = stmt.executeUpdate();
                if(rs == 1)return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        try{
            String sql = "INSERT INTO borroweditems(borrowedDate,itemId) VALUES(?,?)";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(2,borrowedItem.getItemId());
            stmt.setDate(1, Date.valueOf(entry.getDate()));

            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }



}
