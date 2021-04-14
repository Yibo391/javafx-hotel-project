package hotelproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StaffUi extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    VBox layout = new VBox();
    primaryStage.setScene(new Scene(layout,400,400));
    primaryStage.setTitle("Staff ui");
    primaryStage.show();
  }
}
