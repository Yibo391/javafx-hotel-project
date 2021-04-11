import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextField; 

public class UI extends Application {

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Scene scene = new Scene(grid, 300, 275);
        stage.setScene(scene);
        stage.show();
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        Label welcometext = new Label("Please log in.");
        grid.add(welcometext, 0, 1);
        Label userName = new Label("User Name:");
        grid.add(userName, 0, 3);
        
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 3);
        
        Label pw = new Label("Password:");
        grid.add(pw, 0, 4);
        
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 4);
    }

    public static void main(String[] args) {
        launch();
    }

}