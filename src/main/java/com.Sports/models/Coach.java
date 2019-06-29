package com.Sports.models;

import com.Sports.services.DatabaseService;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Team getTeam(){
        Optional<Team> team =  db.getTeamRepository().getAll().stream().filter(t->t.getCoachId().equals(this.employeeId)).findFirst();
        if(team.isPresent()) return team.get();

        return  new Team("None","", "","",-1,-1);
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


    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("name",this.name);
        json.put("employeeId",this.employeeId);
        Team team = this.getTeam();
        json.put("teamId",team.getTeamId());
        json.put("teamName",team.getName());
        if(team.getTeamId() == -1){
            json.put("sportName","None");
        }else{
            json.put("sportName",db.getSportRepository().getById(team.getSportId()).get().getName());
        }
        return json.toString();
    }
}
