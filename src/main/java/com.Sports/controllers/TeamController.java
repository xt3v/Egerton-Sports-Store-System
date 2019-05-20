package com.Sports.controllers;

import com.Sports.models.Team;
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

@WebServlet("/teams")
public class TeamController extends HttpServlet {
    private DatabaseService db = DatabaseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject teamJSON = JsonService.getFromRequest(req);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Team  team = new Team(teamJSON);

        boolean isPresent = Team.checkIfExists(team);
        //TODO check if the coach and captain exists
        JSONObject responseJSon = null;

        if(isPresent){
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            responseJSon = new JSONObject("{'message':'Team already exists'}");

        }else{
            boolean saved  = db.getTeamRepository().save(team);
            if(saved){
                responseJSon = new JSONObject("{'status': 'success'}");
            }else{
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseJSon = new JSONObject("{'message':'Error Saving'}");
            }
        }

        out.write(responseJSon.toString());
        out.flush();
        out.close();
    }
}
