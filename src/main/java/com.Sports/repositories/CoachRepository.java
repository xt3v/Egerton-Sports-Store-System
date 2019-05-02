package com.Sports.repositories;

import com.Sports.models.Coach;
import com.Sports.services.DatabaseHandler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CoachRepository implements Repository<String, Coach> {


    private DatabaseHandler DBHandler;

    @Override
    public Coach getById(String id) {
        return null;
    }

    @Override
    public ArrayList<Coach> getAll() {
        return null;
    }

    @Override
    public ArrayList<Coach> queryList(String sql) {
        return null;
    }

    @Override
    public ResultSet querySet(String sql) {
        return null;
    }

    @Override
    public boolean deleteById(String identifier) {
        return false;
    }


    @Override
    public boolean save(Coach entry) {
        return false;
    }

    @Override
    public void setDBHandler(DatabaseHandler handler) {
        this.DBHandler = handler;
    }
}
