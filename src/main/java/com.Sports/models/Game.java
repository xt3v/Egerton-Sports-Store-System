package com.Sports.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Game {
   private int sportId;
   private String field;
   private LocalDate date;
   private LocalTime time;
   private int fieldId;

    public Game(int sportId, String field, LocalDate date, LocalTime time, int fieldId) {
        this.sportId = sportId;
        this.field = field;
        this.date = date;
        this.time = time;
        this.fieldId = fieldId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public boolean setTime(){
        return true;
    }

    public boolean setDate(){
        return true;
    }

}
