package com.Sports.repositories;

import com.Sports.models.BorrowedItem;
import com.Sports.services.DatabaseHandler;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BorrowedItemRepository implements Repository<Integer,BorrowedItem> {
    private DatabaseHandler DBHandler;

    @Override
    public BorrowedItem getById(Integer id) {
        return null;
    }

    @Override
    public ArrayList<BorrowedItem> getAll() {
        return null;
    }

    @Override
    public ArrayList<BorrowedItem> queryList(String sql) {
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
    public boolean save(BorrowedItem entry) {
        return false;
    }

    @Override
    public void setDBHandler(DatabaseHandler handler) {
        this.DBHandler = handler;
    }
}
