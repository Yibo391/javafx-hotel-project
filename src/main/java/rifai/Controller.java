package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller
{
  @FXML
  private TextField usertext;

  @FXML
  private PasswordField passtext;

  @FXML protected void handleSigninButton(ActionEvent event) {
    System.out.println("Sign in button pressed");
    System.out.println(usertext.getText());
    System.out.println(passtext.getText());

  }

  @FXML
  private void initialize() 
  {
  }

  public void getUsername(){

  }
  
}