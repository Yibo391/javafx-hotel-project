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
import java.sql.PreparedStatement;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Statement;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.ResultSet;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class EditProfileDialog extends Application {

  public static void main(String[] args) throws Exception {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/editpr.fxml"));
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root);
		SQLHandler handler = new SQLHandler();
     primaryStage.setTitle("Edit Profile Dialog");
     primaryStage.setScene(scene);
     primaryStage.show();
     TextField user = (TextField) scene.lookup("#nusrb");
		 TextField passw = (TextField) scene.lookup("#npswb");
		 user.setText(Controller.getUsername());
     
  }
}
