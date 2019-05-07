package com.Sports.repositories;


import com.Sports.models.Sport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class SportRepository implements Repository<Integer, Sport> {

    @Override
    public Optional<Sport> getById(Integer id) {
        Sport sport = null;
        try{
            String sql = "Select * FROM sports WHERE sportId  = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                sport = new Sport(rs.getString("name"),rs.getInt("sportId"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "sport repo");
        }
        Optional<Sport> optionalSport = Optional.ofNullable(sport);
        return optionalSport;
    }

    @Override
    public ArrayList<Sport> getAll() {
        ResultSet rs = db.executeQuery("SELECT * FROM sports");
        return createList(rs);
    }

    private ArrayList<Sport> createList(ResultSet rs) {
        ArrayList<Sport> list = new ArrayList<>();
        try{
            while (rs.next()){
               Sport sport = new Sport(rs.getString("name"),rs.getInt("sportId"));
                list.add(sport);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" create sport list");
        }
        return list;
    }

    @Override
    public ArrayList<Sport> queryList(PreparedStatement stmt) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            return createList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" sport query list");
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
            String sql = "DELETE FROM sports WHERE sportId = ?";
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
    public boolean save(Sport entry) {
       Sport sport = entry;

        Optional<Sport> optionalSport = getById(sport.getSportId());

        if(optionalSport.isPresent()){
            try{
                String sql = "UPDATE sports SET name = ? WHERE sportId = ?";
                PreparedStatement stmt = db.getConn().prepareStatement(sql);
                stmt.setString(1,sport.getName());
                stmt.setInt(2,sport.getSportId());

                int rs = stmt.executeUpdate();
                if(rs == 1)return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        try{
            String sql = "INSERT INTO games(name) VALUES(?)";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1,sport.getName());

            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }


}
