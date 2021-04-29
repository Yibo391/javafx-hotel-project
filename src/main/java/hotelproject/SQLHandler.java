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

  public class SQLHandler {

    public static Connection getLink() throws Exception {
      Connection conn;
      conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
      return conn;
    }

    public static boolean insert(String table, String[] list) {
      Connection conn;
      System.out.println(list);
      if(table.equals("room")){
        String values = list[0]+","+list[1] + "," + list[2]+","+"'"+list[3]+"'"+","+"'"+list[4]+"'";
        String sql = "INSERT INTO room(size, beds, room_number,location,other_info) VALUES("+values+");";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            if(!conn.getAutoCommit()){
                conn.commit();
            }
        return true;

        } catch(SQLException e){
          System.out.println(e);
            return false;
        }
      } else if(table.equals("user")){
        String values = "\"" + list[0]+ "\"" + ","+"\"" + list[2]+ "\"" + "," + "\"" + list[1]+ "\"";
        String sql = "INSERT INTO user(account, identification, password) VALUES("+ values +");";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            if(!conn.getAutoCommit()){
                conn.commit();
            }
        return true;

        } catch(SQLException e){
          System.out.println(e);
            return false;
        }
      } else if(table.equals("booking")){
        String values = "\"" + list[0]+ "\"" + ","+"\"" + list[1]+ "\"" + "," + "\"" + list[2] + "\"" +  "," + false;
        System.out.println(values);
        String sql = "INSERT INTO bookings(room, bFrom, bTo, paid) VALUES("+ values +");";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            if(!conn.getAutoCommit()){
                conn.commit();
            }
        return true;

        } catch(SQLException e){
          System.out.println(e);
            return false;
        }

      }
      return false;
    }

    public static boolean update(String table, String[] list) {
      Connection conn;
      System.out.println(list);
      if(table.equals("user")){
        String sql = "UPDATE user SET account = ?, password = ? WHERE account = ?";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,list[0]);
            stmt.setString(2,list[1]);
            stmt.setString(3,Controller.getUsername());
            stmt.executeUpdate();
            if(!conn.getAutoCommit()){
                conn.commit();
            }
        return true;

        } catch(SQLException e){
          System.out.println(e);
            return false;
        }
      } else if(table.equals("room")){
        String sql = "UPDATE room SET size = ?, beds = ?, location = ?, other_info = ? WHERE room_number = ?";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println(list);
            stmt.setString(1,list[0]);
            stmt.setString(2,list[1]);
            stmt.setString(3,list[3]);
            stmt.setString(4,list[4]);
            stmt.setString(5,list[2]);
            System.out.println(stmt);
            stmt.executeUpdate();
            if(!conn.getAutoCommit()){
                conn.commit();
            }
        return true;

        } catch(SQLException e){
          System.out.println(e);
            return false;
        }
      }
      return false;
    }
}
