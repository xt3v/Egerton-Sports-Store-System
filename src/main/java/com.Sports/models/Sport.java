package com.Sports.models;

import com.Sports.repositories.SportRepository;
import com.Sports.services.DatabaseService;

public class Sport {
    private static  DatabaseService db = DatabaseService.getInstance();
    private String name;
    private int sportId;

    public Sport(String name, int sportId) {
        this.name = name;
        this.sportId = sportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSportId() {
        return this.sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    //checks if the sport name alreagy exists
    public static boolean checkIfExists(String name){
       boolean isPresent = db.getSportRepository().getByName(name).isPresent();
       if(isPresent){
           return true;
       }
       return false;
    }

}

