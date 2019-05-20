package com.Sports.models;

import com.Sports.services.DatabaseService;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String regNo;
    private String name;
    private String residence;
    private String phoneNo;
    private List<BorrowedItem> borrowedItems;
    //TODO add lost items
    private List<BorrowedItem> lostItems;
    private static DatabaseService db = DatabaseService.getInstance();

    public Student(String regNo, String name, String residence,String phoneNo) {
        this.regNo = regNo;
        this.name = name;
        this.residence = residence;
        this.phoneNo = phoneNo;
        this.lostItems = new ArrayList<>();
        this.borrowedItems = new ArrayList<>();
    }

    public Student(JSONObject studentJSON) {
        this.regNo = studentJSON.getString("regNo");
        this.name = studentJSON.getString("name");
        this.residence = studentJSON.getString("residence");
        this.phoneNo = studentJSON.getString("phoneNo");
    }

    public static boolean checkIfExists(Student student) {
        return db.getStudentRepository().getById(student.getRegNo()).isPresent();
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public List<BorrowedItem> getBorrowedItems() {
        return borrowedItems;
    }

    public void setBorrowedItems(List<BorrowedItem> borrowedItems) {
        this.borrowedItems = borrowedItems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    //TODO iscaptain function

    public boolean issueItem(){
       return true;
    }

    public boolean clear(){
        return true;
    }

    public boolean isCaptain() {
        boolean res[] = {false};
        db.getTeamRepository().getAll().forEach(team->{
            if(team.getCaptainRegNo().equals(this.regNo)){
                res[0] = true;
            }
        });
        return res[0];
    }
}
