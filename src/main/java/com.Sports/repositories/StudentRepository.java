package com.Sports.repositories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class StudentRepository implements Repository<String, StudentRepository> {


    @Override
    public Optional<StudentRepository> getById(String id) {
        return null;
    }

    @Override
    public ArrayList<StudentRepository> getAll() {
        return null;
    }

    @Override
    public ArrayList<StudentRepository> queryList(PreparedStatement stmt) {
        return null;
    }

    @Override
    public ResultSet querySet(PreparedStatement stmt) {
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


}
