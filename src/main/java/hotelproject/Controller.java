package hotelproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Dialog;
import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.ChronoLocalDate;
import javafx.scene.control.Tooltip;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.DatePicker;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.sql.ResultSet;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import java.util.Locale;
import java.util.GregorianCalendar;
import java.util.Calendar;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;
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
  
  static String username = "";

  static String bookEdit = "";

  static String role = "";

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
  public TextField perseditpassbar;

	@FXML
	public Label otherinfolabel;

  @FXML
  public ChoiceBox<String> perschoicebox;

  @FXML
  public Label bRoomLabel;

  @FXML
  public Label bFromLabel;

  @FXML
  public Label bToLabel;

  @FXML
  public Label bCustomerLabel;

  @FXML
  public DatePicker odatefilter;

  @FXML
  public TextField persedituserbar;

  @FXML
  public ChoiceBox<String> obookingpaychoice;

  @FXML
  public Label bPaidLabel;

  @FXML
  public TextField usertext;

  @FXML
  public TextField edituserbar;

  @FXML
  public TextField editpassbar;

  @FXML
  public PasswordField passtext;

  @FXML
  public Slider roomsize;

  @FXML
  public ChoiceBox<String> persrolebox;

  @FXML
  public TextField ncfirst;

  @FXML
  public TextField nclast;

  @FXML
  public TextField ncaddr;

  @FXML
  public TextField ncphone;

  @FXML
  public ChoiceBox<String> ncpayment;
  
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
  public ComboBox<String> bBookingCustomer;

  @FXML
  public ChoiceBox<String> eroomid;

  @FXML
  public ListView<String> ebookinglist;

  @FXML
  public ChoiceBox<String> persroleeditbox;

  @FXML
  public Slider eroomsize;

  @FXML
  public Slider ebedno;

  @FXML
  public TextField elocation;

  @FXML
  public TextArea einfo;

  @FXML
  public ChoiceBox<String> nurole;

  @FXML
  public DatePicker bFromDate;

  @FXML
  public DatePicker bToDate;

  @FXML
  public DatePicker eBookFrom;

  @FXML
  public DatePicker eBookTo;

  @FXML
  public ComboBox<String> eBookNo;

  @FXML
  public ComboBox<String> eBookCust;

  @FXML
  public DatePicker roombook;

  @FXML
  public ChoiceBox<String> obookingfilter;

  @FXML
  public TextField obookingfiltersearch;

  public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
  }  


  void triggerBookingDates(){
    ArrayList<ChronoLocalDate> fredlist = new ArrayList<ChronoLocalDate>();
    bFromDate.setDisable(false);
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
    try{
        String bookIdQuery = "SELECT * FROM bookings WHERE Room = ?";
        PreparedStatement stat = handler.getLink().prepareStatement(bookIdQuery);
        stat = handler.getLink().prepareStatement(bookIdQuery);
        stat.setString(1, bRoomSel.getValue());
        System.out.println(stat);
          ResultSet BookIDList= stat.executeQuery();
          Statement statement = (handler.getLink()).createStatement();
          while (BookIDList.next()) {
            int from = doy(BookIDList.getString("bFrom"));
            int to = doy(BookIDList.getString("bTo"));
            Boolean done = false;
            LocalDate i = LocalDate.parse(BookIDList.getString("bFrom"), df);
            Calendar c = Calendar.getInstance();
            String[] splitter = BookIDList.getString("bFrom").split("-");
            if (Integer.valueOf(splitter[1])<9){
              String temp = "0"+splitter[1];
              splitter[1] = temp;
            }
            if (Integer.valueOf(splitter[2])<9){
              String temp = "0"+splitter[2];
              splitter[2] = temp;
            }
            c.set(Integer.valueOf(splitter[0]), Integer.valueOf(splitter[1]), Integer.valueOf(splitter[2]));
            while(!fredlist.contains(LocalDate.parse(BookIDList.getString("bTo"), df))){
            c.add(Calendar.DATE, 1);
            String temp = "\0";
            String prMonth = String.valueOf(c.get(Calendar.MONTH));
            String prDay=String.valueOf(c.get(Calendar.DATE));
            if(Integer.valueOf(c.get(Calendar.MONTH))<=9){
            temp = "0" + c.get(Calendar.MONTH);
            prMonth = temp;
            }
            if(Integer.valueOf(c.get(Calendar.DATE))<=9){
              temp = "0" + c.get(Calendar.DATE);
              prDay = temp;
              }
            if(Integer.valueOf(c.get(Calendar.MONTH))==0)
            prMonth = "01";
            temp = c.get(Calendar.YEAR)+"-"+prMonth+"-"+prDay;
            i = LocalDate.parse(temp, df); 
            fredlist.add(i);
            fredlist.add(i.minusDays(1));
            }
          } 
        } catch(Exception e){
          System.out.println(e);
        }
        Calendar c = Calendar.getInstance();
        String[] splitter = fredlist.get(0).format(df).split("-");
        c.set(Integer.valueOf(splitter[0]), Integer.valueOf(splitter[1]), Integer.valueOf(splitter[2]));
        String temp = "\0";
        String prMonth = String.valueOf(c.get(Calendar.MONTH));
        String prDay=String.valueOf(c.get(Calendar.DATE));
        if(Integer.valueOf(c.get(Calendar.MONTH))<=9){
        temp = "0" + c.get(Calendar.MONTH);
        prMonth = temp;
        }
        if(Integer.valueOf(c.get(Calendar.DATE))<=9){
          temp = "0" + c.get(Calendar.DATE);
          prDay = temp;
          }
        if(Integer.valueOf(c.get(Calendar.MONTH))==0)
        prMonth = "01";
        temp = c.get(Calendar.YEAR)+"-"+prMonth+"-"+prDay;
        fredlist.add(0, LocalDate.parse(temp, df).minusDays(1));
        System.out.println(fredlist);
bToDate.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   setDisable(((item.isAfter(fredlist.get(0))) && !bFromDate.getValue().isBefore(item)) || item.isBefore(bFromDate.getValue()) || item.equals(fredlist.get(0)) || fredlist.contains(item) || ((item.isAfter(fredlist.get(fredlist.size() - 1))) && (fredlist.get(fredlist.size() - 1)).isAfter(bFromDate.getValue())));
               }});

               bFromDate.setDayCellFactory(d ->
               new DateCell() {
                   @Override public void updateItem(LocalDate item, boolean empty) {
                       super.updateItem(item, empty);
                       setDisable(fredlist.contains(item));
                   }});
  }

  int doy(String s){
    String[] splitter = s.split("-");
    LocalDate selector = LocalDate.of(Integer.valueOf(splitter[0]), Integer.valueOf(splitter[1]), Integer.valueOf(splitter[2]));
    int doy = selector.getDayOfYear();
    return doy;
  }

  String daytodate(int s, int year){
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
    LocalDate selector = Year.of(year).atDay(s);
    return selector.format(df);
  }

  @FXML protected void handleSigninButton(ActionEvent event) throws Exception {
    try{
    if((Validator.checkLogin(initUI(usertext, passtext))).equals("Staff")){
      username = usertext.getText();
      role = "Staff";
      StaffUi ui = new StaffUi();
      ui.start(launcher.stage);
    } else if((Validator.checkLogin(initUI(usertext, passtext))).equals("Admin")){
      username = usertext.getText();
      role = "Admin";
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


  @FXML protected void handleSubmitNewCustomerButton(ActionEvent event) throws Exception {

    if((ncfirst.getText().length())>=2){
      if((nclast.getText().length())>=2){
        if((ncaddr.getText().length())>=6){
          if((ncphone.getText().length())>=4){
            if((ncpayment.getValue()!=null)){
              if((isNumeric(ncphone.getText()))){
    try{
    String[] list = {ncfirst.getText(),nclast.getText(),ncaddr.getText(),ncphone.getText(),ncpayment.getValue()};
    if(handler.insert("customer", list))
    {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Customer Insertion Result");
      alert.setHeaderText(null);
      alert.setContentText(ncfirst.getText() + " " + nclast.getText() + " has been succesfully added to the database.");
      alert.showAndWait();
      

    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Customer Insertion Error");
      alert.setHeaderText(null);
      alert.setContentText("Insertion failed. Make sure you've completed all the fields.");
      alert.showAndWait();
    }
   } catch(Exception e){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Customer Insertion Error");
    alert.setHeaderText(null);
    alert.setContentText("SQL Fault.");
    alert.showAndWait();
   }
  } else {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Customer Insertion Error");
    alert.setHeaderText(null);
    alert.setContentText("The phone number cannot contain letters.");
    alert.showAndWait();
  }
  } else {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Customer Insertion Error");
    alert.setHeaderText(null);
    alert.setContentText("Please select a payment method.");
    alert.showAndWait();
  }
} else {
  Alert alert = new Alert(AlertType.ERROR);
  alert.setTitle("Customer Insertion Error");
  alert.setHeaderText(null);
  alert.setContentText("The phone number must be at least 4 digits long.");
  alert.showAndWait();
}
} else {
  Alert alert = new Alert(AlertType.ERROR);
  alert.setTitle("Customer Insertion Error");
  alert.setHeaderText(null);
  alert.setContentText("The address must be at least 6 characters long.");
  alert.showAndWait();
}
  } else {
  Alert alert = new Alert(AlertType.ERROR);
  alert.setTitle("Customer Insertion Error");
  alert.setHeaderText(null);
  alert.setContentText("The last name must be at least two characters long.");
  alert.showAndWait();
      }
  } else {
    Alert alert = new Alert(AlertType.ERROR);
  alert.setTitle("Customer Insertion Error");
  alert.setHeaderText(null);
  alert.setContentText("The first name must be at least two characters long.");
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

  @FXML protected void handleAddUserButton(ActionEvent event) throws Exception {
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
  }

  @FXML protected void handleManageBookingsButton(ActionEvent event) throws Exception {
    BookingDetailDialog bdet = new BookingDetailDialog();
    bdet.start(new Stage());
  }

  @FXML protected void handleViewRoomButton(ActionEvent event) throws Exception {
    RoomDetailDialog rview = new RoomDetailDialog();
    rview.start(new Stage());
  }

  @FXML protected void handleEditRoomButton(ActionEvent event) throws Exception {
    EditRoomDialog eroom = new EditRoomDialog();
    eroom.start(new Stage());
  }


  @FXML protected void handleCreateBookingButton(ActionEvent event) throws Exception {
    NewBookingDialog nbook = new NewBookingDialog();
    nbook.start(new Stage());
  }

  @FXML protected void handleEditProfileButton(ActionEvent event) throws Exception {
    EditProfileDialog eprof = new EditProfileDialog();
    eprof.start(new Stage());
  }

  @FXML protected void handleEditBookingButton(ActionEvent event) throws Exception {
    ObservableList<String> selectedIndices = ebookinglist.getSelectionModel().getSelectedItems();
    String s = "\0";
    boolean paid = false;
    for(String o : selectedIndices){
      s = o;
    }
    String [] split = s.split(" ");
    s = split[0].replaceAll("\\D+","");
    bookEdit = s;
    if(s.length()>0){
    EditBookingDialog ebook = new EditBookingDialog();
    ebook.start(new Stage());
    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Booking Editor Error");
      alert.setHeaderText(null);
      alert.setContentText("You have not selected a booking.");
      alert.showAndWait(); 
    }
  }

  @FXML protected void handleAddCustomerButton(ActionEvent event) throws Exception {
    NewCustomerDialog nc = new NewCustomerDialog();
    nc.start(new Stage());
  }

  @FXML protected void handleDeleteRoomButton(ActionEvent event) throws Exception {
    if(roomSel.getValue()!=null){
      Alert alert1 = new Alert(AlertType.CONFIRMATION);
      alert1.setTitle("Room Deletion");
      alert1.setHeaderText(null);
      alert1.setContentText("You are about to delete room " +roomSel.getValue()+". Are you sure?");
      Optional<ButtonType> result = alert1.showAndWait();
      if (result.get() == ButtonType.OK){
        String[] list = {roomSel.getValue()};
        if(handler.delete("room", list)){
          Alert alert2 = new Alert(AlertType.INFORMATION);
          alert2.setTitle("Room Deletion");
          alert2.setHeaderText(null);
          alert2.setContentText("Room " + roomSel.getValue() + " has been deleted.");
          alert2.showAndWait();
        } else {
          Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Room Deletion Error");
      alert.setHeaderText(null);
      alert.setContentText("SQL Fault.");
      alert.showAndWait();
     }
        
    } else {
    }

    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Room Deletion Error");
      alert.setHeaderText(null);
      alert.setContentText("You have not selected a room.");
      alert.showAndWait();
    }
  }


  @FXML protected void handleMarkBookingButton(ActionEvent event) throws Exception {
    ObservableList<String> selectedIndices = ebookinglist.getSelectionModel().getSelectedItems();
    String s = "\0";
    boolean paid = false;
    for(String o : selectedIndices){
      s = o;
    }
    String [] split = s.split(" ");
    s = split[0].replaceAll("\\D+","");
    Statement statement = (handler.getLink()).createStatement();
    String check = "SELECT Paid FROM bookings WHERE ID ='"+s+"'";
      try {
        ResultSet isPaid = statement.executeQuery(check);
        statement = (handler.getLink()).createStatement();
        while (isPaid.next()) {
          if(isPaid.getString("Paid").equals("0")){
            paid = false;
          } else {
            paid = true;
          }
        }
        } catch (Exception e){
          System.out.println(e);
        }
          if(paid == false){
    Alert alert1 = new Alert(AlertType.CONFIRMATION);
    alert1.setTitle("Booking Payment");
    alert1.setHeaderText(null);
    alert1.setContentText("You are about to mark this booking as paid. Are you sure?");
    Optional<ButtonType> result = alert1.showAndWait();
    if (result.get() == ButtonType.OK){
      String[] list = {s, "1"};
      if(handler.update("bookingpay", list)){
        Alert alert2 = new Alert(AlertType.INFORMATION);
        alert2.setTitle("Booking Payment");
        alert2.setHeaderText(null);
        alert2.setContentText("Booking ID " + s + " has been marked as paid.");
        alert2.showAndWait();
      } else {
        Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Booking Payment Error");
    alert.setHeaderText(null);
    alert.setContentText("SQL Fault.");
    alert.showAndWait();
   }
      
  } else {
  }
} else {
  Alert alert1 = new Alert(AlertType.CONFIRMATION);
  alert1.setTitle("Booking Payment");
  alert1.setHeaderText(null);
  alert1.setContentText("This booking is marked as paid. Do you want to unmark?");
  Optional<ButtonType> result = alert1.showAndWait();
  if (result.get() == ButtonType.OK){
    String[] list = {s, "0"};
    if(handler.update("bookingpay", list)){
      Alert alert2 = new Alert(AlertType.INFORMATION);
      alert2.setTitle("Booking Payment");
      alert2.setHeaderText(null);
      alert2.setContentText("Booking ID " + s + " is no longer marked as paid.");
      alert2.showAndWait();
    } else {
      Alert alert = new Alert(AlertType.ERROR);
  alert.setTitle("Booking Payment Error");
  alert.setHeaderText(null);
  alert.setContentText("SQL Fault.");
  alert.showAndWait();
 }
    
} else {
}
}
  }

  @FXML protected void handleSubmitBookingButton(ActionEvent event) throws Exception {   
    DatePicker mrgRqstDate = new DatePicker();
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
    String fromDate = bFromDate.getValue().format(df);
    String toDate = bToDate.getValue().format(df);
    String s = "\0";
    try{
    s = bBookingCustomer.getValue().replaceAll("\\D+","");
    } catch (Exception e){
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Booking Insertion Error");
      alert.setHeaderText(null);
      alert.setContentText("Please select a customer to book for.");
      alert.showAndWait();
    }
    String[] list = {bRoomSel.getValue(), fromDate, toDate, s};
    if(bRoomSel.getValue()!=null){
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
  } else {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Booking Insertion Error");
    alert.setHeaderText(null);
    alert.setContentText("Please select a room to book.");
    alert.showAndWait();

  }
  }

  @FXML protected void handleEditPApplyButton(ActionEvent event) throws Exception {
    String[] list = {edituserbar.getText(), editpassbar.getText(), getRole(), getUsername()};
    if((edituserbar.getText().length())>=4){
      if((editpassbar.getText().length())>=6){
    try{
    if(handler.update("user", list))
    {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Profile Edit Result");
      alert.setHeaderText(null);
      alert.setContentText("Profile edited succesfully.");
      username = edituserbar.getText();
      alert.showAndWait();


    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Profile Edit Error");
      alert.setHeaderText(null);
      alert.setContentText("Edit failed. Make sure you've completed all the fields.");
      alert.showAndWait();
    }
   } catch(Exception e){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Profile Edit");
    alert.setHeaderText(null);
    alert.setContentText("SQL Fault.");
    System.out.println(e);
    alert.showAndWait();
   } 
  } else {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Profile Edit Error");
    alert.setHeaderText(null);
    alert.setContentText("The password must be at least 6 characters long.");
    alert.showAndWait();
   }
  } else {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Profile Edit Error");
    alert.setHeaderText(null);
    alert.setContentText("The username must be at least 4 characters long.");
    alert.showAndWait();
  }
  }

  @FXML protected void handleEditRApplyButton(ActionEvent event) throws Exception {
    String str;
    try{
      str = einfo.getText();
    } catch (Exception e){
      str = "-";
    }
    String[] list = {String.valueOf(Math.round(eroomsize.getValue())),String.valueOf(Math.round(ebedno.getValue())),eroomid.getValue(),elocation.getText(), str};
    if(eroomid.getValue()!=null){
    try{
    if(handler.update("room", list))
    {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Room Edit Result");
      alert.setHeaderText(null);
      alert.setContentText("Room " + eroomid.getValue() + " has been edited succesfully.");
      alert.showAndWait();


    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Room Edit Error");
      alert.setHeaderText(null);
      alert.setContentText("Edit failed. Make sure you've completed all the fields.");
      alert.showAndWait();
    }
   } catch(Exception e){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Room Edit Error");
    alert.setHeaderText(null);
    alert.setContentText("SQL Fault.");
    System.out.println(e);
    alert.showAndWait();
   } 
  } else {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Room Edit Error");
    alert.setHeaderText(null);
    alert.setContentText("Please select a room to edit.");
    alert.showAndWait();
  }
  }


  public void handleComboBox(ActionEvent event) {
    ArrayList<ChronoLocalDate> fredlist = new ArrayList<ChronoLocalDate>();
		String roomID= roomSel.getValue();
    ArrayList<ChronoLocalDate> blist = new ArrayList<ChronoLocalDate>();
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
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
      String bookIdQuery = "SELECT * FROM bookings WHERE Room = ?";
      PreparedStatement stat = handler.getLink().prepareStatement(bookIdQuery);
      stat = handler.getLink().prepareStatement(bookIdQuery);
      stat.setString(1, roomID);
        ResultSet BookIDList= stat.executeQuery();
        while (BookIDList.next()) {
          int from = doy(BookIDList.getString("bFrom"));
          int to = doy(BookIDList.getString("bTo"));
          Boolean done = false;
          LocalDate i = LocalDate.parse(BookIDList.getString("bFrom"), df);
          Calendar c = Calendar.getInstance();
          String[] splitter = BookIDList.getString("bFrom").split("-");
          if (Integer.valueOf(splitter[1])<9){
            String temp = "0"+splitter[1];
            splitter[1] = temp;
          }
          if (Integer.valueOf(splitter[2])<9){
            String temp = "0"+splitter[2];
            splitter[2] = temp;
          }
          c.set(Integer.valueOf(splitter[0]), Integer.valueOf(splitter[1]), Integer.valueOf(splitter[2]));
          while(!fredlist.contains(LocalDate.parse(BookIDList.getString("bTo"), df))){
          c.add(Calendar.DATE, 1);
          String temp = "\0";
          String prMonth = String.valueOf(c.get(Calendar.MONTH));
          String prDay=String.valueOf(c.get(Calendar.DATE));
          if(Integer.valueOf(c.get(Calendar.MONTH))<=9){
          temp = "0" + c.get(Calendar.MONTH);
          prMonth = temp;
          }
          if(Integer.valueOf(c.get(Calendar.DATE))<=9){
            temp = "0" + c.get(Calendar.DATE);
            prDay = temp;
            }
          if(Integer.valueOf(c.get(Calendar.MONTH))==0)
          prMonth = "01";
          temp = c.get(Calendar.YEAR)+"-"+prMonth+"-"+prDay;
          i = LocalDate.parse(temp, df); 
          fredlist.add(i);
          fredlist.add(i.minusDays(1));
          }
        }
        System.out.println(blist);
        roombook.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   if(fredlist.contains(item))
                   setStyle("-fx-background-color: #ffc0cb;");
                   else setStyle("-fx-background-color: #FFFFFF;");
               }}); 

			}
	}catch (Exception e) {
		e.printStackTrace();
		}
	}

  public void handleFilterChoiceChanged(ActionEvent event) {
    if(obookingfilter.getValue().equals("Date")){
      odatefilter.setVisible(true);
      obookingpaychoice.setVisible(false);
      obookingfiltersearch.setVisible(false);
    } else if(obookingfilter.getValue().equals("Payment status")){
      odatefilter.setVisible(false);
      obookingfiltersearch.setVisible(false);
      obookingpaychoice.setVisible(true);
    } else {
      odatefilter.setVisible(false);
      obookingfiltersearch.setVisible(true);
      obookingpaychoice.setVisible(false);
    }
	}

  public void handleRoomChosen(ActionEvent event){
    if (bBookingCustomer.getValue()!=null){
      triggerBookingDates();
    }
  }

  public void handleEditBookingApplyButton(ActionEvent event){
    DatePicker mrgRqstDate = new DatePicker();
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
    String fromDate = eBookFrom.getValue().format(df);
    String toDate = eBookTo.getValue().format(df);
    String s = "\0";
    try{
    s = eBookCust.getValue().replaceAll("\\D+","");
    } catch (Exception e){
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Booking Editor Error");
      alert.setHeaderText(null);
      alert.setContentText("Please select a customer to book for.");
      alert.showAndWait();
    }
    String[] list = {eBookNo.getValue(), fromDate, toDate, s, bookEdit};
    try{
    if(handler.update("booking", list))
    {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Booking Editor Result");
      alert.setHeaderText(null);
      alert.setContentText("Booking has been succesfully edited.");
      alert.showAndWait();


    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Booking Editor Error");
      alert.setHeaderText(null);
      alert.setContentText("Edit failed. Make sure you've completed all the fields.");
      alert.showAndWait();
    }
   } catch(Exception e){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Booking Editor Error");
    alert.setHeaderText(null);
    alert.setContentText("SQL Fault.");
    System.out.println(e);
    alert.showAndWait();
   }
  }

  public void handlePersonnelChosen(ActionEvent event) throws Exception{
    System.out.println(perschoicebox.getValue());
    persedituserbar.setText(perschoicebox.getValue());
    String sql = "SELECT * from user WHERE account = ?";
    PreparedStatement stat = handler.getLink().prepareStatement(sql);
    stat.setString(1, perschoicebox.getValue());
        ResultSet lst= stat.executeQuery();
        while (lst.next()) {
          persrolebox.setValue(lst.getString("identification"));
  }
}

  public void handleEditPersApplyButton(ActionEvent event){
    String str;
      if((perseditpassbar.getText().length())>=6){
    try{
    String[] list = {persedituserbar.getText(), perseditpassbar.getText(), persrolebox.getValue(), persedituserbar.getText()};
    if(handler.update("user", list))
    {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("User Editing Result");
      alert.setHeaderText(null);
      alert.setContentText("User " + perschoicebox.getValue() + " has been edited succesfully.");
      alert.showAndWait();
      

    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("User Editing Error");
      alert.setHeaderText(null);
      alert.setContentText("Edit failed. Make sure you've completed all the fields.");
      alert.showAndWait();
    }
   } catch(Exception e){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("User Editing Error");
    alert.setHeaderText(null);
    alert.setContentText("SQL Fault.");
    alert.showAndWait();
   }
  } else {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("User Editing Error");
    alert.setHeaderText(null);
    alert.setContentText("The password must be at least 6 characters long.");
    alert.showAndWait();
  }
  }

  public void handleEditPersonnelButton(ActionEvent event) throws Exception{
    EditPersonnelDialog editpr = new EditPersonnelDialog();
    editpr.start(new Stage());

  }

  public void handlebFromDate(ActionEvent event){
    bToDate.setDisable(false);
  }

  public void handleCustomerChosen(ActionEvent event){
    if (bRoomSel.getValue()!=null){
      triggerBookingDates();
    }
  }

  public void handleBookingDateFilter(ActionEvent event) {
    try{
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
    String select = odatefilter.getValue().format(df);
      ebookinglist.getItems().clear();
      String bookIdQuery = "SELECT * FROM bookings";
      PreparedStatement stat = handler.getLink().prepareStatement(bookIdQuery);
      stat = handler.getLink().prepareStatement(bookIdQuery);
        ResultSet BookIDList= stat.executeQuery();
        Statement statement = (handler.getLink()).createStatement();
        while (BookIDList.next()) {
          String name = "\0";
          String customerNameQuery = "SELECT firstname, lastname FROM customer WHERE ID ='"+BookIDList.getString("Customer")+"'";
          ResultSet Customers= statement.executeQuery(customerNameQuery);
          while(Customers.next()){
            name = Customers.getString("firstname") + " " + Customers.getString("lastname");
          }
          int current = doy(select);
          String entry = BookIDList.getString("ID") + ". " + "Room " + BookIDList.getString("Room") + ", " + name + ", " + BookIDList.getString("bFrom") + " -> " + BookIDList.getString("bTo");
          if((current>=doy(BookIDList.getString("bFrom"))) && (current<=doy(BookIDList.getString("bTo")))){
          ebookinglist.getItems().add(entry);  
          }
        } 
      } catch(Exception e){

      }

	}

  public void handleBPayChoice(ActionEvent event) {
    try{
    String bookIdQuery = "\0";
    PreparedStatement stat = handler.getLink().prepareStatement(bookIdQuery);
    ebookinglist.getItems().clear();
    String crit = "\0";
      if(obookingpaychoice.getValue().equals("Paid")){
        crit = "1";
        bookIdQuery = "SELECT * FROM bookings WHERE PAID LIKE ?";
        stat = handler.getLink().prepareStatement(bookIdQuery);
        stat.setString(1, crit);
      } else if(obookingpaychoice.getValue().equals("Unpaid")){
        crit = "0";
        bookIdQuery = "SELECT * FROM bookings WHERE PAID LIKE ?";
        stat = handler.getLink().prepareStatement(bookIdQuery);
        stat.setString(1, crit);
      } else {
        bookIdQuery = "SELECT * FROM bookings";
        stat = handler.getLink().prepareStatement(bookIdQuery);
      }
    System.out.println(crit);
    ResultSet BookIDList= stat.executeQuery();
    Statement statement = (handler.getLink()).createStatement();
    while (BookIDList.next()) {
      String name = "\0";
      String customerNameQuery = "SELECT firstname, lastname FROM customer WHERE ID ='"+BookIDList.getString("Customer")+"'";
      ResultSet Customers= statement.executeQuery(customerNameQuery);
      while(Customers.next()){
        name = Customers.getString("firstname") + " " + Customers.getString("lastname");
      }
      String entry = BookIDList.getString("ID") + ". " + "Room " + BookIDList.getString("Room") + ", " + name + ", " + BookIDList.getString("bFrom") + " -> " + BookIDList.getString("bTo");
      ebookinglist.getItems().add(entry);  
    }
  } catch(Exception e){
    System.out.println(e);
  }
	}

  public void handleRoomComboBox(ActionEvent event) {
		String roomID = eroomid.getValue();
		String roomDetailQuery ="SELECT * FROM room where room_number="+roomID;
		try {
			Statement statement = (handler.getLink()).createStatement();
			ResultSet RoomDetailList= statement.executeQuery(roomDetailQuery);
			
			if(RoomDetailList.next()) {
			Integer beds = Integer.parseInt(RoomDetailList.getString("beds"));
			ebedno.setValue(beds);
			
			String location = RoomDetailList.getString("Location");
			elocation.setText(location);
			
			Integer size = Integer.parseInt(RoomDetailList.getString("size"));
			eroomsize.setValue(size);

      String other = RoomDetailList.getString("other_info");
      try{
			otherinfolabel.setText(other);
      } catch (Exception e){
        System.out.println(e);
      }

			}
	}catch (Exception e) {
		e.printStackTrace();
		}
	}

  public void handleBookingFilterKey(ActionEvent event) {
    String bookIdQuery = "\0";
    try{
    PreparedStatement stat = handler.getLink().prepareStatement(bookIdQuery);
    if(obookingfilter.getValue().equals("Room Number")){
      ebookinglist.getItems().clear();
      if(obookingfiltersearch.getText().length()>0){
        System.out.println("ui");
      bookIdQuery = "SELECT * FROM bookings WHERE Room LIKE ?";
      stat = handler.getLink().prepareStatement(bookIdQuery);
      stat.setString(1, obookingfiltersearch.getText());
      } else {
        System.out.println("e");
      bookIdQuery = "SELECT * FROM bookings";
      stat = handler.getLink().prepareStatement(bookIdQuery);
      }
        ResultSet BookIDList= stat.executeQuery();
        Statement statement = (handler.getLink()).createStatement();
        while (BookIDList.next()) {
          String name = "\0";
          String customerNameQuery = "SELECT firstname, lastname FROM customer WHERE ID ='"+BookIDList.getString("Customer")+"'";
          ResultSet Customers= statement.executeQuery(customerNameQuery);
          while(Customers.next()){
            name = Customers.getString("firstname") + " " + Customers.getString("lastname");
          }
          String entry = BookIDList.getString("ID") + ". " + "Room " + BookIDList.getString("Room") + ", " + name + ", " + BookIDList.getString("bFrom") + " -> " + BookIDList.getString("bTo");
          ebookinglist.getItems().add(entry);  
        }

    } else if(obookingfilter.getValue().equals("Payment status")){

    }  else if(obookingfilter.getValue().equals("Customer")){
      ebookinglist.getItems().clear();
    bookIdQuery = "SELECT * FROM bookings";
    stat = handler.getLink().prepareStatement(bookIdQuery);
      ResultSet BookIDList= stat.executeQuery();
      Statement statement = (handler.getLink()).createStatement();
      while (BookIDList.next()) {
        String name = "\0";
        String customerNameQuery = "SELECT firstname, lastname FROM customer WHERE ID ='"+BookIDList.getString("Customer")+"'";
        ResultSet Customers= statement.executeQuery(customerNameQuery);
        while(Customers.next()){
          name = Customers.getString("firstname") + " " + Customers.getString("lastname");
        }
        String entry = BookIDList.getString("ID") + ". " + "Room " + BookIDList.getString("Room") + ", " + name + ", " + BookIDList.getString("bFrom") + " -> " + BookIDList.getString("bTo");
        if(obookingfiltersearch.getText().length()>0){
        if (name.toLowerCase().contains(obookingfiltersearch.getText().toLowerCase())){
        ebookinglist.getItems().add(entry);  
        }
        } else {
        ebookinglist.getItems().add(entry);  
        }
      }
    }
  } catch (Exception e){
    System.out.println(e);

  }
	}

    public void handleBookingView(MouseEvent event) {
    ObservableList<String> selectedIndices = ebookinglist.getSelectionModel().getSelectedItems();
    String s = "\0";
    for(String o : selectedIndices){
      s = o;
    }
    String [] split = s.split(" ");
    s = split[0].replaceAll("\\D+","");

		String roomDetailQuery ="SELECT * FROM bookings where ID="+s;
		try {
			Statement statement = (handler.getLink()).createStatement();
			ResultSet RoomDetailList= statement.executeQuery(roomDetailQuery);
		
			if(RoomDetailList.next()) {
        String name = "\0";
        String customerQuery = "SELECT firstname, lastname FROM customer WHERE ID="+RoomDetailList.getString("Customer");
        statement = (handler.getLink()).createStatement();
        ResultSet CustomerName= statement.executeQuery(customerQuery);
        if(CustomerName.next()){
          name = CustomerName.getString("firstname") + " " + CustomerName.getString("lastname");
        }
        bRoomLabel.setText( RoomDetailList.getString("Room")); 
        bCustomerLabel.setText(name);
        System.out.println(RoomDetailList.getString("Paid"));
        if(RoomDetailList.getString("Paid").equals("0")){
          bPaidLabel.setText("No");
        } else {
          bPaidLabel.setText("Yes");
        }

			}
	}catch (Exception e) {
		e.printStackTrace();
		}
	}

  public static String getUsername(){
    return username;
  }

  public static String getRole(){
    return role;
  }

  public static String GetBookEditID(){
    return bookEdit;
  }


  @FXML
  private void initialize() 
  {
  }


  
}