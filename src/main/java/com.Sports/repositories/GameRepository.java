package com.Sports.repositories;

import com.Sports.models.Game;
import com.Sports.services.DatabaseHandler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GameRepository implements Repository<Integer, Game> {
    private DatabaseHandler DBHandler;

    @Override
    public Game getById(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Game> getAll() {
        return null;
    }

    @Override
    public ArrayList<Game> queryList(String sql) {
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
    public boolean save(Game entry) {
        return false;
    }

    @Override
    public void setDBHandler(DatabaseHandler handler) {
        this.DBHandler = handler;
    }
}
