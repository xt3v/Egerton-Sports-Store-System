package com.Sports.models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String regNo;
    private String name;
    private String residence;
    private String phoneNo;
    //TODO  add borrowed item list
     private List<BorrowedItem> borrowedItems;
     private List<BorrowedItem> lostItems;
    private boolean isCaptain;

    public Student(String regNo, String name, String residence,String phoneNo, boolean isCaptain) {
        this.regNo = regNo;
        this.name = name;
        this.residence = residence;
        this.phoneNo = phoneNo;
        this.isCaptain = isCaptain;
        this.lostItems = new ArrayList<>();
        this.borrowedItems = new ArrayList<>();
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

    public boolean isCaptain() {
        return isCaptain;
    }

    public void setCaptain(boolean captain) {
        isCaptain = captain;
    }

    public boolean issueItem(){
       return true;
    }

    public boolean clear(){
        return true;
    }

}
