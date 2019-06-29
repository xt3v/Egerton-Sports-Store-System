package com.Sports.controllers;

import com.Sports.models.Game;
import com.Sports.models.GameRequest;
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

@WebServlet("/games")
public class GameController extends HttpServlet {
    DatabaseService db = DatabaseService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out =null;
       try{
           out = resp.getWriter();
           resp.setContentType("application/json");
           resp.setCharacterEncoding("UTF-8");
           JSONObject jsonObject = JsonService.getFromRequest(req);

           if(jsonObject.has("requestId")){
               int reqId = Integer.parseInt(jsonObject.getString("requestId"));
               GameRequest gameRequest = db.getGameRequestRepository().getById(reqId).get();
               Team team = db.getTeamRepository().getById(gameRequest.getTeamId()).get();
               gameRequest.setApproved(true);
               db.getGameRequestRepository().save(gameRequest);
               System.out.println("POPO");
               Game game = new Game(team.getSportId(),team.getTeamId(),gameRequest.getDateTime(),jsonObject.getInt("fieldId"),-1,gameRequest.getDuration());
               if(!db.getGameRepository().save(game)){
                   System.out.println("LOLO");
                   resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                   JSONObject responseJSON = new JSONObject("{'message':'Did not save !'}");
                   out.write(responseJSON.toString());
                   out.flush();
                   out.close();
               }
           }
       }catch (Exception e){
           resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
           JSONObject responseJSON = new JSONObject("{'message':'"+e.getMessage()+"'}");
           out.write(responseJSON.toString());
           out.flush();
           out.close();
           System.out.println(e.getMessage() +" post game requests items");
       }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playedS[] = req.getParameterValues("notplayed");
        if(playedS != null){
            String userIds[] =  req.getParameterValues("userId");
            if(userIds != null){
                getAllNotPlayedByUser(resp,userIds[0]);
            }else{
                getAllNotPlayed(resp);
            }
        }else{
            getAll(resp);
        }
    }

    private void getAllNotPlayedByUser(HttpServletResponse resp, String userId) {

        PrintWriter out = null;
        try{
            out = resp.getWriter();

            Team team = db.getTeamRepository().getAll()
                    .stream().filter(t-> t.getCaptainRegNo().equals(userId) || t.getCoachId().equals(userId))
                    .findFirst().get();

            ArrayList<Game> games= new ArrayList<>();
            db.getGameRepository().getAll().stream()
                    .filter(g->g.getTeamId() == team.getTeamId() && !g.isPlayed())
                    .forEach(g->{
                        games.add(g);
                    });
            JSONArray repArray = new JSONArray();
           games.forEach(game->{
               JSONObject obj = new JSONObject(game.toString());
               repArray.put(obj);
            });

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            out.write(repArray.toString());
            out.flush();
            out.close();
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSon = new JSONObject("{'message':'Error getting all games'}");
            out.write(responseJSon.toString());
            out.flush();
            out.close();
        }

    }

    private void getAllNotPlayed(HttpServletResponse resp) {
        PrintWriter out = null;
        try{
            out = resp.getWriter();
            JSONArray repArray = new JSONArray();
            db.getGameRepository().getAll().forEach(game->{
                if(!game.isPlayed()){
                    JSONObject obj = new JSONObject(game);
                    repArray.put(obj);
                }
            });

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            out.write(repArray.toString());
            out.flush();
            out.close();
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSon = new JSONObject("{'message':'Error getting all games'}");
            out.write(responseJSon.toString());
            out.flush();
            out.close();
        }
    }

    private void getAll(HttpServletResponse resp){
        PrintWriter out = null;
        try{
            out = resp.getWriter();
            JSONArray repArray = new JSONArray();
            db.getGameRepository().getAll().forEach(game->{
                JSONObject obj = new JSONObject(game.toString());
                repArray.put(obj);
            });

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            out.write(repArray.toString());
            out.flush();
            out.close();
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSon = new JSONObject("{'message':'Error getting all games'}");
            out.write(responseJSon.toString());
            out.flush();
            out.close();
        }
    }

}