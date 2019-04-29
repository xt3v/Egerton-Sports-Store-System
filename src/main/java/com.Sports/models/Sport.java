package com.Sports.models;
public class Sport {
    private String name;
    private int sportId;

    public Sport(String name, int sportId) {
        this.name = name;
        sportId = sportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        sportId = sportId;
    }

}

