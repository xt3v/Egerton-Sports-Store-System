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
import java.util.ArrayList;

@WebServlet("/borroweditems")
public class BorrowedItemsController extends HttpServlet {

    private DatabaseService db = DatabaseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idArray[] = req.getParameterValues("borrowId");
        if(idArray != null){
            getItems(idArray[0],resp);
        }
    }

    private void getItems(String id, HttpServletResponse resp) {
        PrintWriter out = null;
        try{
            ArrayList<BorrowedItem> borrowedItems = db.getBorrowedItemRepository().getByBorrowerId(id);
            out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            JSONArray itemArray = new JSONArray();
            borrowedItems.forEach(bi->{
                JSONObject obj = new JSONObject(bi.toString());
                itemArray.put(obj);
            });
            out.write(itemArray.toString());
            out.flush();
            out.close();

        } catch (IOException e) {

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSON = new JSONObject("{'message':'"+e.getMessage()+"'}");
            out.write(responseJSON.toString());
            out.flush();
            out.close();
            System.out.println(e.getMessage() +" get borrowed items in borroweditemscontroller");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = null;
        try {
            JSONObject returnItemJSON = JsonService.getFromRequest(req);

            out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

             BorrowedItem borrowedItem = new BorrowedItem(returnItemJSON);

             if(borrowedItem.getQuantity() == 0){
                 db.getBorrowedItemRepository().deleteById(borrowedItem.getBorrowedItemId());
             }else{
                 db.getBorrowedItemRepository().save(borrowedItem);
             }
             Item item = borrowedItem.getItem();
             item.setQuantity(item.getQuantity() + returnItemJSON.getInt("returnedAmount"));
             db.getItemRepository().save(item);
             out.close();
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSON = new JSONObject("{'message':'"+e.getMessage()+"'}");
            out.write(responseJSON.toString());
            out.flush();
            out.close();
            System.out.println(e.getMessage() + " change borrowed items");
        }
    }
}

