package com.Sports.models;


import java.time.LocalDateTime;
public class Game {
    private int sportId;
    private int teamId;
    private LocalDateTime time;
    private int fieldId;
    private int gameId;

    public Game(int sportId, int teamId,LocalDateTime time, int fieldId, int gameId) {
        this.sportId = sportId;
        this.teamId = teamId;
        this.time = time;
        this.fieldId = fieldId;
        this.gameId = gameId;

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


}