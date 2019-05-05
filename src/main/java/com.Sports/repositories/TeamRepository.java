package com.Sports.repositories;

import com.Sports.models.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public class TeamRepository implements Repository<Integer, Team> {

    @Override
    public Optional<Team> getById(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Team> getAll() {
        return null;
    }

    @Override
    public ArrayList<Team> queryList(PreparedStatement stmt) {
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
    public boolean save(Team entry) {
        return false;
    }


}
