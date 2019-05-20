package com.Sports.controllers;

import com.Sports.models.Item;
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

@WebServlet("/items")
public class ItemController extends HttpServlet {
    private DatabaseService db = DatabaseService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idArray[] = req.getParameterValues("sportId");

        if(idArray != null){
            getItemsBySport(idArray[0],resp);
        }
    }

    private void getItemsBySport(String sportIdString, HttpServletResponse resp) {
       try{
           int sportId = Integer.parseInt(sportIdString);
           ArrayList<Item> items = db.getItemRepository().getBySportId(sportId);
           PrintWriter out = resp.getWriter();
           resp.setContentType("application/json");
           resp.setCharacterEncoding("UTF-8");
           JSONArray sportsArray = new JSONArray(items);
           out.write(sportsArray.toString());
           out.flush();
           out.close();
       } catch (IOException e) {
           System.out.println(e.getMessage() + "get item by sport in controller");
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        JSONObject itemJSON = JsonService.getFromRequest(req);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Item item  = new Item(itemJSON);

        boolean isPresent = Item.checkIfExists(item);
        //TODO check if the coach and captain exists
        JSONObject responseJSon = null;

        if(isPresent){
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            responseJSon = new JSONObject("{'message':'Item already exists'}");

        }else{
            boolean saved  = db.getItemRepository().save(item);
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
