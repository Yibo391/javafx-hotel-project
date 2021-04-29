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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Statement;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class RoomDetailDialog extends Application {

  public static void main(String[] args) throws Exception {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/roomDetail.fxml"));
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root);
		SQLHandler handler = new SQLHandler();
     primaryStage.setTitle("Room Overview");
     primaryStage.setScene(scene);
     primaryStage.show();
     ComboBox roomSel = (ComboBox) scene.lookup("#rs");
		 ObservableList <String> list = FXCollections.observableArrayList();
		 String roomIdQuery = "SELECT room_number FROM room";
		 try {
			Statement statement = (handler.getLink()).createStatement();
			ResultSet RoomIDList= statement.executeQuery(roomIdQuery);
			while (RoomIDList.next()) {
				list.add(RoomIDList.getString("room_number"));
			}
			System.out.println(list);
			for(int i = 0; i<list.size(); i++){
				System.out.println(list.get(i));
				roomSel.getItems().add(list.get(i));
			}
	} catch (Exception e) {
		e.printStackTrace();
		}
     
  }
}
