package hotelproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
    VBox root =new VBox();
    root.setSpacing(10);

    HBox accountLabels = new HBox();
    accountLabels.setPadding(new Insets(30,0,0,0));
    Label account = new Label("Account:");
    TextField accountInput = new TextField();
    accountLabels.getChildren().addAll(account,accountInput);

    HBox pwdLabels = new HBox();
    pwdLabels.setPadding(new Insets(10,0,10,0));
    Label password = new Label("New Password:");
    PasswordField pwdInput = new PasswordField();
    pwdLabels.getChildren().addAll(password,pwdInput);

    HBox pwdLabelsAgain = new HBox();
    pwdLabels.setPadding(new Insets(10,0,10,0));
    Label passwordAgain = new Label("New Password:");
    PasswordField pwdInputAgain = new PasswordField();
    pwdLabelsAgain.getChildren().addAll(passwordAgain,pwdInputAgain);

    Button confirm = new Button("Confirm");

    confirm.setOnAction(event -> {
      if (pwdInput.getText().equals(pwdInputAgain.getText())){
        System.out.println("ok");
      }
    });
    root.getChildren().addAll(accountLabels,pwdLabels,pwdLabelsAgain,confirm);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
