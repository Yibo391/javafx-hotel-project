package hotelproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

  public class Validator {

    public static String checkLogin(Map<String, String> info) {
      boolean loginSuccess = false;
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet resultSet = null;
      String id = null;
      try {
        Class.forName("com.mysql.jdbc.Driver");
        String name = info.get("loginName");
        String pwd = info.get("loginpwd");
        try{
          conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root");
        } catch (Exception SQLSyntaxErrorException){
          Connection tconn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=root");
          ps = tconn.prepareStatement("create database hoteldb");
          ps.executeUpdate();
          tconn.close();
          conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root");
          ps = conn.prepareStatement("create table user(account TEXT, password TEXT, identification TEXT)");
          ps.executeUpdate();
        }
  
        String sql = "select account,password,identification from user where account=? and password=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2,pwd);
        resultSet = ps.executeQuery();
        while (resultSet.next()) {
          id = resultSet.getString(3);
          loginSuccess = true;
        }
  
      } catch (Exception e) {
        System.out.println(e);
      }finally {
        try {
          conn.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
        try {
          ps.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
        try {
          resultSet.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }
      return id;
    }
}
