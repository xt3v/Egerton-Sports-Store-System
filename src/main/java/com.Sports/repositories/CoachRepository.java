package com.Sports.repositories;

import com.Sports.models.Coach;
import com.Sports.services.DatabaseHandler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CoachRepository implements Repository<String, Coach> {

    @Override
    public Optional<Coach> getById(String id) {
       Coach coach = null;
        try{
            String sql = "Select * FROM coaches WHERE employeeId  = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                coach = new Coach(rs.getString("name"),rs.getString("employeeId"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "  coachRepo");
        }
        Optional<Coach> optionalCoach = Optional.ofNullable(coach);
        return optionalCoach;
    }

    @Override
    public ArrayList<Coach> getAll() {
        ResultSet rs = db.executeQuery("SELECT * FROM coaches");
        return createList(rs);
    }

    private ArrayList<Coach> createList(ResultSet rs) {
        ArrayList<Coach> list = new ArrayList<>();
        try{
            while (rs.next()){
                Coach coach = new Coach(rs.getString("name"),rs.getString("employeeId"));
                list.add(coach);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" create coach");
        }
        return list;
    }

    @Override
    public ArrayList<Coach> queryList(PreparedStatement stmt) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            return createList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" coaches query list");
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
    public boolean deleteById(String identifier) {
        try {
            String sql = "DELETE FROM coaches WHERE employeeId = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1,identifier);
            int rs = stmt.executeUpdate();
            if(rs == 1)return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean save(Coach entry) {
        Coach coach = entry;

        Optional<Coach> optionalBorrowedItem = getById(coach.getEmployeeId());

        if(optionalBorrowedItem.isPresent()){
            try{
                String sql = "UPDATE coaches SET name = ? , employeeId = ? WHERE employeeId = ?";
                PreparedStatement stmt = db.getConn().prepareStatement(sql);
                stmt.setString(1,coach.getName());
                stmt.setString(3,coach.getEmployeeId());
                stmt.setString(2,coach.getEmployeeId());
                int rs = stmt.executeUpdate();
                if(rs == 1)return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        try{
            String sql = "INSERT INTO coaches(name,employeeId) VALUES(?,?)";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1,coach.getName());
            stmt.setString(2,coach.getEmployeeId());
            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


}
