package com.Sports.repositories;

import com.Sports.models.GameRequest;
import com.Sports.services.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class GameRequestRepository {

    private DatabaseHandler db = DatabaseHandler.getInstance();

    public boolean save(GameRequest entry){
        GameRequest game = entry;
        Optional<GameRequest> optionalGame = getById(game.getId());
        if(optionalGame.isPresent()){
            try{
                String sql = "UPDATE gamerequests SET dateTime = ? , approved = ? , requesterId = ?, teamId = ? , duration = ?,declined = ? WHERE id = ?";
                PreparedStatement stmt = db.getConn().prepareStatement(sql);
                stmt.setTimestamp(1,Timestamp.valueOf(game.getDateTime()));
                stmt.setBoolean(2,game.isApproved());
                stmt.setString(3,game.getRequesterId());
                stmt.setInt(4,game.getTeamId());
                stmt.setFloat(5,game.getDuration());
                stmt.setBoolean(6,game.isDeclined());
                stmt.setInt(7,game.getId());
                int rs = stmt.executeUpdate();
                if(rs == 1)return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        try{
            String sql = "INSERT INTO gamerequests(dateTime,approved,requesterId,teamId,duration) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setTimestamp(1,Timestamp.valueOf(game.getDateTime()));
            stmt.setBoolean(2,game.isApproved());
            stmt.setString(3,game.getRequesterId());
            stmt.setInt(4,game.getTeamId());
            stmt.setFloat(5,game.getDuration());
            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

    public Optional<GameRequest> getById(int id) {
        GameRequest game = null;
        try {
            String sql = "SELECT * FROM gamerequests WHERE id = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                LocalDateTime dateTime = rs.getTimestamp("dateTime").toLocalDateTime();
                game = new GameRequest(dateTime,rs.getInt("duration"),rs.getInt("teamId"),rs.getInt("id"),rs.getBoolean("approved"),rs.getString("requesterId"));
                game.setDeclined(rs.getBoolean("declined"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(game);
    }

    public ArrayList<GameRequest> getAll() {
        ResultSet rs = db.executeQuery("SELECT * FROM gamerequests");
        return createList(rs);
    }

    private ArrayList<GameRequest> createList(ResultSet rs) {
        ArrayList<GameRequest> list = new ArrayList<>();
        try{
            while (rs.next()){
                LocalDateTime dateTime = rs.getTimestamp("dateTime").toLocalDateTime();
                GameRequest game = new GameRequest(dateTime,rs.getInt("duration"),rs.getInt("teamId"),rs.getInt("id"),rs.getBoolean("approved"),rs.getString("requesterId"));
                game.setDeclined(rs.getBoolean("declined"));
                list.add(game);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" create game request list");
        }
        return list;
    }
}
