package hotelproject;


import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectTest extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    VBox layout = new VBox();
    layout.setPadding(new Insets(5));
    layout.setSpacing(5);
    Label welcome = new Label("Welcome");
    HBox accountBox = new HBox();
    HBox pwdBox = new HBox();
    accountBox.setPadding(new Insets(5));
    accountBox.setSpacing(5);
    pwdBox.setPadding(new Insets(5));
    pwdBox.setSpacing(5);
    Label account = new Label("Account:");
    TextField accountInput = new TextField();
    Label pwd = new Label("Password:");
    TextField pwdInput = new TextField();
    pwdInput.visibleProperty();
    accountBox.getChildren().addAll(account, accountInput);
    pwdBox.getChildren().addAll(pwd, pwdInput);
    Button login = new Button("sign in");
    Label tip = new Label();
    login.setOnAction(event -> {
      Map<String,String> info = LoginCheck.initUI(accountInput,pwdInput);
      boolean loginSucess = LoginCheck.checkLogin(info);
      if(loginSucess) {
        tip.setText("ok");
      }else {
        tip.setText("no");
      }

    });



    layout.getChildren().addAll(welcome,accountBox, pwdBox, login,tip);
    primaryStage.setScene(new Scene(layout));
    primaryStage.show();
  }



}
