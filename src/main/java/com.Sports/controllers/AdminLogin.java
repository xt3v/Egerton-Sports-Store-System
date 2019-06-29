
package com.Sports.controllers;
import com.Sports.models.SessionItem;
import com.Sports.models.Student;
import com.Sports.services.Auth;
import com.Sports.services.DatabaseService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


@WebServlet({"/adminlogin","/adminLogin"})
public class AdminLogin extends HttpServlet{
     DatabaseService dbService = DatabaseService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getCookies() != null && req.getCookies().length > 3){
            Optional<String> optional = Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals("egersportsession"))
                    .map(Cookie::getValue)
                    .findAny();

            if(optional.isPresent()){

                Optional<SessionItem> sessionItemOptional = dbService.getSessionRepository().getByValue(optional.get());
                if(sessionItemOptional.isPresent()){
                    SessionItem sessionItem = sessionItemOptional.get();
                    if(sessionItem.getRole().equals("storekeeper")){
                        resp.sendRedirect("storekeeper.jsp");
                    }else{
                        resp.sendRedirect("coordinator.jsp");
                    }
                }else{
                    resp = removeCookies(resp);
                    resp.sendRedirect("/adminLogin.jsp");
                }
            }else{
                resp.sendRedirect("/adminLogin.jsp");
            }
        }else{
            resp.sendRedirect("/adminLogin.jsp");
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("inputName");
        String password = req.getParameter("inputPassword");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminLogin.jsp");
        if(null == role || password == null || role.trim().equals("") || password.trim().equals("")){
            req.setAttribute("error",true);
            req.setAttribute("message","Please enter username and password");
            rd.forward(req,resp);
        }else{
            boolean valid = Auth.checkLogin(role,password,role);
            if(valid){
                /*TODO change the storekeeper and coordinator pages to jsp files get csrf token and send */

                if(role.equals("storekeeper")){
                    resp = setLoginCookies(resp,"storekeeper","storekeeper");
                    resp.sendRedirect("storekeeper.jsp");

                }else{
                    resp = setLoginCookies(resp,"coordinator","coordinator");
                    resp.sendRedirect("coordinator.jsp");
                }

            }else{
                req.setAttribute("error",true);
                req.setAttribute("message","Invalid user or password");
                rd.forward(req,resp);
            }
        }

    }

    private HttpServletResponse setLoginCookies(HttpServletResponse resp, String id, String role) {

        String value = System.nanoTime() + "id";
        Cookie sessionvalueset = new Cookie("egersportsession", value);
        sessionvalueset.setMaxAge(60*60*4);
        resp.addCookie(sessionvalueset);

        Cookie sessionusername = new Cookie("egersportusername", role);
        sessionusername.setMaxAge(60*60*4);
        resp.addCookie(sessionusername);

        Cookie sessionuserid = new Cookie("egersportuserid", id);
        sessionuserid.setMaxAge(60*60*4);
        resp.addCookie(sessionuserid);

        Cookie sessionuserrole = new Cookie("egersportuserrole", role);
        sessionuserid.setMaxAge(60*60*4);
        resp.addCookie(sessionuserrole);

        SessionItem sessionItem = new SessionItem(-1,value,role,id);
        dbService.getSessionRepository().save(sessionItem);
        return resp;
    }

    private HttpServletResponse removeCookies(HttpServletResponse resp) {
        Cookie sessionvalueremove = new Cookie("egersportsession", "");
        sessionvalueremove.setMaxAge(0);
        resp.addCookie(sessionvalueremove);

        Cookie sessionusername = new Cookie("egersportusername", "");
        sessionusername.setMaxAge(0);
        resp.addCookie(sessionusername);

        Cookie sessionuserid = new Cookie("egersportuserid", "");
        sessionuserid.setMaxAge(0);
        resp.addCookie(sessionuserid);

        Cookie sessionuserrole = new Cookie("egersportuserrole", "");
        sessionuserid.setMaxAge(60);
        resp.addCookie(sessionuserrole);

        return resp;
    }
}