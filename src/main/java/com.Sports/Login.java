
package com.Sports;
import com.Sports.services.Auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class Login extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("inputName");
        String password = req.getParameter("inputPassword");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");;
        if(null == role || password == null || role.trim().equals("") || password.trim().equals("")){
            req.setAttribute("error",true);
            req.setAttribute("message","Please enter username and password");
            rd.forward(req,resp);
        }else{
            boolean valid = Auth.checkLogin(role,password);
            if(valid){
                /*TODO change the storekeeper and coordinator pages to jsp files get csrf token and send */
                if(role.equals("storekeeper")){
                    resp.sendRedirect("storekeeper.html");
                }else{
                    resp.sendRedirect("coordinator.html");
                }
            }else{
                req.setAttribute("error",true);
                req.setAttribute("message","Invalid user or password");
                rd.forward(req,resp);
            }
        }


    }
}