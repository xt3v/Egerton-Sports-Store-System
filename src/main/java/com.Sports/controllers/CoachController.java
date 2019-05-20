package com.Sports.controllers;

import com.Sports.models.Coach;
import com.Sports.services.DatabaseService;
import com.Sports.services.JsonService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/coaches")
public class CoachController  extends HttpServlet {

    private DatabaseService dbService = DatabaseService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        JSONObject coachJSON = JsonService.getFromRequest(req);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Coach coach = new Coach(coachJSON);
        System.out.println("EMPLOYEEE");
        System.out.println(coach.getEmployeeId());
        System.out.println(coach.getName());
        boolean isPresent = Coach.checkIfExists(coach);
        System.out.println(isPresent);
        JSONObject responseJSon = null;
        if(isPresent){
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            responseJSon = new JSONObject("{'message':'Coach already exists'}");

        }else{
            System.out.println("SAVING");
            boolean saved  = dbService.getCoachRepository().save(coach);

            if(saved){
                System.out.println("SAVED");
                responseJSon = new JSONObject("{'status': 'success'}");
            }else{
                System.out.println("NOT SAVED");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseJSon = new JSONObject("{'message':'Error Saving'}");
            }
        }
        System.out.println(responseJSon.toString());
        out.write(responseJSon.toString());
        out.flush();
        out.close();
    }
}
