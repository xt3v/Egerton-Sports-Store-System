package com.Sports.controllers;

import com.Sports.models.Team;
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

@WebServlet("/teams")
public class TeamController extends HttpServlet {
    private DatabaseService db = DatabaseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idArray[] = req.getParameterValues("id");
        if(idArray != null){
            getSingle(idArray[0],resp);
        }else{
            String sportIdArray[] = req.getParameterValues("sportId");
            if(sportIdArray != null){
                getAllBySport(sportIdArray[0],resp);
            }else{
                getAll(resp);
            }
        }
    }

    private void getAllBySport(String id, HttpServletResponse resp) {
        PrintWriter out = null;
        try{
            out = resp.getWriter();
            ArrayList<Team> teams  = db.getTeamRepository().getBySportId(Integer.parseInt(id));
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            JSONArray teamsArray = new JSONArray();

            for (Team team : teams) {
               JSONObject teamJson = new JSONObject(team.toString());
               teamsArray.put(teamJson);
            }

            out.write(teamsArray.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSon = new JSONObject("{'message':'Internal error'}");
            out.write(responseJSon.toString());
            out.flush();
            out.close();
        }
    }

    private void getAll(HttpServletResponse resp) {
    }

    private void getSingle(String ids, HttpServletResponse resp) {
        PrintWriter out = null;
        try{
            int id = Integer.parseInt(ids);
            out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Team team = db.getTeamRepository().getById(id).get();

            if(team == null){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                JSONObject responseJSon = new JSONObject("{'message':'Internal error'}");
                out.write(responseJSon.toString());
            }else{
                JSONObject teamJSON = new JSONObject();
                out.write(teamJSON.toString());
            }

            out.flush();
            out.close();
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSon = new JSONObject("{'message':'Internal error'}");
            out.write(responseJSon.toString());
            out.flush();
            out.close();
        }
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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try{
            out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            JSONObject teamJson = JsonService.getFromRequest(req);
            System.out.println(teamJson.toString());
            Team team = new Team(teamJson);

            //TODO validate coach id and captain reg no
            JSONObject responseJSon;
            if(!db.getStudentRepository().getById(team.getCaptainRegNo()).isPresent()){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseJSon = new JSONObject("{'message':'Captain id is not valid'}");
            }else if(!db.getCoachRepository().getById(team.getCoachId()).isPresent()){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseJSon = new JSONObject("{'message':'Coach id is not valid'}");
            }else if(db.getTeamRepository().save(team)){
               responseJSon = new JSONObject("{'status': 'success'}");
            }else{
               resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
               responseJSon = new JSONObject("{'message':'Error Saving'}");
            }

            out.write(responseJSon.toString());
            out.flush();
            out.close();

        }catch (Exception e){
            System.out.println(e.getMessage()+" put teams");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSon = new JSONObject("{'message':'Error Saving'}");
            out.write(responseJSon.toString());
            out.flush();
            out.close();
        }
    }
}
