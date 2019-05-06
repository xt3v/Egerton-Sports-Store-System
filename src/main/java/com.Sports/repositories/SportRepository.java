package com.Sports.repositories;

import com.Sports.models.Sport;
import javafx.scene.Parent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class SportRepository implements Repository<Integer, Sport> {


    @Override
    public Optional<Sport> getById(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Sport> getAll() {
        return null;
    }

    @Override
    public ArrayList<Sport> queryList(PreparedStatement statement) {
        return null;
    }

    @Override
    public ResultSet querySet(PreparedStatement statement) {
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

}
