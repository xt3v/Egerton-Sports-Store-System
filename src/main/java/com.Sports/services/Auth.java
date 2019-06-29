package com.Sports.services;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    private static DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    private static DatabaseService databaseService = DatabaseService.getInstance();

    public static boolean checkLogin(String role,String password,String loginId){
        try {
            String sql;
            if(role.equals("coordinator") || role.equals("storekeeper")) {
                System.out.println("Here");
                sql = "SELECT * FROM users WHERE role = ?";
            }else{
                sql = "SELECT * FROM users WHERE loginId = ?";
            }
            PreparedStatement statement = databaseHandler.getConn().prepareStatement(sql);
            statement.setString(1,loginId);
            ResultSet rs = statement.executeQuery();

            if(!rs.next()){
                return false;
            }
            System.out.println("got "+role);
            return confirmPassword(rs.getString("password"),password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean confirmPassword(String encryptedpass, String password) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(),encryptedpass);
        System.out.println(encryptedpass);
        System.out.println(password);
        System.out.println(hashPassWord(password));
        System.out.println(result.verified);
        return result.verified;
    }


     public static void createAccount(String role,String loginId,String password){
        try {
            String hashPassword = hashPassWord(password);
            String sql = "INSERT INTO users(role,password,loginId) VALUES(?,?,?)";
            PreparedStatement stmt = databaseHandler.getConn().prepareStatement(sql);
            stmt.setString(1,role);
            stmt.setString(2,hashPassword);
            stmt.setString(3,loginId);
            stmt.execute();
        }catch (Exception e){
            System.out.println(e.getMessage() + "create acccount");
        }
     }

    private static String hashPassWord(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }


    public static boolean changePassword(String newpassword,String userId) {
        int rs = 0;
        try{
            String sql = "UPDATE users SET password = ? WHERE loginId = ?";
            PreparedStatement stmt = databaseHandler.getConn().prepareStatement(sql);
            stmt.setString(1,hashPassWord(newpassword));
            stmt.setString(2,userId);
            rs = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Auth change password");
        }
        return rs == 1;
    }

    public static boolean logout(String userId) {
       return databaseService.getSessionRepository().deleteByUser(userId);
    }
}
