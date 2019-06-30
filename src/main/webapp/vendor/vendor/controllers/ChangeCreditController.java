package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Customer;
import services.DatabaseService;

public class ChangeCreditController {
   @FXML Label customername;
    @FXML Label currentcredit;
    @FXML TextField paidcredit;
    @FXML Label newcredit;

    private Customer customer;
    private Stage stage;

    public void setCustomer(Customer cus, Stage stage) {
        customer = cus;
        this.stage = stage;

        //set the labels
        customername.setText(cus.getName());
        currentcredit.setText(String.format("%.3f",cus.getBalance()));
        paidcredit.setText("0");
        newcredit.setText("0");

    }


  @FXML void reduceCredit(ActionEvent actionEvent) {
        customer.setBalance(Float.parseFloat(newcredit.getText()));
      DatabaseService db = DatabaseService.getDatabaseService();
      db.getCustomerDAO().save(customer);
      exit();
    }

    //update the newcredit label
   @FXML void calculateNewCredit(){
       float curr = customer.getBalance();
       float paid = Float.parseFloat(paidcredit.getText().trim());
       float nwcred = curr - paid;
       newcredit.setText(String.format("%.3f",nwcred));
   }
   @FXML void exit() {
        stage.close();
    }
}
