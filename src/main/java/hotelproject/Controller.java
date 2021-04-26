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
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Controller
{
  SQLHandler handler = new SQLHandler();

  @FXML
  public TextField usertext;

  @FXML
  public PasswordField passtext;

  @FXML
  public Slider roomsize;
  
  @FXML
  public Slider bedno;

  @FXML
  public TextField roomno;

  @FXML
  public TextField rlocation;

  @FXML
  public TextArea otherinfo;

  @FXML
  public TextField nuuser;

  @FXML
  public TextField nupass;

  @FXML
  public TextField nupassr;

  @FXML
  public ChoiceBox<String> nurole;

  @FXML protected void handleSigninButton(ActionEvent event) throws Exception {
    if((Validator.checkLogin(initUI(usertext, passtext))).equals("Staff")){
      StaffUi ui = new StaffUi();
      ui.start(new Stage());
    } else if((Validator.checkLogin(initUI(usertext, passtext))).equals("Admin")){
      AdminUi ui = new AdminUi();
      ui.start(new Stage());
    }
  }

  @FXML protected void handleSubmitRoomButton(ActionEvent event) throws Exception {
    String str;
    try{
      str = otherinfo.getText();
    } catch (Exception e){
      str = "-";
    }
    try{
    String[] list = {String.valueOf(Math.round(roomsize.getValue())),String.valueOf(Math.round(bedno.getValue())),roomno.getText(),rlocation.getText(), str};
    if(handler.insert("room", list))
    {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Room Insertion Result");
      alert.setHeaderText(null);
      alert.setContentText("Room " + roomno.getText() + " has been succesfully added to the database.");
      alert.showAndWait();

    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Room Insertion Error");
      alert.setHeaderText(null);
      alert.setContentText("Insertion failed. Make sure you've completed all the fields.");
      alert.showAndWait();
    }
   } catch(Exception e){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Room Insertion Error");
    alert.setHeaderText(null);
    alert.setContentText("SQL Fault.");
    System.out.println(e);
    alert.showAndWait();
   }
  }

  @FXML protected void handleSubmitNewUserButton(ActionEvent event) throws Exception {
    String str;
    try{
      str = otherinfo.getText();
    } catch (Exception e){
      str = "-";
    }
    try{
    String[] list = {nuuser.getText(),nupass.getText(),  nurole.getValue()};
    if(nupass.getText().equals(nupassr.getText())){
    if(handler.insert("user", list))
    {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("User Insertion Result");
      alert.setHeaderText(null);
      alert.setContentText("User " + nuuser.getText() + " has been succesfully added to the database.");
      alert.showAndWait();

    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("User Insertion Error");
      alert.setHeaderText(null);
      alert.setContentText("Insertion failed. Make sure you've completed all the fields.");
      alert.showAndWait();
    }
   } else {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("User Insertion Error");
    alert.setHeaderText(null);
    alert.setContentText("Passwords don't match. Please try again.");
    alert.showAndWait();
   }
   } catch(Exception e){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("User Insertion Error");
    alert.setHeaderText(null);
    alert.setContentText("SQL Fault.");
    System.out.println(e);
    alert.showAndWait();
   }
  }

  @FXML protected void handleAddRoomButton(ActionEvent event) throws Exception {
    
    NewRoomDialog newroom = new NewRoomDialog();
    newroom.start(new Stage());
  }

  @FXML protected void handleAddCustomerButton(ActionEvent event) throws Exception {
    NewUserDialog newuser = new NewUserDialog();
    newuser.start(new Stage());
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