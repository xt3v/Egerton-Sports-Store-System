package com.Sports.repositories;

import com.Sports.models.Field;
import com.Sports.services.DatabaseHandler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class FieldRepository implements Repository<Integer, Field> {
    private DatabaseHandler DBHandler;

    @Override
    public Field getById(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Field> getAll() {
        return null;
    }

    @Override
    public ArrayList<Field> queryList(String sql) {
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
    public boolean save(Field entry) {
        return false;
    }

    @Override
    public void setDBHandler(DatabaseHandler handler) {
        this.DBHandler = handler;
    }
}
