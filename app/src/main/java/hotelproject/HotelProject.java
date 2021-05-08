package hotelproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HotelProject extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        root.setPadding(new Insets(5));
        Label title = new Label("JavaFX");
        Label mysql; 
               try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cattle_farm_db?user=root&password=root&useSSL=false");
                mysql = new Label("Driver found and connected");
                root.getChildren().addAll(title, mysql);
                primaryStage.setScene(new Scene(root, 400, 200));
                primaryStage.setTitle("JavaFX");
                primaryStage.show();
            
            } catch (SQLException e) {
                mysql = new Label("An error has occurred: " + e.getMessage());
                root.getChildren().addAll(title, mysql);
                primaryStage.setScene(new Scene(root, 400, 200));
                primaryStage.setTitle("JavaFX");
                primaryStage.show();}
                }
    public static void main(String[] args) {
        System.out.println("Moshen");
        
            launch(args);
    }

    // @Override
    // public void start(Stage primaryStage) throws Exception {
    //     Scene theScene = new Scene(new Label("Hello"), 640, 480);
    //     primaryStage.setScene(theScene);
    //     primaryStage.setTitle("First example");
    //     primaryStage.show();
    // }
    
}
