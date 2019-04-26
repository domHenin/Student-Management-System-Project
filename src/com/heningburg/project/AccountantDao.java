package com.heningburg.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountantDao {
    public static Connection getCon() {
        Connection con = null;
        try {
            class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "", "") //->change later when mysql properly setup
        } catch (Exception e) {
            System.out.println(e);
            return con;
        }
    }

    public static boolean validate(String name, String password) {
        boolean status=false;
        try {
            Connection con=getCon();
            PreparedStatement preparedStatement=con.prepareStatement("select * from feereport accountant where name=? and password=?"); //-> change 'feereport' name
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet rs=preparedStatement.executeQuery();
            status=rs.next();
            con.close();
        }catch (Exception e) { System.out.println(e); }

        return status;
    }

    public static int save(Accountant a) {
        int status=0;
        try {
            Connection con=getCon();
            PreparedStatement preparedStatement=con.prepareStatement("insert into feereport accountant(name, password, email, contactno) values(?,?,?,?)");
            preparedStatement.setString(1, a.getName());
            preparedStatement.setString(2, a.getPassword());
            preparedStatement.setString(3, a.getEmail());
            preparedStatement.setString(4, a.getContactno());
            status=preparedStatement.executeUpdate();
            con.close();
        }
    }
}
