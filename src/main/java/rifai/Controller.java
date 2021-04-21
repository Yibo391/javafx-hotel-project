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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
  @FXML protected void handleAddRoomButton(ActionEvent event) throws Exception {
    
    VBox layout = new VBox();
    GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
    NewRoomDialog newroom = new NewRoomDialog();
    newroom.createNewRoom(layout, grid);
    layout.getChildren().addAll(grid);
    Stage stage = new Stage();
    stage.setScene(new Scene(layout,400,400));
    stage.setTitle("Add Room Dialog");
    stage.show();


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