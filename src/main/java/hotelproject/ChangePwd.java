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
import javafx.stage.Stage;

public class ChangePwd extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    VBox root = new VBox();
    root.setSpacing(10);

    HBox accountLabels = new HBox();
    accountLabels.setPadding(new Insets(30, 0, 0, 0));
    Label account = new Label("Account:");
    TextField accountInput = new TextField();
    accountLabels.getChildren().addAll(account, accountInput);

    HBox pwdLabels = new HBox();
    pwdLabels.setPadding(new Insets(10, 0, 10, 0));
    Label password = new Label("Old Password:");
    PasswordField pwdInput = new PasswordField();
    pwdLabels.getChildren().addAll(password, pwdInput);

    HBox pwdLabelsNew = new HBox();
    pwdLabels.setPadding(new Insets(10, 0, 10, 0));
    Label passwordAgain = new Label("New Password:");
    PasswordField pwdInputAgain = new PasswordField();
    pwdLabelsNew.getChildren().addAll(passwordAgain, pwdInputAgain);

    HBox pwdLabelsNew2 = new HBox();
    pwdLabels.setPadding(new Insets(10, 0, 10, 0));
    Label passwordAgain2 = new Label("New Password:");
    PasswordField pwdInputAgain2 = new PasswordField();
    pwdLabelsNew2.getChildren().addAll(passwordAgain2, pwdInputAgain2);

    Button confirm = new Button("Confirm");

    confirm.setOnAction(event -> {
      Alert alert = new Alert(AlertType.INFORMATION);
      Map<String, String> info = LoginCheck.initUI(accountInput, pwdInput);
      boolean success = LoginCheck.checkLogin(info);
      if (success) {
        Map<String, String> pwdNew = LoginCheck.initUI(accountInput, pwdInputAgain);
        if (pwdInputAgain.getText().equals(pwdInputAgain2.getText())) {
          LoginCheck.changePwd(pwdNew);
          alert.setTitle("successful");
          alert.setHeaderText("you got it");
        } else {
          alert.setTitle("error");
          alert.setHeaderText("The two inputs are different");
        }
      } else {
        alert.setTitle("wrong account or password");
        alert.setHeaderText("sorry, please check your account or password");
      }
      alert.showAndWait();
    });
    root.getChildren().addAll(accountLabels, pwdLabels, pwdLabelsNew, pwdLabelsNew2, confirm);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
