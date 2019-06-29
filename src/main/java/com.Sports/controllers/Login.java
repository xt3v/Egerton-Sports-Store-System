package com.Sports.controllers;


import com.Sports.models.SessionItem;
import com.Sports.models.Student;
import com.Sports.services.Auth;
import com.Sports.services.DatabaseService;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Printable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@WebServlet({"/login"})
public class Login extends HttpServlet {
    private DatabaseService dbService = DatabaseService.getInstance();
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
                  resp.sendRedirect("/home.jsp");
              }else{
                  resp = removeCookies(resp);
                  resp.sendRedirect("/login.jsp");
              }
          }else{
              resp.sendRedirect("/login.jsp");
          }
      }else{
          resp.sendRedirect("/login.jsp");
      }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
        try{

            String loginId = req.getParameter("loginId");
            String password = req.getParameter("passwordLogin");
            String role = "";
            if(dbService.getStudentRepository().getById(loginId).isPresent()){
                role = "student";
                if(dbService.getStudentRepository().getById(loginId).get().isCaptain())role = "captain";
            }else if(dbService.getCoachRepository().getById(loginId).isPresent()){
                role = "coach";
            }else{
                req.setAttribute("error",true);
                req.setAttribute("message","Wrong Username or Password!");
                rd.forward(req,resp);
                return;
            }

            boolean valid = Auth.checkLogin(role,password,loginId);
            System.out.println("Valid "+role+" password "+password +" "+valid);
            if(!valid){
                System.out.println("POPO");
                req.setAttribute("error",true);
                req.setAttribute("message","Wrong Username or Password!");
                rd.forward(req,resp);
                return;
            }
            System.out.println(loginId + " "+ role);
            resp = setLoginCookies(resp,loginId,role);
            resp.sendRedirect("/home.jsp");
        }catch (Exception e){
            System.out.println(e.getMessage());
            req.setAttribute("error",true);
            req.setAttribute("message","Invalid user or password");
            rd.forward(req,resp);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        if(!Auth.logout(userId)){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSon = new JSONObject("{'message':'Not logged out !'}");
            PrintWriter out = resp.getWriter();
            out.write(responseJSon.toString());
            out.flush();
            out.close();
        }
    }

    private HttpServletResponse setLoginCookies(HttpServletResponse resp, String id, String role) {
        String name = "";
        if(role.equals("coach")){
            name = dbService.getCoachRepository().getById(id).get().getName();
        }else{
            Student student = dbService.getStudentRepository().getById(id).get();
            name = student.getName();
            if(student.isCaptain()) role = "captain";
        }
        System.out.println("cookie role "+role);
        System.out.println("Cookie name "+name);
        String value = System.nanoTime() + new Random().nextInt(200) + "";
        Cookie sessionvalueset = new Cookie("egersportsession", value);
        sessionvalueset.setMaxAge(60*60*4);
        resp.addCookie(sessionvalueset);
        System.out.println("1");
        Cookie sessionusername = new Cookie("egersportusername", name.split(" ")[0]);
        sessionusername.setMaxAge(60*60*4);
        resp.addCookie(sessionusername);
        System.out.println("2");
        Cookie sessionuserid = new Cookie("egersportuserid", id);
        sessionuserid.setMaxAge(60*60*4);
        resp.addCookie(sessionuserid);
        System.out.println("3");
        Cookie sessionuserrole = new Cookie("egersportuserrole", role);
        sessionuserid.setMaxAge(60*60*4);
        resp.addCookie(sessionuserrole);
        System.out.println("4");
        SessionItem sessionItem = new SessionItem(-1,value,role,id);
        dbService.getSessionRepository().save(sessionItem);
        System.out.println("5");
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
