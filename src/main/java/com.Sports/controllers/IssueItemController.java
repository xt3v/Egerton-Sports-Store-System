package com.Sports.controllers;

import com.Sports.models.BorrowedItem;
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
import java.time.LocalDate;

@WebServlet("/issueitems")
public class IssueItemController extends HttpServlet {

    private DatabaseService db = DatabaseService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject responseJSon = null;
        PrintWriter out = null;
        try{
            JSONObject issueItemJSON = JsonService.getFromRequest(req);

            out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            //add to students
            String borrowerId = issueItemJSON.getString("borrowerId");
            String status = issueItemJSON.getString("status");
            if(db.getBorrowedItemRepository().getByBorrowerId(borrowerId).size() > 0 && !(status.equals("coach")||status.equals("captain")) ){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseJSon = new JSONObject("{'message':'Has not returned previous items'}");
            }else{
                JSONArray itemsArray = issueItemJSON.getJSONArray("items");
                for(int i = 0;i<itemsArray.length();i++){
                    JSONObject itemJSON = itemsArray.getJSONObject(i);
                    BorrowedItem borrowedItem = new BorrowedItem(itemJSON.getInt("itemId"), LocalDate.now(),-1,
                            itemJSON.getInt("amount"),borrowerId);
                    Item item = db.getItemRepository().getById(itemJSON.getInt("itemId")).get();
                    item.setQuantity(item.getQuantity()-itemJSON.getInt("amount"));
                    db.getItemRepository().save(item);
                    db.getBorrowedItemRepository().save(borrowedItem);
                }
                responseJSon = new JSONObject("{'success':'successfully added'}");
            }
        }catch (Exception e){
            System.out.println(e.getMessage() + " issueitems post cnotroller");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseJSon = new JSONObject("{'message':'Error Saving'}");
        }finally {
            out.write(responseJSon.toString());
            out.flush();
            out.close();
        }
    }
}
