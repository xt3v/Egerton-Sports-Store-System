package com.Sports.repositories;

import com.Sports.models.Game;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class GameRepository implements Repository<Integer, Game> {

    @Override
    public Optional<Game> getById(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Game> getAll() {
        return null;
    }

    @Override
    public ArrayList<Game> queryList(PreparedStatement stmt) {
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
    public boolean save(Game entry) {
        return false;
    }


}
