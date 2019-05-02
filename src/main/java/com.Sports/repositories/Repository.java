package com.Sports.repositories;

import com.Sports.services.DatabaseHandler;
import com.Sports.services.DatabaseService;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface Repository<T,S>{

    //get object by identifier
    S getById(T id);

    //get all objects
    ArrayList<S> getAll();

    //enter raw sql to get object
    ArrayList<S> queryList(String sql);

    //enter row sql to get ResultSet
    ResultSet querySet(String sql);

    //delete from db3
    boolean deleteById(T identifier);

    //add to db
    boolean save(S entry);

    void setDBHandler(DatabaseHandler handler);
}
