package com.Sports.models;

import com.Sports.services.DatabaseService;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    private static DatabaseService db = DatabaseService.getInstance();
    private String name;
    private String employeeId;
    private List<BorrowedItem> borrowedItemList;

    public Coach(String name, String employeeId) {
        this.name = name;
        this.employeeId = employeeId;
        this.borrowedItemList = new ArrayList<>();
    }

    public Coach(JSONObject coachJSON) {
        this.name = coachJSON.getString("name");
        this.employeeId = coachJSON.getString("employeeId");
    }

    public List<BorrowedItem> getBorrowedItemList() {
        return borrowedItemList;
    }

    public void setBorrowedItemList(List<BorrowedItem> borrowedItemList) {
        this.borrowedItemList = borrowedItemList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public static boolean checkIfExists(Coach coach) {
        boolean isPresent = db.getCoachRepository().getById(coach.getEmployeeId()).isPresent();

        return isPresent;
    }
}
