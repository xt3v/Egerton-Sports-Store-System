package com.Sports.repositories;
import com.Sports.services.DatabaseHandler;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentRepository implements Repository<String, StudentRepository> {
    private DatabaseHandler DBHandler;

    @Override
    public StudentRepository getById(String id) {
        return null;
    }

    @Override
    public ArrayList<StudentRepository> getAll() {
        return null;
    }

    @Override
    public ArrayList<StudentRepository> queryList(String sql) {
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
    public boolean save(StudentRepository entry) {
        return false;
    }

    @Override
    public void setDBHandler(DatabaseHandler handler) {
        this.DBHandler = handler;
    }
}
