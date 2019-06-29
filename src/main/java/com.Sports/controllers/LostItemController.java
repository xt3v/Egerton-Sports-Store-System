package com.Sports.controllers;

import com.Sports.models.BorrowedItem;
import com.Sports.models.Item;
import com.Sports.models.LostItem;
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
import java.util.ArrayList;

@WebServlet("/lostitems")
public class LostItemController extends HttpServlet {

    private DatabaseService db = DatabaseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String idArray[] = req.getParameterValues("id");
            if(idArray != null){
                getItems(idArray[0],resp);
            }
        }


    private void getItems(String id, HttpServletResponse resp) {
        PrintWriter out = null;
        try{
            ArrayList<LostItem> lostItems = db.getLostItemRepository().getByBorrowerId(id);
            out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            JSONArray itemArray = new JSONArray();
            lostItems.forEach(bi->{
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
            System.out.println(e.getMessage() +" lost items in borroweditemscontroller");
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try{
             out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            JSONObject lostItemJSON =  JsonService.getFromRequest(req);
            LostItem lostItem = new LostItem(lostItemJSON);
            if(lostItem.getQuantity() == 0){
                db.getLostItemRepository().deleteById(lostItem.getLostItemId());
            }else{
                db.getLostItemRepository().save(lostItem);
            }
            Item item = lostItem.getItem();
            System.out.println("HEHEHEHEHEHEHE "+item.getQuantity()+" "+lostItemJSON.getInt("recoveredAmount"));
            item.setQuantity(item.getQuantity() + lostItemJSON.getInt("recoveredAmount"));
            db.getItemRepository().save(item);
            out.close();
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSON = new JSONObject("{'message':'"+e.getMessage()+"'}");
            out.write(responseJSON.toString());
            out.flush();
            out.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            JSONObject lostItemJSON = JsonService.getFromRequest(req);
            int lostAmount = lostItemJSON.getInt("lostAmount");
            LostItem lostItem = new LostItem(lostItemJSON.getInt("itemId"),LocalDate.now(),-1,lostAmount,lostItemJSON.getString("borrowerId"));

            BorrowedItem borrowedItem = db.getBorrowedItemRepository().getById(lostItemJSON.getInt("borrowedItemId")).get();
            db.getLostItemRepository().save(lostItem);
            borrowedItem.setQuantity(borrowedItem.getQuantity() - lostAmount);

            if(borrowedItem.getQuantity() == 0)db.getBorrowedItemRepository().deleteById(borrowedItem.getBorrowedItemId());
            else db.getBorrowedItemRepository().save(borrowedItem);
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject responseJSON = new JSONObject("{'message':'"+e.getMessage()+"'}");
            out.write(responseJSON.toString());
            out.flush();
            out.close();
            System.out.println(e.getMessage());
        }


    }
}
