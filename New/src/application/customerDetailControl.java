package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class customerDetailControl implements Initializable{
	@FXML
	public TextField searchCustomer;
	@FXML
	public Button searchButton;
	@FXML
	public Label customerName;
	@FXML
	public Label updateStatus;
	@FXML
	public ComboBox<String> customerList;
	@FXML
	public TextField emailTextField;
	@FXML
	public TextField roomNumber;
	@FXML
	public TextField guestNumber;
	@FXML
	public DatePicker birthDate;
	@FXML
	public DatePicker dateBookedTo;
	@FXML
	public DatePicker dateBookedFrom;
	
	String CustomerSelected;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		}
	
	
	customerDataBaseConnection databaseConnection =new customerDataBaseConnection();
	Connection database = databaseConnection.getConnection();
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
	
	public void retrieveCustomer(ActionEvent event) {
		updateStatus.setVisible(false);
		
		ObservableList <String> customer_list = FXCollections.observableArrayList();
		String searchIndex =searchCustomer.getText();
		String customerQuery = "SELECT * FROM customerDataTable where first_name Like '%"+searchIndex+"%' or email Like '%"+searchIndex+"%'";		
		try {
			Statement statement = database.createStatement();
			ResultSet customer= statement.executeQuery(customerQuery);
			while (customer.next()) {
				customer_list.add(customer.getString("first_name"));
			}
			customerList.setItems(customer_list);
			customerList.setVisible(true);
	}catch (Exception e) {
		e.printStackTrace();
		}
	
	
	}
	public void updateCustomerInfo(ActionEvent event) {
		String Email=emailTextField.getText();
		String RoomNumber=roomNumber.getText();
		String GuestNumber=guestNumber.getText();
		String BirthDate=birthDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String DateBookedTo=dateBookedTo.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String DateBookedFrom=dateBookedFrom.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String customerQuary ="UPDATE customerDataTable SET"+ "`email` = '"+Email+"',`birth_data`='"+BirthDate+"', `booked_from`='"
				+DateBookedFrom+"',`booked_to`='"+DateBookedTo+"',`number_of_guest`='"+GuestNumber+"',`room_number`='"+RoomNumber+ "' where first_name='"+CustomerSelected+"'";
		try {
			updateStatus.setVisible(true);
			updateStatus.setText("Updating....." );
			Statement statement = database.createStatement();
			int RowUpdated =  statement. executeUpdate(customerQuary);
			if(RowUpdated>0) {
				
				updateStatus.setText("Successfully Updated" );
				
				;
				
				
				
			}
			else {
				updateStatus.setVisible(true);
				updateStatus.setText("Update Failed" );
				
				
			}
			
	}catch (Exception e) {
		e.printStackTrace();
		}
		}

	public void onClickHandler (ActionEvent event) {
		updateStatus.setVisible(false);
		CustomerSelected= customerList.getValue();
		customerName.setText(CustomerSelected);
		String customerQuary ="SELECT * FROM customerDataTable where first_name='"+CustomerSelected+"'";
		try {
			Statement statement = database.createStatement();
			ResultSet Customer= statement.executeQuery(customerQuary);
			
			
			if(Customer.next()) {
			String email= Customer.getString("email");
			emailTextField.setText(email);
			String roomId= Customer.getString("room_number");
			roomNumber.setText(roomId);
			
			String numberOfGuest= Customer.getString("number_of_guest");
			guestNumber.setText(numberOfGuest);
			
			String birth = Customer.getString("birth_data");
			birthDate.setValue(LOCAL_DATE(birth));
			
			String bookingStarted = Customer.getString("booked_from");
			dateBookedFrom.setValue(LOCAL_DATE(bookingStarted));
			
			String bookingEnd = Customer.getString("booked_to");
			dateBookedTo.setValue(LOCAL_DATE(bookingEnd));
			
			
	}
		}catch (Exception e) {
		e.printStackTrace();
		}
	
}
}
		
