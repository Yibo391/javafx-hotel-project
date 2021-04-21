package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller
{
  @FXML
  private TextField username;

  @FXML
  private TextArea password;
  
   
  @FXML
  private void initialize() 
  {
  }
   
  @FXML
  private void printOutput() 
  {
      System.out.println(username.getText());
  }
}