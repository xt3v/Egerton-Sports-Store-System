package com.Sports.repositories;

import com.Sports.models.Team;
import com.Sports.services.DatabaseHandler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TeamRepository implements Repository<Integer, Team> {
    private DatabaseHandler DBHandler;

    @Override
    public Team getById(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Team> getAll() {
        return null;
    }

    @Override
    public ArrayList<Team> queryList(String sql) {
        return null;
    }

    @Override
    public ResultSet querySet(String sql) {
        return null;
    }

    @Override
    public boolean deleteById(Integer identifier) {
        return false;
    }

    @Override
    public boolean save(Team entry) {
        return false;
    }

    @Override
    public void setDBHandler(DatabaseHandler handler) {
        this.DBHandler = handler;
    }
}
