package com.Sports.repositories;

import com.Sports.models.Item;
import com.Sports.services.DatabaseHandler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemRepository implements Repository<Integer, Item> {
    private DatabaseHandler DBHandler;

    @Override
    public Item getById(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Item> getAll() {
        return null;
    }

    @Override
    public ArrayList<Item> queryList(String sql) {
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
    public boolean save(Item entry) {
        return false;
    }
    @Override
    public void setDBHandler(DatabaseHandler handler) {
        this.DBHandler = handler;
    }

}
