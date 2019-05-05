package com.Sports.repositories;

import com.Sports.models.Field;
import javafx.scene.Parent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class FieldRepository implements Repository<Integer, Field> {


    @Override
    public Optional<Field> getById(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Field> getAll() {
        return null;
    }

    @Override
    public ArrayList<Field> queryList(PreparedStatement stmt) {
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
    public boolean save(Field entry) {
        return false;
    }

}
