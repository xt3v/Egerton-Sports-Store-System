package com.Sports.models;


import com.Sports.services.DatabaseService;
import org.json.JSONObject;

import java.time.LocalDateTime;
public class Game {
    private int sportId;
    private int teamId;
    private LocalDateTime time;
    private int fieldId;
    private int gameId;
    private float duration;
    private  boolean played;

    private DatabaseService db = DatabaseService.getInstance();

    public Game(int sportId, int teamId,LocalDateTime time, int fieldId, int gameId,float duration) {
        this.sportId = sportId;
        this.teamId = teamId;
        this.time = time;
        this.fieldId = fieldId;
        this.gameId = gameId;
        this.duration = duration;
        this.played = false;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sportId",this.sportId);
        jsonObject.put("teamId",this.teamId);
        jsonObject.put("time",this.time);
        jsonObject.put("field",new JSONObject(db.getFieldRepository().getById(this.getFieldId()).get()));
        jsonObject.put("sportName",db.getSportRepository().getById(this.sportId).get().getName());
        jsonObject.put("teamName",db.getTeamRepository().getById(this.teamId).get().getName());
        jsonObject.put("fieldId",this.fieldId);
        jsonObject.put("gameId",this.getGameId());
        jsonObject.put("duration",this.duration);
        jsonObject.put("played",this.played);
        jsonObject.put("date",this.time.toLocalDate());
        jsonObject.put("actualTime",this.time.toLocalTime());
        return jsonObject.toString();

    }
}