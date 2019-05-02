package com.Sports.repositories;

import com.Sports.models.Sport;
import com.Sports.services.DatabaseHandler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SportRepository implements Repository<Integer, Sport> {
    private DatabaseHandler DBHandler;

    @Override
    public Sport getById(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Sport> getAll() {
        return null;
    }

    @Override
    public ArrayList<Sport> queryList(String sql) {
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
    public boolean save(Sport entry) {
        return false;
    }

    @Override
    public void setDBHandler(DatabaseHandler handler) {
        this.DBHandler = handler;
    }
}
