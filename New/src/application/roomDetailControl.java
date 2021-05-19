package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class roomDetailControl implements Initializable  {
	
	

	@FXML
	public ComboBox<String> roomDetailComboBox;
	@FXML
	public Label bedtypeLabel;
	@FXML
	public Label priceLebel;
	@FXML
	public Label floorLebel;
	@FXML
	public Label sizeLebel;
	@FXML
	public Label maximumoccupantLebel;
	@FXML
	public Label bookedfromLebel;
	@FXML
	public Label bookedtoLevel;
	
	
	DatabaseConnection databaseConnection =new DatabaseConnection();
	Connection database = databaseConnection.getConnection();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList <String> list = FXCollections.observableArrayList();
		String roomIdQuery = "SELECT RoomID FROM roomDetail";
		try {
				Statement statement = database.createStatement();
				ResultSet RoomIDList= statement.executeQuery(roomIdQuery);
				while (RoomIDList.next()) {
					list.add(RoomIDList.getString("RoomID"));
				}
				roomDetailComboBox.setItems(list);
		}catch (Exception e) {
			e.printStackTrace();
			}
		
		
		}
	public void handleComboBox(ActionEvent event) {
		String roomID= roomDetailComboBox.getValue();
		String roomDetailQuery ="SELECT * FROM roomDetail where RoomID="+roomID;
		try {
			Statement statement = database.createStatement();
			ResultSet RoomDetailList= statement.executeQuery(roomDetailQuery);
			
			
			if(RoomDetailList.next()) {
			String bedType= RoomDetailList.getString("BedType")+" Size";
			bedtypeLabel.setText(bedType);
			String price= Integer.toString(RoomDetailList.getInt("Price"))+" kr";
			priceLebel.setText(price);
			
			String floor = Integer.toString(RoomDetailList.getInt("Floor"))+" th Floor";
			floorLebel.setText(floor);
			
			String size = Integer.toString(RoomDetailList.getInt("Size"))+" Square meter";
			sizeLebel.setText(size);
			
			String maximumOccupant = Integer.toString(RoomDetailList.getInt("MaximumOccupant"))+" Person";
			maximumoccupantLebel.setText(maximumOccupant);
			
			String bookedFrom= RoomDetailList.getString("BookedFrom");
			bookedfromLebel.setText(bookedFrom);
			
			String bookedTO= RoomDetailList.getString("BookedTO");
			bookedtoLevel.setText(bookedTO);
			}
	}catch (Exception e) {
		e.printStackTrace();
		}
	}
}

 