package com.Sports.repositories;

import com.Sports.models.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class ItemRepository implements Repository<Integer, Item> {


    @Override
    public Optional<Item> getById(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Item> getAll() {
        return null;
    }

    @Override
    public ArrayList<Item> queryList(PreparedStatement stmt) {
        return null;
    }

    @Override
    public ResultSet querySet(PreparedStatement stmt) {
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


}
