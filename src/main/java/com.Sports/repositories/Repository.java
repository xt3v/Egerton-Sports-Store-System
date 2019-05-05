package com.Sports.repositories;

import com.Sports.services.DatabaseHandler;
import com.Sports.services.DatabaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

public interface Repository<T,S>{

    DatabaseHandler db = DatabaseHandler.getInstance();

    //get object by identifier
    Optional<S> getById(T id);

    //get all objects
    ArrayList<S> getAll();

    //enter raw sql to get object
    ArrayList<S> queryList(PreparedStatement stmt);

    //enter row sql to get ResultSet
    ResultSet querySet(PreparedStatement stmt);

    //delete from db3
    boolean deleteById(T identifier);

    //add to db
    boolean save(S entry);

}
