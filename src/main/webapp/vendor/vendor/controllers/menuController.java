package controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import services.ViewService;

public class menuController {
    @FXML Button addProdBtn;
    @FXML Button addCusBtn;
    @FXML Button  posBtn;
    @FXML Button viewSalesBtn;
    @FXML Button  stockBtn;
    @FXML Button  viewDebtorsBtn;

    @FXML void goToView(Event e){
        ViewService vs = ViewService.getViewService();

         if(e.getSource().equals(addProdBtn)){
               vs.changeScene("../views/addproduct.fxml");
         }else if(e.getSource().equals(addCusBtn)){
             vs.changeScene("../views/addcustomer.fxml");
         }else if(e.getSource().equals(posBtn)){
             vs.changeScene("../views/pos.fxml");
         }else if(e.getSource().equals(viewSalesBtn)){
             vs.changeScene("../views/viewsales.fxml");
         }else if(e.getSource().equals(stockBtn)){
             vs.changeScene("../views/displaystock.fxml");
         }else{
             vs.changeScene("../views/viewdebtors.fxml");
         }
    }
}
