package hotelproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Statement;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class BookingDetailDialog extends Application {

  public static void main(String[] args) throws Exception {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/bookDetail.fxml"));
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root);
		SQLHandler handler = new SQLHandler();
     primaryStage.setTitle("Booking Overview");
     primaryStage.setScene(scene);
     primaryStage.show();
     ListView bklist = (ListView) scene.lookup("#bklist");
		 ObservableList <String> list = FXCollections.observableArrayList();
		 String bookIdQuery = "SELECT * FROM bookings";
		 try {
			Statement statement = (handler.getLink()).createStatement();
			ResultSet BookIDList= statement.executeQuery(bookIdQuery);
      statement = (handler.getLink()).createStatement();
			while (BookIDList.next()) {
        String name = "\0";
        String customerNameQuery = "SELECT firstname, lastname FROM customer WHERE ID ='"+BookIDList.getString("Customer")+"'";
        ResultSet Customers= statement.executeQuery(customerNameQuery);
        while(Customers.next()){
          name = Customers.getString("firstname") + " " + Customers.getString("lastname");
        }
        String entry = BookIDList.getString("ID") + ". " + "Room " + BookIDList.getString("Room") + ", " + name + ", " + BookIDList.getString("bFrom") + " -> " + BookIDList.getString("bTo");
        bklist.getItems().add(entry);  
			}
	} catch (Exception e) {
		e.printStackTrace();
		}
     
  }
}
