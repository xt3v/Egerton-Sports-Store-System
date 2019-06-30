package controllers;

import dataFormats.MessageToPOS;
import dataFormats.ProductsJson;
import TableDetails.ProductDetails;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Product;
import models.Sale;
import services.DatabaseService;
import services.ViewService;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.*;

public class PosController implements Initializable {
    @FXML TextField sku;
    @FXML Label productname;
    @FXML Label productprice;
    @FXML TableView<ProductDetails> posTable;
    @FXML TableColumn<ProductDetails, String> skuColumn;
    @FXML TableColumn<ProductDetails, String> nameColumn;
    @FXML TableColumn<ProductDetails, String> priceColumn;
    @FXML TableColumn<ProductDetails, String> subTotalColumn;
    @FXML TableColumn<ProductDetails, String> piecesColumn;
    @FXML TextField piecesSelected;
    @FXML Button addToCartBtn;
    @FXML Label grandtotal;
    @FXML Label balance;
    @FXML TextField cashgiven;

    private ObservableList<ProductDetails> data;  //store the producst to show on db
    private Product selectedProduct;  //Set the selected product used to populate table
    private DatabaseService db = null;  //handle db transactions
    private ViewService  vs;

    //changes to main menu
    @FXML void goToMenu() {
        vs.goToMenu();
    }

    //search for a product using sku
    @FXML void searchSku() {
       try {
           //search for the product by sku and populate labels
           if(!sku.getText().trim().equals("")){
               //Enable add button and pieces fieled
               piecesSelected.setDisable(false);
               addToCartBtn.setDisable(false);

               Product product = (Product) db.getProductDAO().getByIdentifier(sku.getText()); //get product
               productname.setText(product.getName());   //set product name to label
               productprice.setText(String.format("%.3f", product.getPrice()));        //set product price to label
               selectedProduct = product;  //set selected product

               //check if product is availabel
               if(product.getQuantity() < 1){
                   //if not available disable addtocart and pieces field
                   piecesSelected.setDisable(true);
                   addToCartBtn.setDisable(true);
               }
           }

       }catch (Exception e){
           System.out.println(e.getMessage());
       }

    }

    /*
      *init method for the controller
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set the columns data source
        skuColumn.setCellValueFactory(new PropertyValueFactory<>("sku"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        piecesColumn.setCellValueFactory(new PropertyValueFactory<>("pieces"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        subTotalColumn.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        //initialize observable list
        data = FXCollections.observableArrayList();
        posTable.setItems(null);
        posTable.setItems(data);   //add list to table

        db = DatabaseService.getInstance();   //get instance of database service
        vs = ViewService.getViewService();  //get instance of view service
    }

    /*
      called when want to add item to cart
     */
    @FXML void addProduct() {
        if(!piecesSelected.getText().trim().equals("")){    //check if pieces not empty
            int pieces = Integer.parseInt(piecesSelected.getText());  //get selected pieces

            //check if pieces selected are available
            if (null == selectedProduct)return;
             if(pieces < selectedProduct.getQuantity()){
                float price = selectedProduct.getPrice();   ////get price
                float subtotal = price * pieces;  //calculate the subtotal
                data.add(new ProductDetails(selectedProduct.getSku(), selectedProduct.getName(), String.format("%.3f", price),
                        String.format("%d", pieces), String.format("%.3f", subtotal)));  //add product details to data list
                 calculateTotal(subtotal);   //calculate GrandTotal
                 clearSelectedItem();
            }
        }
    }

    //find balance
    @FXML void calculateBalance(){
        try{
            Float total =  Float.parseFloat(grandtotal.getText());   //get total from label
            Float amountgiven = Float.parseFloat(cashgiven.getText()); //get cashgiven
            Float bal = amountgiven - total;    //calculate balance
            balance.setText(String.format("%.3f",bal));     //update label
        }catch (Exception e){
            System.out.println("Error !!");
        }

    }


    //resets selected item to null and clears its data
    private void clearSelectedItem() {
        productname.setText("");   //clear product name to label
        productprice.setText("");        //clear  product price to label
        selectedProduct = null;  //clear selected product
        piecesSelected.setText("");  //clear selected pieces
    }

    //Gets the grand total of products
    private void calculateTotal(float subtotal) {
        Float total = Float.parseFloat(grandtotal.getText());   //get total from label
        total+=subtotal;       //add new subtotal
        grandtotal.setText(String.format("%.3f",total));  //update label
    }

    //Action for check out button
    //also checks if user is to pay with credit
    @FXML void checkOut(ActionEvent actionEvent) {
        float bal = Float.parseFloat(balance.getText());

        if(bal < 0){   //pay with credit

            try {
                //show pay with credit modal
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/paybycredit.fxml"));
                Parent root =  loader.load();
                PayByCreditController con = loader.getController();
                Stage stage = new Stage();
                con.setTotal(bal,stage);
                stage.setScene(new Scene(root));
                stage.showAndWait();

                //get message from modal
                MessageToPOS pos = (MessageToPOS) vs.getMessage("pos");
                if(null != pos){
                    if(pos.isSuccess()){
                        addSaleToDB(pos.getCredit());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            //pay normal
             addSaleToDB(0);
        }


    }


    //add sale to db
    private void addSaleToDB(float credit) {
        Date date = new  Date(System.nanoTime());
        Time time = new Time(date.getTime());
        
        JsonArray json = createProductJson();    //create the json product array
        Sale sale = new Sale(date,json,time,Float.parseFloat(grandtotal.getText()),credit,false);

        db.getSaleDAO().save(sale);

        //clear fields
         clearFields();
    }


    //creates the prodcurs json Array
    private JsonArray createProductJson() {
        JsonArray jsonRay = new JsonArray();
        for (ProductDetails details : data){
              ProductsJson p = new ProductsJson();
              p.sku = details.getSku();
              p.amount =  Integer.parseInt(details.getPieces());
              JsonElement json = new JsonParser().parse(new Gson().toJson(p));
              jsonRay.add(json);

              //reduce from stock
               reduceStock(details.getSku(),Integer.parseInt(details.getPieces()));
        }
        return jsonRay;
    }

    private void reduceStock(String sku, int pieces) {
        Product product = (Product) db.getProductDAO().getByIdentifier(sku);
         db.getProductDAO().save(product);
    }


    //clear the inut fileds
    private void clearFields() {
        sku.setText("");
        productprice.setText("");
        productname.setText("");
        posTable.setItems(null);
        data = FXCollections.observableArrayList();
        posTable.setItems(data);

         grandtotal.setText("0");
       balance.setText("0");
       cashgiven.setText("0");

       piecesSelected.setText("0");

    }
}
