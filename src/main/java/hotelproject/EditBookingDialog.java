package hotelproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import java.util.Locale;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDate;
import org.w3c.dom.Text;

import java.sql.ResultSet;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class EditBookingDialog extends Application {

  public static void main(String[] args) throws Exception {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    String id = Controller.GetBookEditID();
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/editbook.fxml"));
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root);
		SQLHandler handler = new SQLHandler();
     primaryStage.setTitle("Edit Booking Dialog");
     primaryStage.setScene(scene);
     primaryStage.show();
     ComboBox eBookNo = (ComboBox) scene.lookup("#eBookNo");
     ComboBox eBookCust = (ComboBox) scene.lookup("#eBookCust");
     DatePicker eBookFrom = (DatePicker) scene.lookup("#eBookFrom");
     DatePicker eBookTo = (DatePicker) scene.lookup("#eBookTo");
     Label eBookTitle = (Label) scene.lookup("#ebooklabel");
     eBookTitle.setText("Booking " + id);
		 String sql = "SELECT * from BOOKINGS where ID = ?";
		 try {
      PreparedStatement stmt = handler.getLink().prepareStatement(sql);
      stmt.setString(1,id);
      System.out.println("========== "+ stmt);
      ResultSet results = stmt.executeQuery();
			while (results.next()) {
        String name = "\0";
        String customerNameQuery = "SELECT firstname, lastname FROM customer WHERE ID ='"+results.getString("Customer")+"'";
        Statement statement = (handler.getLink()).createStatement();
        ResultSet Customers = statement.executeQuery(customerNameQuery);
        while(Customers.next()){
          name = results.getString("Customer") + ". " + Customers.getString("firstname") + " " + Customers.getString("lastname");
        }
        System.out.println(results.getString("Room"));
        System.out.println(name);
        eBookNo.setValue(results.getString("Room"));
        eBookCust.setValue(name);
        eBookFrom.setValue(LocalDate.parse(results.getString("bFrom"), df));
        eBookTo.setValue(LocalDate.parse(results.getString("bTo"), df));
			}
      sql = "SELECT * from customer";
      stmt = handler.getLink().prepareStatement(sql);
      ResultSet results2 = stmt.executeQuery();
      while (results2.next()) {
        String name = results2.getString("ID") + ". " + results2.getString("firstname") + " " + results2.getString("lastname");
        eBookCust.getItems().add(name);
      }
      sql = "SELECT * from room";
      stmt = handler.getLink().prepareStatement(sql);
      ResultSet results3 = stmt.executeQuery();
      while (results3.next()) {
        eBookNo.getItems().add(results3.getString("room_number"));
      }
	} catch (Exception e) {
		System.out.println(e);
		}
     
  }
}
