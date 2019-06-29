package com.Sports.controllers;

import com.Sports.models.Student;
import com.Sports.services.Auth;
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

@WebServlet("/students")
public class StudentController extends HttpServlet {

    private DatabaseService dbService = DatabaseService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject studentJSON = JsonService.getFromRequest(req);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Student student = new Student(studentJSON);
        boolean isPresent = Student.checkIfExists(student);

        JSONObject responseJSon = null;
        if(isPresent){
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            responseJSon = new JSONObject("{'message':'Student already exists'}");

        }else{

            boolean saved  = dbService.getStudentRepository().save(student);

            if(saved){
                String password = student.getRegNo();
                if(studentJSON.has("password")){
                    password = studentJSON.getString("password");
                }
                Auth.createAccount("student",student.getRegNo(),password);
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idArray[] = req.getParameterValues("id");
        if(idArray != null){
            getSingle(idArray[0],resp);
        }else{
            getAll(resp);
        }
    }

    private void getSingle(String id, HttpServletResponse resp) {
        PrintWriter out =null;
        try{
            out = resp.getWriter();
            Optional<Student> optionalStudent = dbService.getStudentRepository().getById(id);
            if(optionalStudent.isPresent()){
                out.write(new JSONObject(optionalStudent.get()).toString());
                out.flush();
                out.close();
            }else{
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write(new JSONObject("{'message':'Error fetching'}").toString());
                out.flush();
                out.close();
            }
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write(new JSONObject("{'message':'Error fetching '}").toString());
            out.flush();
            out.close();
        }
    }

    private void getAll(HttpServletResponse resp){
        PrintWriter out = null;
        try{
            ArrayList<Student> students = dbService.getStudentRepository().getAll();
            out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            JSONArray studentArray = new JSONArray(students);
            out.write(studentArray.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + " getting all  students");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSon = new JSONObject("{'message':'Internal error'}");
            out.write(responseJSon.toString());
            out.flush();
            out.close();

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try{
            out = resp.getWriter();
            JSONObject studentJSON = JsonService.getFromRequest(req);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Student student = new Student(studentJSON);

            boolean saved  = dbService.getStudentRepository().save(student);
            JSONObject responseJSon;
            if(saved){
                responseJSon = new JSONObject("{'status': 'success'}");
            }else{
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseJSon = new JSONObject("{'message':'Error Saving'}");
            }

            out.write(responseJSon.toString());
            out.flush();
            out.close();
        }catch (Exception e){
                System.out.println(e.getMessage() + " update student");

                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                JSONObject responseJSon = new JSONObject("{'message':'Internal error'}");
                out.write(responseJSon.toString());
                out.flush();
                out.close();

        }
    }
}
