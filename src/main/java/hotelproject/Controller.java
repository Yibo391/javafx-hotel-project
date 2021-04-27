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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
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
  
  String role = "";

  @FXML
  public ComboBox<String> roomSel;

	@FXML
	public Label bedtypeLabel;

	@FXML
	public Label floorLebel;
  
	@FXML
	public Label sizeLebel;

	@FXML
	public Label bookedfromLebel;

	@FXML
	public Label bookedtoLevel;

	@FXML
	public Label otherinfolabel;

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
  public ComboBox<String> bRoomSel;

  @FXML
  public ChoiceBox<String> nurole;

  @FXML
  public ChoiceBox<String> fday;

  @FXML
  public ChoiceBox<String> fmonth;

  @FXML
  public ChoiceBox<String> fyear;

  @FXML
  public ChoiceBox<String> tday;

  @FXML
  public ChoiceBox<String> tmonth;

  @FXML
  public ChoiceBox<String> tyear;

  @FXML protected void handleSigninButton(ActionEvent event) throws Exception {
    try{
    if((Validator.checkLogin(initUI(usertext, passtext))).equals("Staff")){
      StaffUi ui = new StaffUi();
      ui.start(launcher.stage);
    } else if((Validator.checkLogin(initUI(usertext, passtext))).equals("Admin")){
      AdminUi ui = new AdminUi();
      ui.start(launcher.stage);
    }
   } catch(NullPointerException e){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Login Error");
    alert.setHeaderText(null);
    alert.setContentText("Login failed. Either your username-pasword combination is wrong or a connection with the database could not be established.");
    alert.showAndWait();
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
    if((nuuser.getText().length())>=4){
      if((nupass.getText().length())>=6){
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
    alert.showAndWait();
   }
  } else {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("User Insertion Error");
    alert.setHeaderText(null);
    alert.setContentText("The password must be at least 6 characters long.");
    alert.showAndWait();
  }
} else {
  Alert alert = new Alert(AlertType.ERROR);
  alert.setTitle("User Insertion Error");
  alert.setHeaderText(null);
  alert.setContentText("The username must be at least 4 characters long.");
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

  @FXML protected void handleReturnButton(ActionEvent event) throws Exception {
    System.out.println(role);
  }

  @FXML protected void handleViewRoomButton(ActionEvent event) throws Exception {
    RoomDetailDialog rview = new RoomDetailDialog();
    rview.start(new Stage());
  }

  @FXML protected void handleCreateBookingButton(ActionEvent event) throws Exception {
    NewBookingDialog nbook = new NewBookingDialog();
    nbook.start(new Stage());
  }

  @FXML protected void handleSubmitBookingButton(ActionEvent event) throws Exception {
    String fromDate = String.valueOf(fyear.getValue()) + "-" + String.valueOf(fmonth.getValue()) + "-" + String.valueOf(fday.getValue());
    String toDate = String.valueOf(tyear.getValue()) + "-" + String.valueOf(tmonth.getValue()) + "-" + String.valueOf(tday.getValue());
    String[] list = {bRoomSel.getValue(), fromDate, toDate};
    try{
    if(handler.insert("booking", list))
    {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Booking Insertion Result");
      alert.setHeaderText(null);
      alert.setContentText("Room " + bRoomSel.getValue() + " has been succesfully booked.");
      alert.showAndWait();


    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Booking Insertion Error");
      alert.setHeaderText(null);
      alert.setContentText("Insertion failed. Make sure you've completed all the fields.");
      alert.showAndWait();
    }
   } catch(Exception e){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Booking Insertion Error");
    alert.setHeaderText(null);
    alert.setContentText("SQL Fault.");
    System.out.println(e);
    alert.showAndWait();
   }
  }


  public void handleComboBox(ActionEvent event) {
		String roomID= roomSel.getValue();
		String roomDetailQuery ="SELECT * FROM room where room_number="+roomID;
		try {
			Statement statement = (handler.getLink()).createStatement();
			ResultSet RoomDetailList= statement.executeQuery(roomDetailQuery);
			
			if(RoomDetailList.next()) {
			String beds = RoomDetailList.getString("beds")+" beds";
			bedtypeLabel.setText(beds);
			
			String location = RoomDetailList.getString("Location");
			floorLebel.setText(location);
			
			String size = RoomDetailList.getString("size")+" Square Meters";
			sizeLebel.setText(size);

      String other = RoomDetailList.getString("other_info");
			otherinfolabel.setText(other);

			}
	}catch (Exception e) {
		e.printStackTrace();
		}
	}


  @FXML
  private void initialize() 
  {
  }


  
}