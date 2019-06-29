package com.Sports.repositories;

import com.Sports.models.Game;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class GameRepository implements Repository<Integer, Game> {


    @Override
    public Optional<Game> getById(Integer id) {
        Game game = null;
        try{
            String sql = "Select * FROM games WHERE gameId  = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
               LocalDateTime time = rs.getTimestamp("dateTime").toLocalDateTime();
               game = new Game(rs.getInt("sportId"),rs.getInt("teamId"),time,rs.getInt("fieldId"),rs.getInt("gameId"),rs.getFloat("duration"));
               game.setPlayed(rs.getBoolean("played"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "  game repo");
        }
        Optional<Game> optionalGame = Optional.ofNullable(game);
        return optionalGame;
    }

    @Override
    public ArrayList<Game> getAll() {
        ResultSet rs = db.executeQuery("SELECT * FROM games");
        return createList(rs);
    }

    private ArrayList<Game> createList(ResultSet rs) {
        ArrayList<Game> list = new ArrayList<>();
        try{
            while (rs.next()){
                LocalDateTime time = rs.getTimestamp("dateTime").toLocalDateTime();
               Game game = game = new Game(rs.getInt("sportId"),rs.getInt("teamId"),time,rs.getInt("fieldId"),rs.getInt("gameId"),rs.getFloat("duration"));
                game.setPlayed(rs.getBoolean("played"));
                list.add(game);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" create gale list");
        }
        return list;
    }

    @Override
    public ArrayList<Game> queryList(PreparedStatement stmt) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            return createList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"  game query list");
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
            String sql = "DELETE FROM games WHERE gameId = ?";
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
    public boolean save(Game entry) {
        Game game = entry;

        Optional<Game> optionalGame = getById(game.getFieldId());

        if(optionalGame.isPresent()){
            try{
                String sql = "UPDATE games SET fieldId = ? , sportId = ? , teamId = ? , dateTime = ? , duration = ?,played = ? WHERE gameId = ?";
                PreparedStatement stmt = db.getConn().prepareStatement(sql);
                stmt.setInt(1,game.getFieldId());
                stmt.setInt(2,game.getSportId());
                stmt.setInt(3,game.getTeamId());
                stmt.setTimestamp(4, Timestamp.valueOf(game.getTime()));
                stmt.setFloat(5,game.getDuration());
                stmt.setBoolean(6,game.isPlayed());
                stmt.setInt( 7,game.getGameId());
                int rs = stmt.executeUpdate();
                if(rs == 1)return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        try{
            String sql = "INSERT INTO games(dateTime,fieldId,sportId,teamId,duration) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(game.getTime()));
            stmt.setInt(2,game.getFieldId());
            stmt.setInt(3,game.getSportId());
            stmt.setInt(4,game.getTeamId());
            stmt.setFloat(5,game.getDuration());

            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }

}
