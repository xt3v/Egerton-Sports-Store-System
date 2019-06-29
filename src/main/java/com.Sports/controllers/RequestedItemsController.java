package com.Sports.controllers;

import com.Sports.models.RequestedItems;
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

@WebServlet({"/requesteditems","/requestitems"})
public class RequestedItemsController  extends HttpServlet {
    DatabaseService databaseService = DatabaseService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out  = null;
        try {
            out = resp.getWriter();
            JSONObject jsonObject = JsonService.getFromRequest(req);
            RequestedItems requestedItems = new RequestedItems(-1,jsonObject.getInt("gameId"),jsonObject.getJSONArray("items").toString());
            if(!databaseService.getRequestItemsRepository().save(requestedItems)){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                JSONObject responseJSon = new JSONObject("{'message':'Error Saving'}");
                out.write(responseJSon.toString());
                out.flush();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSon = new JSONObject("{'message':'Error Saving'}");
            out.write(responseJSon.toString());
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try{
            out = resp.getWriter();
            JSONArray jsonArray = new JSONArray();
            databaseService.getRequestItemsRepository().getAll()
                    .forEach(g->{

                        JSONObject obj = new JSONObject(g.toString());
                        jsonArray.put(obj);
                    });
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.write(jsonArray.toString());
            out.flush();
            out.close();
        }catch (Exception e){
            System.out.println(e.getMessage() +"  request");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write(new JSONObject("{'message':'Request not successful'}").toString());
            out.flush();
            out.close();
        }
    }
}
