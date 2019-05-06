package com.Sports.repositories;

import com.Sports.models.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class FieldRepository implements Repository<Integer, Field> {


    @Override
    public Optional<Field> getById(Integer id) {
        Field field = null;
        try{
            String sql = "Select * FROM fields WHERE fieldId  = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                field = new Field(rs.getString("fieldName"),rs.getInt("fieldId"),rs.getInt("sportId"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "  filedRepo");
        }
        Optional<Field> optionalField = Optional.ofNullable(field);
        return optionalField;
    }

    @Override
    public ArrayList<Field> getAll() {
        ResultSet rs = db.executeQuery("SELECT * FROM fields");
        return createList(rs);
    }

    private ArrayList<Field> createList(ResultSet rs) {
        ArrayList<Field> list = new ArrayList<>();
        try{
            while (rs.next()){
                Field field = field = new Field(rs.getString("fieldName"),rs.getInt("fieldId"),rs.getInt("sportId"));
                list.add(field);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" create field");
        }
        return list;
    }

    @Override
    public ArrayList<Field> queryList(PreparedStatement stmt) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            return createList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" field query list");
        }
        return null;
    }

    @Override
    public ResultSet querySet(PreparedStatement statement) {

        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer identifier) {
        try {
            String sql = "DELETE FROM fields WHERE fieldId = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setInt(1,identifier);
            int rs = stmt.executeUpdate();
            if(rs == 1)return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean save(Field entry) {
        Field field = entry;

        Optional<Field> optionalField = getById(field.getFieldId());

        if(optionalField.isPresent()){
            try{
                String sql = "UPDATE fields SET fieldName = ? , sportId = ? WHERE fieldId = ?";
                PreparedStatement stmt = db.getConn().prepareStatement(sql);
                stmt.setString(1,field.getFieldName());
                stmt.setInt(2,field.getSportId());
                stmt.setInt(3,field.getFieldId());
                int rs = stmt.executeUpdate();
                if(rs == 1)return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        try{
            String sql = "INSERT INTO fields(fieldName,sportId) VALUES(?,?)";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1,field.getFieldName());
            stmt.setInt(2,field.getSportId());
            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


}
