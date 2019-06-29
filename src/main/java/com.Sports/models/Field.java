package com.Sports.models;

import com.Sports.services.DatabaseService;
import org.json.JSONObject;

public class Field {
    private static DatabaseService db = DatabaseService.getInstance();
    private String fieldName;
    private int fieldId;
    private int sportId;

    public Field(String fieldName, int fieldId, int sportId) {
        this.fieldName = fieldName;
        this.fieldId = fieldId;
        this.sportId = sportId;
    }

    public Field(JSONObject fieldJSON) {
        this.fieldName = fieldJSON.getString("fieldName");
        this.fieldId = fieldJSON.getInt("fieldId");
        this.sportId = fieldJSON.getInt("sportId");
    }

    public static boolean checkIfExists(Field field) {
       return db.getFieldRepository().getByName(field.getFieldName()).isPresent();
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


    @Override
    public String toString() {
        JSONObject res = new JSONObject();
        res.put("fieldName",this.fieldName);
        res.put("fieldId",this.fieldId);
        res.put("sportId",this.sportId);
        res.put("sportName",db.getSportRepository().getById(this.sportId).get().getName());
        return res.toString();
    }
}
