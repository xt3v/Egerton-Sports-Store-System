package DAO;


import models.Admin;
import models.Shopkeeper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 Data Access Object for ShopKeeper  class
 */
public class ShopkeeperDAO implements DAO {

    @Override
    public Object getByIdentifier(Object identifier) {
        Shopkeeper shopkeeper = null;
        try{
            String  id = (String)identifier;  //type cast to datatype of identifier
            //query db
            ResultSet rs = db.executeQuery("SELECT  * FROM products WHERE sku = '"+id+"'");

            //if result has been gotten create customer
            if(rs.next()){
               if(rs.getBoolean("admin") ){
                   shopkeeper = new Admin(rs.getString("name"),rs.getString("employee_id"),
                           rs.getString("username"),rs.getString("password"));
               }else{
                   shopkeeper = new Shopkeeper(rs.getString("name"),rs.getString("employee_id"),
                           rs.getString("username"),rs.getString("password"));
               }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shopkeeper;
    }

    @Override
    public ArrayList getAll(){
        ResultSet rs = db.executeQuery("SELECT  * FROM shopkeepers");
        return createList(rs);
    }


    @Override
    public ArrayList queryList(String sql) {
            ResultSet rs = db.executeQuery(sql);
            return createList(rs);
    }

    @Override
    public ResultSet querySet(String sql) {
        //queries and returns resulset as is
        return db.executeQuery(sql);
    }


    @Override
    public boolean deleteByIdentifier(Object identifier) {
        String  id = (String)identifier;
        //delet entry with the identifier given
        int rs =  db.executeAction("DELETE FROM products WHERE employee_id  = '"+id+"'");

        //check if successfull
        if (rs == 1) return true;
        return false;
    }

    @Override
    public boolean save(Object entry) {
      Shopkeeper shopkeeper = (Shopkeeper) entry;
      int isAdmin = 0;
      if(shopkeeper instanceof Admin){
          isAdmin = 1;
      }

        //sql to be executed
        String sql = "INSERT INTO shopkeepers(name,username,password,employee_id,admin)"+
                "VALUES('"+shopkeeper.getName()+"','"+shopkeeper.getUsername()+"','"+shopkeeper.getPassword()+"',"+
                "'"+shopkeeper.getEmployee_id()+"','"+isAdmin+"') ";
        int rs = db.executeAction(sql);

        //check if successfull
        if(rs == 1)return true;
        return false;
    }

    //loops throught resultset and creates arraylist
    private ArrayList createList(ResultSet rs) {
        ArrayList<Shopkeeper> list  = new ArrayList<>();
        try{
            while (rs.next()){
                Shopkeeper shopkeeper;
                if(rs.getBoolean("admin") ){
                    shopkeeper = new Admin(rs.getString("name"),rs.getString("employee_id"),
                            rs.getString("username"),rs.getString("password"));
                }else{
                    shopkeeper = new Shopkeeper(rs.getString("name"),rs.getString("employee_id"),
                            rs.getString("username"),rs.getString("password"));
                }
                list.add(shopkeeper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
