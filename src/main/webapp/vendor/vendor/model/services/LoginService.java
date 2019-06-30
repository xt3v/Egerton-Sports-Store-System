package services;

import models.Cashier;
import java.util.ArrayList;

public class LoginService {
     private Cashier cashier;
     private static LoginService loginService = null;

     private  LoginService(){

     }

     public static LoginService  getLoginSerice(){
         if (loginService == null) loginService = new LoginService();
         return loginService;
     }

     public boolean login(String username,String password){
         DatabaseService db = DatabaseService.getInstance();
         ArrayList<Cashier>list =  db.getCashierDAO().queryList("SELECT * FROM cashiers WHERE username = '"+username+"' ");
         if (list.size() > 0){
              if(list.get(0).getPassword().equals(password)){
                   return true;
              }
         }
         return false;
     }

    public Cashier getCashier() {
        return cashier;
    }
}
