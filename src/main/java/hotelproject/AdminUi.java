package hotelproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class
AdminUi extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    VBox layout = new VBox();
    User user = new User();
    GridPane grid = new GridPane();
    user.createNewUser(grid, layout);
    layout.getChildren().add(grid);
   primaryStage.setScene(new Scene(layout,400,400));
    primaryStage.setTitle("Admin ui");
   primaryStage.show();
  }
}
