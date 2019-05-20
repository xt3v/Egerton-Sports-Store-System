package com.Sports.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    private static DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

    public static boolean checkLogin(String username,String password){
        try {
            String sql = "SELECT * FROM users WHERE role = ? AND password = ?";
            PreparedStatement statement = databaseHandler.getConn().prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static String setSessionKey(String role){
//
//    }
}
