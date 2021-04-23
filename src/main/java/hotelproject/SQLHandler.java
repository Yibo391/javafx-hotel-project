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

      }
      return false;
    }
}
