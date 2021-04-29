package hotelproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class
UserUi extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui.fxml"));
    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root);
     primaryStage.setTitle("Hydrogen Hotel User Panel");
     primaryStage.setScene(scene);
     primaryStage.show();
     
  }
}
