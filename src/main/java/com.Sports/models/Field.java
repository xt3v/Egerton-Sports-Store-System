package com.Sports.models;

public class Field {
    private String fieldname;
    private int fieldId;
    private int sportId;

    public Field(String fieldname, int fieldId, int sportId) {
        this.fieldname = fieldname;
        this.fieldId = fieldId;
        sportId = sportId;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        sportId = sportId;
    }

    }
