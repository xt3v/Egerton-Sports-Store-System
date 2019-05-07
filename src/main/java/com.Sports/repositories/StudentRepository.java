package com.Sports.repositories;

import com.Sports.models.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class StudentRepository implements Repository<String, Student> {

    @Override
    public Optional<Student> getById(String id) {
        Student student = null;
        try{
            String sql = "Select * FROM students WHERE regNo  = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
              student = new Student(rs.getString("regNo"),rs.getString("name"),rs.getString("residence"),
                      rs.getString("phoneNo"),rs.getBoolean("isCaptain"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "student repo");
        }
        Optional<Student> optionalStudent = Optional.ofNullable(student);
        return optionalStudent;
    }

    @Override
    public ArrayList<Student> getAll() {
        ResultSet rs = db.executeQuery("SELECT * FROM students");
        return createList(rs);
    }

    private ArrayList<Student> createList(ResultSet rs) {
        ArrayList<Student> list = new ArrayList<>();
        try{
            while (rs.next()){
                Student student = new Student(rs.getString("regNo"),rs.getString("name"),rs.getString("residence"),
                        rs.getString("phoneNo"),rs.getBoolean("isCaptain"));
                list.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" create student list");
        }
        return list;
    }

    @Override
    public ArrayList<Student> queryList(PreparedStatement stmt) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            return createList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" student query list");
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
    public boolean deleteById(String identifier) {
        try {
            String sql = "DELETE FROM students WHERE regNo = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1,identifier);
            int rs = stmt.executeUpdate();
            if(rs == 1)return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean save(Student entry) {
        Student student = entry;

        Optional<Student> optionalStudent = getById(student.getRegNo());

        if(optionalStudent.isPresent()){
            try{
                String sql = "UPDATE students SET name = ?,residence = ?, phoneNo = ? , isCaptain = ? WHERE regNo = ?";
                PreparedStatement stmt = db.getConn().prepareStatement(sql);
                stmt.setString(1,student.getName());
                stmt.setString(2,student.getResidence());
                stmt.setString(3,student.getPhoneNo());
                stmt.setBoolean(4,student.isCaptain());
                stmt.setString(5,student.getRegNo());

                int rs = stmt.executeUpdate();
                if(rs == 1)return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        try{
            String sql = "INSERT INTO students(name,residence,phoneNo,isCaptain) VALUES(?,?,?,?)";
            PreparedStatement stmt = db.getConn().prepareStatement(sql);
            stmt.setString(1,student.getName());
            stmt.setString(2,student.getResidence());
            stmt.setString(3,student.getPhoneNo());
            stmt.setBoolean(4,student.isCaptain());

            int rs = stmt.executeUpdate();

            if(rs == 1)return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return false;
    }


}
