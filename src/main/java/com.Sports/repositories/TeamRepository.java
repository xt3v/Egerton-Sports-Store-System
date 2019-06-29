package com.Sports.repositories;

import com.Sports.models.Team;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class TeamRepository implements Repository<Integer, Team> {

    @Override
    public Optional<Team> getById(Integer id) {
        Team team = null;
        try{
            String sql = "Select * FROM teams WHERE teamId  = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                team = new Team(rs.getString("name"),rs.getString("gender"),rs.getString("captainRegNo"),rs.getString("coachId"),rs.getInt("teamId"),rs.getInt("sportId"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "team repo");
        }
        Optional<Team> optionalTeam = Optional.ofNullable(team);
        return optionalTeam;
    }

    @Override
    public ArrayList<Team> getAll() {
        ResultSet rs = db.executeQuery("SELECT * FROM teams");
        return createList(rs);
    }

    private ArrayList<Team> createList(ResultSet rs) {
        ArrayList<Team> list = new ArrayList<>();
        try{
            while (rs.next()){
                 Team team = new Team(rs.getString("name"),rs.getString("gender"),rs.getString("captainRegNo"),rs.getString("coachId"),rs.getInt("teamId"),rs.getInt("sportId"));
                list.add(team);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" create team list");
        }
        return list;
    }

    @Override
    public ArrayList<Team> queryList(PreparedStatement stmt) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            return createList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" student query list");
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
            String sql = "DELETE FROM teams WHERE teamId = ?";
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
    public boolean save(Team entry) {
        Team team = entry;

        Optional<Team> optionalTeam = getById(team.getTeamId());

        if(optionalTeam.isPresent()){
            try{
                String sql = "UPDATE teams SET name = ?,gender = ?, captainRegNo = ? , coachId = ?, sportId = ? WHERE teamId = ?";
                PreparedStatement stmt = db.getConn().prepareStatement(sql);
                stmt.setString(1,team.getName());
                stmt.setString(2,team.getGender());
                stmt.setString(3,team.getCaptainRegNo());
                stmt.setString(4,team.getCoachId());
                stmt.setInt(5,team.getSportId());
                stmt.setInt(6,team.getTeamId());

                int rs = stmt.executeUpdate();
                if(rs == 1)return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        try{
            String sql = "INSERT INTO teams(name,gender,captainRegNo,coachId,sportId) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1,team.getName());
            stmt.setString(2,team.getGender());
            stmt.setString(3,team.getCaptainRegNo());
            stmt.setString(4,team.getCoachId());
            stmt.setInt(5,team.getSportId());

            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }


    public Optional<Team> getByName(String name) {
        Team team = null;
        try{
            String sql = "SELECT * FROM teams WHERE name = ?";
            PreparedStatement statement = db.getConn().prepareStatement(sql);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                team = new Team(rs.getString("name"),rs.getString("gender"),rs.getString("captainRegNo"),rs.getString("coachId"),rs.getInt("teamId"),rs.getInt("sportId"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() +" get sport by name");
        }
        Optional<Team> optionalTeam = Optional.ofNullable(team);
        return optionalTeam;
    }

    public ArrayList<Team> getBySportId(int sportId) {
        try{

            String sql = "SELECT * FROM teams WHERE sportId = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1,sportId);
            return queryList(stmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }
}
