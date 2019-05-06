package com.Sports.models;

public class Field {
    private String fieldName;
    private int fieldId;
    private int sportId;

    public Field(String fieldname, int fieldId, int sportId) {
        this.fieldName = fieldname;
        this.fieldId = fieldId;
        this.sportId = sportId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldname) {
        this.fieldName = fieldname;
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
