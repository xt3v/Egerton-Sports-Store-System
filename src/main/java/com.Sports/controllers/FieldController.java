package com.Sports.controllers;

import com.Sports.models.Field;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@WebServlet("/fields")
public class FieldController extends HttpServlet {

    private DatabaseService db = DatabaseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HERE");
        String idArray[] = req.getParameterValues("sportId");
        if(idArray != null){

        }else{
            String rees[] = req.getParameterValues("req");
            System.out.println(rees);
            if(rees != null){
                System.out.println("HERE");
                getAvailable(rees[0],resp);
            }else {
                getAll(resp);
            }

        }
    }

    private void getAvailable(String ree,HttpServletResponse resp) {
        PrintWriter out = null;
        try{
            out = resp.getWriter();
            GameRequest gameRequest = db.getGameRequestRepository().getById(Integer.parseInt(ree)).get();
            ArrayList<Integer> fields = new ArrayList<>();
            db.getGameRepository().getAll().stream()
                    .filter( g -> g.getSportId() == db.getTeamRepository().getById(gameRequest.getTeamId()).get().getSportId()
                            && g.getTime().toLocalDate().equals(gameRequest.getDateTime().toLocalDate()))

                    .forEach(g -> {
                        LocalTime start = g.getTime().toLocalTime();
                        LocalTime end  = start.plusHours((long) g.getDuration());
                        LocalTime rstart = gameRequest.getDateTime().toLocalTime();
                        LocalTime rend = rstart.plusHours((long)gameRequest.getDuration());
                        if( start.equals(rstart) || (rstart.isAfter(start) && rstart.isBefore(end)) || (rend.isAfter(start) && rend.isBefore(end)) ||
                                rend.equals(end) ){
                            fields.add(g.getFieldId());
                        }
                    });
            ArrayList<Field> fieldArrayList = new ArrayList<>();


            db.getFieldRepository().getAll().forEach(f->{ ;
                Team team = db.getTeamRepository().getById(gameRequest.getTeamId()).get();
                if(fields.indexOf(f.getFieldId()) < 0 && f.getSportId() == team.getSportId()){
                    fieldArrayList.add(f);
                }
            });
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.write(fieldArrayList.toString());
            out.flush();
            out.close();
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSon = new JSONObject("{'message':'Error Saving'}");
            out.write(responseJSon.toString());
            out.flush();
            out.close();
        }
    }

    private void getAll(HttpServletResponse resp) throws IOException{
        JSONArray repArray = new JSONArray();
        db.getFieldRepository().getAll().forEach(field->{
            JSONObject obj = new JSONObject(field.toString());
            repArray.put(obj);
        });
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        out.write(repArray.toString());
        out.flush();
        out.close();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        JSONObject fieldJSON = JsonService.getFromRequest(req);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Field field = new Field(fieldJSON);
        boolean isPresent = Field.checkIfExists(field);
        JSONObject responseJSon = null;

        if(isPresent){
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            responseJSon = new JSONObject("{'message':'Field already exists'}");
        }else{
            boolean saved  = db.getFieldRepository().save(field);
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
        JSONObject fieldJSON = JsonService.getFromRequest(req);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Field field = new Field(fieldJSON);
        System.out.println(field.toString());
        boolean isPresent = Field.checkIfExists(field);
        JSONObject responseJSon = null;

        boolean saved  = db.getFieldRepository().save(field);
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
