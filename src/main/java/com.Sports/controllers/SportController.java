package com.Sports.controllers;

import com.Sports.models.Sport;
import com.Sports.models.Student;
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
import java.util.Optional;

@WebServlet("/sports")
public class SportController extends HttpServlet {
    private DatabaseService dbService = DatabaseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String idArray[] = req.getParameterValues("id");
         if(idArray != null){
             getSingleSports(idArray[0],resp);
         }else{
             getAllSports(resp);
         }
    }

    private void getSingleSports(String id,HttpServletResponse resp) {
      try{
          int idNo = Integer.parseInt(id);
          Optional<Sport> optionalSport = dbService.getSportRepository().getById(idNo);
          if(optionalSport.isPresent()){
              PrintWriter out = resp.getWriter();
              resp.setContentType("application/json");
              resp.setCharacterEncoding("UTF-8");
              JSONObject sportJSON = new JSONObject(optionalSport.get());
              out.write(sportJSON.toString());
              out.flush();
              out.close();
          }else{

          }
      } catch (IOException e) {
          System.out.println(e.getMessage() + " getting one sport");
      }

    }

    private  void getAllSports(HttpServletResponse resp){
        try{
            ArrayList<Sport> sports = dbService.getSportRepository().getAll();
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            JSONArray sportsArray = new JSONArray(sports);
            out.write(sportsArray.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + " getting all sports");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject sportJSON = JsonService.getFromRequest(req);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        boolean isPresent = Sport.checkIfExists(sportJSON.getString("name"));

        JSONObject responseJSon = null;
        if(isPresent){
             resp.setStatus(HttpServletResponse.SC_CONFLICT);
             responseJSon = new JSONObject("{'message':'Sport already exists'}");

        }else{

            Sport sport = new Sport(sportJSON.getString("name"),-1);
            boolean saved  = dbService.getSportRepository().save(sport);

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
