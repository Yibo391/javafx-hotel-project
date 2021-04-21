package ui;

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
public class UI extends Application {


    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        Controller control = new Controller();
        Application.launch(UI.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
         stage.setTitle("Menu");
         stage.setScene(scene);
       stage.show();
     }
    
}