package com.Sports.repositories;

import com.Sports.models.SessionItem;
import com.Sports.services.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SessionRepository {

    private DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

    public Optional<SessionItem> getByValue(String value){
        Optional<SessionItem> optionalSessionItem  = null;
        SessionItem sessionItem = null;
        try{
            String sql = "SELECT * FROM sessions WHERE value = ?";
            PreparedStatement ps = databaseHandler.getConn().prepareStatement(sql);
            ps.setString(1,value);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                sessionItem = new SessionItem(rs.getInt("id"),rs.getString("value"),
                        rs.getString("role"),rs.getString("user_id"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        optionalSessionItem = Optional.ofNullable(sessionItem);
        return optionalSessionItem;
    }


    public boolean save(SessionItem entry){
        SessionItem sessionItem = entry;

        Optional<SessionItem> optionalSessionItem = getById(sessionItem.getId());

        if(optionalSessionItem.isPresent()){
            try{
                String sql = "UPDATE sessions SET value = ?  WHERE user_id = ?";
                PreparedStatement stmt = databaseHandler.getConn().prepareStatement(sql);
                stmt.setString(1,sessionItem.getValue());
                stmt.setString(2,sessionItem.getUserId());
                int rs = stmt.executeUpdate();
                if(rs == 1)return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        try{
            String sql = "INSERT INTO sessions(value,role,user_id) VALUES(?,?,?)";
            PreparedStatement stmt = databaseHandler.getConn().prepareStatement(sql);
            stmt.setString(1,sessionItem.getValue());
            stmt.setString(2,sessionItem.getRole());
            stmt.setString(3,sessionItem.getUserId());
            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Optional<SessionItem> getById(int id) {
       SessionItem sessionItem = null;
        try{
            String sql = "Select * FROM sessions WHERE id  = ?";
            PreparedStatement stmt = databaseHandler.getConn().prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                sessionItem = new SessionItem(rs.getInt("id"),rs.getString("value"),
                        rs.getString("role"),rs.getString("user_id"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "  seesion item by id");
        }
        Optional<SessionItem> optionalSessionItem = Optional.ofNullable(sessionItem);
        return optionalSessionItem;
    }

    public boolean deleteByValue(String value){
        try{
            String sql = "DELETE FROM sessions WHERE value  = ?";
            PreparedStatement stmt = databaseHandler.getConn().prepareStatement(sql);
            stmt.setString(1,value);
            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage() +" delete sessions by value");
        }
        return false;
    }

    public boolean deleteByUser(String userId) {
        try{
            String sql = "DELETE FROM sessions WHERE user_id = ?";
            PreparedStatement stmt = databaseHandler.getConn().prepareStatement(sql);
            stmt.setString(1,userId);
            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage() +" delete sessions by user");
        }
        return false;
    }
}
