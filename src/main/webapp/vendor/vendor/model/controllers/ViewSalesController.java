package controllers;

import javafx.fxml.FXML;
import services.ViewService;

public class ViewSalesController {
    @FXML
    void goToMenu(){
        ViewService.getViewService().goToMenu();
    }
}
