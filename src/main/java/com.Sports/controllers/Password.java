package com.Sports.controllers;

import com.Sports.models.SessionItem;
import com.Sports.services.Auth;
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
import java.util.Optional;

@WebServlet("/password")
public class Password  extends HttpServlet {
     DatabaseService dbservice = DatabaseService.getInstance();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jsonObject = JsonService.getFromRequest(req);
        String oldPassword = jsonObject.getString("oldpassword");
        String newPassword = jsonObject.getString("newpassword");
        String sessionValue = jsonObject.getString("session");

        Optional<SessionItem> optionalSessionItem = dbservice.getSessionRepository().getByValue(sessionValue);
        PrintWriter out = resp.getWriter();

        JSONObject responseJSon = null;
        if(optionalSessionItem.isPresent()){
            SessionItem sessionItem = optionalSessionItem.get();
            if(Auth.checkLogin(sessionItem.getRole(),oldPassword,sessionItem.getUserId())){
                if(Auth.changePassword(newPassword,sessionItem.getUserId())){
                    responseJSon = new JSONObject("{'success':'success'}");
                }else{
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    responseJSon = new JSONObject("{'message':'Error Changing password'}");
                }
            }else{
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseJSon = new JSONObject("{'message':'Password is incorrect !'}");
            }
        }else{
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseJSon = new JSONObject("{'message':'Authentication error'}");
        }

        out.write(responseJSon.toString());
        out.flush();
        out.close();
    }
}
