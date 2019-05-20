package com.Sports.controllers;


import com.Sports.models.Coach;
import com.Sports.models.Student;
import com.Sports.services.DatabaseService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/search")
public class SearchController extends HttpServlet {
    DatabaseService db = DatabaseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idArray[] = req.getParameterValues("borrowId");
        if(idArray != null){
            searchBorrower(idArray[0],resp);
        }else{
            //
        }
    }

    private void searchBorrower(String id, HttpServletResponse resp) {
       try{
           PrintWriter out = resp.getWriter();
           resp.setContentType("application/json");
           resp.setCharacterEncoding("UTF-8");
           Optional<Coach> optionalCoach = db.getCoachRepository().getById(id);

           if(optionalCoach.isPresent()){
               Coach coach = optionalCoach.get();
               String coachJSON = "{'name': '" +
                       coach.getName() +
                       "','id':'" +
                       coach.getEmployeeId() +
                       "','status':'coach'}";

               out.write(new JSONObject(coachJSON).toString());
           }else{
               Optional<Student> optionalStudent = db.getStudentRepository().getById(id);

               if(optionalStudent.isPresent()){
                   Student student = optionalStudent.get();

                   String studentJSON = "{'name': '" +
                           student.getName() +
                           "','id':'" +
                           student.getRegNo() +
                           "','status':'" +
                           ( student.isCaptain()? "captain" : "student" ) +
                           "'}";
                   out.write(new JSONObject(studentJSON).toString());
               }else{
                   resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                   JSONObject responseJSON = new JSONObject("{'message':'Not Registered'}");
                   out.write(responseJSON.toString());
               }
           }

           out.flush();
           out.close();
       } catch (IOException e) {
           System.out.println(e.getMessage()+ " search borrower");
       }


    }
}
