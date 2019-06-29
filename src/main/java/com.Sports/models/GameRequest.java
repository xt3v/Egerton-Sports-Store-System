package com.Sports.models;

import com.Sports.services.DatabaseService;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class GameRequest {
    private LocalDateTime dateTime;
    private float duration;
    private int teamId;
    private int id;
    private boolean approved;
    private String requesterId;
    private boolean declined;

    private DatabaseService databaseService = DatabaseService.getInstance();

    public GameRequest(LocalDateTime dateTime,float duration, int teamId, int id, boolean approved, String requesterId) {
        this.dateTime = dateTime;
        this.duration = duration;
        this.teamId = teamId;
        this.id = id;
        this.approved = approved;
        this.requesterId = requesterId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public boolean isDeclined() {
        return declined;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setDeclined(boolean declined) {
        this.declined = declined;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("duration",this.duration);
        jsonObject.put("dateTime",this.dateTime);
        jsonObject.put("team",new JSONObject(databaseService.getTeamRepository().getById(this.teamId).get().toString()));
        jsonObject.put("id",this.id);
        jsonObject.put("approved",this.approved);
        jsonObject.put("requesterId",this.requesterId);
        jsonObject.put("date",this.dateTime.toLocalDate());
        jsonObject.put("declined",this.declined);
        jsonObject.put("time",this.dateTime.toLocalTime());
        return jsonObject.toString();
    }
}
