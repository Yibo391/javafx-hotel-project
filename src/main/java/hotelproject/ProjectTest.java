package hotelproject;


import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProjectTest extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    VBox layout = new VBox();//layout
    layout.setPadding(new Insets(5));
    layout.setSpacing(5);
    HBox accountBox = new HBox(); //1
    HBox pwdBox = new HBox();//2
    accountBox.setPadding(new Insets(5));
    accountBox.setSpacing(5);
    pwdBox.setPadding(new Insets(5));
    pwdBox.setSpacing(5);
    Label welcome = new Label("Welcome");
    welcome.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 40));

    Label account = new Label("Account:");
    TextField accountInput = new TextField();
    Label pwd = new Label("Password:");
    PasswordField pwdInput = new PasswordField();
    accountBox.getChildren().addAll(account, accountInput);
    pwdBox.getChildren().addAll(pwd, pwdInput);

    HBox sign = new HBox();//
    sign.setSpacing(80);
    Button login = new Button("sign in");
    Button changePwd = new Button("Change Password");
    changePwd.setPadding(Insets.EMPTY);
    changePwd.setTextFill(Color.BLUE);
    sign.getChildren().addAll(login, changePwd);
    Label tip = new Label(); // tip label for verification
    login.setOnAction(event -> { //login
      Map<String, String> info = LoginCheck.initUI(accountInput, pwdInput);
      boolean loginSucess = LoginCheck.checkLogin(info);
      if (loginSucess && LoginCheck.id.equals("1")) {
        AdminUi adminUi = new AdminUi();
        adminUi.start(new Stage());
        primaryStage.close();
      } else if(loginSucess && LoginCheck.id.equals("2")){
        StaffUi staffUi = new StaffUi();
        staffUi.start(new Stage());
        primaryStage.close();
      }else {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("wrong account or password");
        alert.setHeaderText("sorry, please check your account or password");
        alert.showAndWait();

      }
    });
    changePwd.setOnAction(event -> { //change password
      ChangePwd changePwd1 = new ChangePwd();
      changePwd1.start(new Stage());
    });

    layout.getChildren().addAll(welcome, accountBox, pwdBox, sign, tip);
    primaryStage.setScene(new Scene(layout));
    primaryStage.show();
  }


}
