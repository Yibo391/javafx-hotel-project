package hotelproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Random;
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
        Random random = new Random();
        Integer rid = 0;
        String check = "SELECT ID FROM bookings";
        try{
          conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
        Statement statement = conn.createStatement();
        ObservableList <String> rlist = FXCollections.observableArrayList();
        ResultSet BookIDList = statement.executeQuery(check);
        while (BookIDList.next()) {
          rlist.add(BookIDList.getString("ID"));
        }
        while(rlist.contains(String.valueOf(rid))){
          rid = random.nextInt(9999);
        }
      } catch(SQLException e){
        System.out.println(e);
      }
        String values = "\"" + list[0]+ "\"" + ","+"\"" + list[1]+ "\"" + "," + "\"" + list[2] + "\"" +  "," + false + "," + "\"" + list[3] + "\"" + "," + "\"" + rid + "\"";
        System.out.println(values);
        String sql = "INSERT INTO bookings(room, bFrom, bTo, paid, Customer, ID) VALUES("+ values +");";
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

      } else if(table.equals("customer")){
        Random random = new Random();
        Integer rid = 0;
        String check = "SELECT ID FROM customer";
        try{
          conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
        Statement statement = conn.createStatement();
        ObservableList <String> rlist = FXCollections.observableArrayList();
        ResultSet CustomerIDList = statement.executeQuery(check);
        while (CustomerIDList.next()) {
          rlist.add(CustomerIDList.getString("ID"));
        }
        while(rlist.contains(String.valueOf(rid))){
          rid = random.nextInt(9999);
        }
      } catch(SQLException e){
        System.out.println(e);
      }
        String values = "'"+list[0]+"'"+","+"'"+list[1] +"'"+ "," +"'"+ list[4]+"'"+","+"'"+list[3]+"'"+","+"'"+list[2]+"'"+","+"'"+rid+"'";
        String sql = "INSERT INTO customer(firstname, lastname, prefpayment,phone,address,ID) VALUES("+values+");";
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
        String sql = "UPDATE user SET account = ?, password = ?, identification = ? WHERE account = ?";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,list[0]);
            stmt.setString(2,list[1]);
            stmt.setString(3,list[2]);
            stmt.setString(4,list[3]);
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
      } else if(table.equals("bookingpay")){
        String sql = "\0";
        if (list[1].equals("1")){
        sql = "UPDATE bookings SET paid = 1 WHERE ID = ?";
        } else {
        sql = "UPDATE bookings SET paid = 0 WHERE ID = ?";
        }
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,list[0]);
            stmt.executeUpdate();
            if(!conn.getAutoCommit()){
                conn.commit();
            }
        return true;

        } catch(SQLException e){
          System.out.println(e);
            return false;
        }
      } else if(table.equals("booking")){
        String sql = "UPDATE bookings SET Room = ?, bFrom = ?, bTo = ?, Customer = ? WHERE ID = ?";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println(list);
            stmt.setString(1,list[0]);
            stmt.setString(2,list[1]);
            stmt.setString(3,list[2]);
            stmt.setString(4,list[3]);
            stmt.setString(5,list[4]);
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

    public static boolean delete(String table, String[] list) {
      Connection conn;
      System.out.println(list);
      if(table.equals("room")){
        String sql = "DELETE FROM room WHERE room_number = ?";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,list[0]);
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
