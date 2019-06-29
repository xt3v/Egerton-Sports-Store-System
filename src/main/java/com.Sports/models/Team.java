package com.Sports.models;

import com.Sports.services.DatabaseService;
import org.json.JSONObject;

import java.util.Optional;

public class Team {
    private static DatabaseService databaseservice = DatabaseService.getInstance();
    private String name;
    private String gender;
    private String captainRegNo;
    private String coachId;
    private int teamId;
    private int sportId;

    public Team(String name, String gender, String captainRegNo, String coachId, int teamId, int sportId) {
        this.name = name;
        this.gender = gender;
        this.captainRegNo = captainRegNo;
        this.coachId = coachId;
        this.teamId = teamId;
        this.sportId = sportId;
    }

    public Team(JSONObject teamJSON) {
       this.name = teamJSON.getString("name");
       this.gender = teamJSON.getString("gender");
       this.captainRegNo = teamJSON.getString("captainRegNo");
       this.coachId = teamJSON.getString("coachId");
       this.sportId = teamJSON.getInt("sportId");
       this.teamId = teamJSON.getInt("teamId");
    }

    public static boolean checkIfExists(Team team) {
        return databaseservice.getTeamRepository().getByName(team.getName()).isPresent();
    }

    public static Optional<Team> getByCoachCaptain(String id) {
       return  databaseservice.getTeamRepository().getAll()
                .stream()
                .filter(t-> t.getCoachId().equals(id) || t.getCaptainRegNo().equals(id))
                .findFirst();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCaptainRegNo() {
        return captainRegNo;
    }

    public void setCaptainRegNo(String captainRegNo) {
        this.captainRegNo = captainRegNo;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public boolean setCaptain(){
        return true;
    }


    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",this.name);
        jsonObject.put("gender",this.gender);
        jsonObject.put("captainRegNo",this.captainRegNo);
        jsonObject.put("coachId",this.coachId);
        jsonObject.put("teamId",this.teamId);
        jsonObject.put("sportId",this.sportId);
        jsonObject.put("sportName",databaseservice.getSportRepository().getById(this.sportId).get().getName());
        jsonObject.put("captainName",databaseservice.getStudentRepository().getById(this.captainRegNo).get().getName());
        jsonObject.put("coachName",databaseservice.getCoachRepository().getById(this.coachId).get().getName());
        return jsonObject.toString();
    }
}
