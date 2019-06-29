package com.Sports.controllers;


import com.Sports.models.GameRequest;
import com.Sports.models.Team;
import com.Sports.services.DatabaseService;
import com.Sports.services.JsonService;
import jdk.nashorn.internal.parser.DateParser;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.awt.X11.XException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet("/gamerequest")
public class GameRequestsController extends HttpServlet {
    DatabaseService databaseService = DatabaseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAll(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try{
            out = resp.getWriter();
            JSONObject jsonObject = JsonService.getFromRequest(req);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate localDate = LocalDate.parse(jsonObject.getString("date"),formatter);
            LocalTime localTime = LocalTime.parse(jsonObject.getString("time"));
            LocalDateTime localDateTime = LocalDateTime.of(localDate,localTime);
            System.out.println("HERE !");
            int teamId ;
            if(Team.getByCoachCaptain(jsonObject.getString("userId")).isPresent()){
                teamId = Team.getByCoachCaptain(jsonObject.getString("userId")).get().getTeamId();
            }else{
                throw new Exception("Coach has no team !");
            }

            GameRequest gameRequest = new GameRequest(localDateTime,jsonObject.getInt("duration"),teamId,-1,false,jsonObject.getString("userId"));

            if(!databaseService.getGameRequestRepository().save(gameRequest)){
               throw new Exception("Not saved");
            }
        }catch (Exception e){
            System.out.println(e.getMessage() +" post game request");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write(new JSONObject("{'message':'"+e.getMessage()+"'}").toString());
            out.flush();
            out.close();
        }
    }

    private void getAll(HttpServletResponse resp){
         PrintWriter out = null;
         try{
             out = resp.getWriter();
             ArrayList<GameRequest> gameRequestArrayList =  databaseService.getGameRequestRepository().getAll();

             JSONArray jsonArray = new JSONArray();
             gameRequestArrayList.forEach( req->{
                  if(!req.isDeclined() && !req.isApproved()){
                      JSONObject jsonObject = new JSONObject(req.toString());
                      jsonArray.put(jsonObject);
                  }
             });
             resp.setContentType("application/json");
             resp.setCharacterEncoding("UTF-8");
             out.write(jsonArray.toString());
             out.flush();
             out.close();

         }catch (Exception e){
             resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
             JSONObject responseJSON = new JSONObject("{'message':'"+e.getMessage()+"'}");
             out.write(responseJSON.toString());
             out.flush();
             out.close();
             System.out.println(e.getMessage() +" get game requests items");
         }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try{
            out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String ids[] = req.getParameterValues("id");
            GameRequest gameRequest = databaseService.getGameRequestRepository().getById(Integer.parseInt(ids[0])).get();
            gameRequest.setDeclined(true);
            if(!databaseService.getGameRequestRepository().save(gameRequest)){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                JSONObject responseJSON = new JSONObject("{'message':'Did not save rejection'}");
                out.write(responseJSON.toString());
                out.flush();
                out.close();
            }
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSON = new JSONObject("{'message':'"+e.getMessage()+"'}");
            out.write(responseJSON.toString());
            out.flush();
            out.close();
            System.out.println(e.getMessage() +" decline game requests items");
        }
    }
}
