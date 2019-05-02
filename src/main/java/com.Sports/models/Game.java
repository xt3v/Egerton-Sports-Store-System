package com.Sports.models;

import java.time.LocalDateTime;

public class Game {
   private int sportId;
   private String field;
   private LocalDateTime dateTime;
   private int fieldId;

    public Game(int sportId, String field, LocalDateTime dateTime, int fieldId) {
        this.sportId = sportId;
        this.field = field;
        this.dateTime = dateTime;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
