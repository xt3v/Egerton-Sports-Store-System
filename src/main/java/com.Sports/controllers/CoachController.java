package com.Sports.controllers;

import com.Sports.models.Coach;
import com.Sports.services.Auth;
import com.Sports.services.DatabaseService;
import com.Sports.services.JsonService;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/coaches")
public class CoachController  extends HttpServlet {

    private DatabaseService dbService = DatabaseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idArray[] = req.getParameterValues("id");
        if(idArray != null){
            getSingle(idArray[0],resp);
        }else{
            getAll(resp);
        }
    }

    private void getSingle(String s, HttpServletResponse resp) {
    }

    private void getAll(HttpServletResponse response){
        PrintWriter out = null;
        try{
            ArrayList<Coach> coaches = dbService.getCoachRepository().getAll();
            out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            JSONArray coachArray = new JSONArray();
            coaches.forEach(coach -> {
                coachArray.put(new JSONObject(coach.toString()));
            });

            out.write(coachArray.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + " getting all  coches");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSon = new JSONObject("{'message':'Internal error'}");
            out.write(responseJSon.toString());
            out.flush();
            out.close();

        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        JSONObject coachJSON = JsonService.getFromRequest(req);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Coach coach = new Coach(coachJSON);
        boolean isPresent = Coach.checkIfExists(coach);
        JSONObject responseJSon = null;
        if(isPresent){
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            responseJSon = new JSONObject("{'message':'Coach already exists'}");

        }else{
            boolean saved  = dbService.getCoachRepository().save(coach);

            if(saved){
                Auth.createAccount("coach",coach.getEmployeeId(),coach.getEmployeeId());
                responseJSon = new JSONObject("{'status': 'success'}");
            }else{
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseJSon = new JSONObject("{'message':'Error Saving'}");
            }
        }
        System.out.println(responseJSon.toString());
        out.write(responseJSon.toString());
        out.flush();
        out.close();
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject coachJSON = JsonService.getFromRequest(req);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Coach coach = new Coach(coachJSON);
        System.out.println(coachJSON.toString());
        boolean saved  = dbService.getCoachRepository().save(coach);
        JSONObject responseJSon;
        if(saved){
            responseJSon = new JSONObject("{'status': 'success'}");
        }else{
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseJSon = new JSONObject("{'message':'Error Saving'}");
        }

        out.write(responseJSon.toString());
        out.flush();
        out.close();
    }
}
