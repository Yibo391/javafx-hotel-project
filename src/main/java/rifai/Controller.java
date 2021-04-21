package hotelproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.stage.Stage;

public class Controller
{
  @FXML
  public TextField usertext;

  @FXML
  public PasswordField passtext;

  @FXML protected void handleSigninButton(ActionEvent event) throws Exception {
    if((Validator.checkLogin(initUI(usertext, passtext))).equals("staff")){
      StaffUi ui = new StaffUi();
      ui.start(new Stage());
    } else if((Validator.checkLogin(initUI(usertext, passtext))).equals("admin")){
      AdminUi ui = new AdminUi();
      ui.start(new Stage());
    }

  }

  Map<String, String> initUI(TextField account, PasswordField pwd) {
    String userName = usertext.getText();
    String userPwd = passtext.getText();
    Map<String, String> a = new HashMap<>();
    a.put("loginName", userName);
    a.put("loginpwd", userPwd);
    return a;
  }


  @FXML
  private void initialize() 
  {
  }


  
}