package hotelproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * EditRoom
 */
public class EditRoom {
    DatabaseContext dbContext = new DatabaseContext();
    Connection conn;
    protected void editRoom(VBox root, GridPane grid) {
        Button submit = new Button("Edit room");
        submit.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            root.getChildren().clear();
            get(root);

            // root.getChildren().clear();
            // root.getChildren().add(get(root));
        }
        });
        // GridPane.setConstraints(submit, 1, 0);
        // grid.getChildren().add(submit);
        grid.add(submit, 3, 0);
    }

    
    private Boolean checkIfListIsClean(ArrayList<String> list){

        boolean pass = false;
        Integer count = 0;
        for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).isBlank()){
                    count++;
                }
            pass = count == list.size()? true : false;
        }

        return pass;

    }

    private GridPane  updateRoomUI(VBox layout, ArrayList<String> list){
        GridPane grid = new GridPane();
        final TextField roomNumber = new TextField();
        Text room  = new Text("Room Number:");
        roomNumber.setPromptText(list.get(3));
        final TextField size  = new TextField();
        Text roomSize  = new Text("Room size(KVM)");
        size.setPromptText(list.get(1));
        size.getText();
        final TextField beds  = new TextField();
        Text bedNumber  = new Text("Number of beds");
        beds.setPromptText(list.get(2));
        beds.getText();
        final TextField location  = new TextField();
        Text roomLocation  = new Text("Location");
        location.setPromptText(list.get(4));
        final TextField other_info  = new TextField();
        Text otherInfo  = new Text("Other information");
        other_info.setPromptText(list.get(5));
        // final TextField room_state  = new TextField();
        ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Booked",
        "Free"
    );
        final ComboBox<String> comboBox = new ComboBox<>(options);
        Text state  = new Text("Booked status");
        // room_state.setPromptText(list.get(6));
        Button saveButton = new Button("Save changes"); 

        grid.add(roomSize, 0, 0);
        grid.add(size, 1, 0);
        grid.add(bedNumber, 0, 1);
        grid.add(beds, 1, 1);
        grid.add(room, 0, 2);
        grid.add(roomNumber, 1, 2);
        grid.add(roomLocation, 0, 3);
        grid.add(location, 1, 3);
        grid.add(otherInfo, 0, 4);
        grid.add(other_info, 1, 4);
        grid.add(state, 0, 5);
        grid.add(comboBox, 1, 5);
        grid.add(saveButton, 1, 6);
        grid.add(backButton(layout), 2, 6);

        Button editButton = new Button(); 
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                layout.getChildren().clear();
                layout.getChildren().add(grid);
            }
        });
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GridPane gridResult = new GridPane();
                ArrayList<String> inputList = new ArrayList<>();
                inputList.add(list.get(0));
                inputList.add(size.getText());
                inputList.add(beds.getText());
                inputList.add(roomNumber.getText());
                inputList.add(location.getText());
                inputList.add(other_info.getText());
                System.out.println(comboBox.getSelectionModel().getSelectedItem());
                if(comboBox.getSelectionModel().getSelectedItem().equals("Booked")){

                     inputList.add("1");
                }else{
                    inputList.add("0");

                }
                Label labelResult = new Label();
                if(checkIfListIsClean(inputList)){
                    if(dbContext.updateDb(inputList)){
                        labelResult = new Label("Room has been updated");
                    }else{
                        labelResult = new Label("Db Update failed");
    
                    }
                }else{
                    labelResult = new Label("Missing text in some fields");

                }
                gridResult.add(labelResult, 1, 1);
                // gridResult.add(backButton(layout), 1, 2);


                layout.getChildren().clear();
                layout.getChildren().add(gridResult);
            }
        });

        return grid;
    }
    private void get(VBox root) {
        GridPane grid = new GridPane();
        final TextField roomNumber = new TextField();
        Text room  = new Text("Enter Room Number");
        roomNumber.setPromptText("Enter room number.");
        Button searchButton = new Button("Search");
        grid.add(room, 0 ,0);
        grid.add(roomNumber,1,0);
        grid.add(searchButton,2,0);
        root.getChildren().add(grid);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                room.setVisible(false);
                roomNumber.setVisible(false);
                searchButton.setVisible(false);
                Label label = new Label();
                try{
                    System.out.println("roomNumber.getText()");
                    Integer.parseInt(roomNumber.getText());
                   Integer id = Integer.valueOf(roomNumber.getText());
                        ArrayList<String> list = dbContext.getRoom(id);
                        if(list.size() == 7){
                            
                            Label size = new Label(list.get(1));
                            Label beds = new Label(list.get(2));
                            Label room_number = new Label(list.get(3));
                            Label location = new Label(list.get(4));
                            Label other_info = new Label(list.get(5));
                            if(list.get(6).equals("1")){
                                Label booked = new Label("Booked");
                                grid.add(booked,2,6);

                            }else{
                                Label booked = new Label("Not booked");
                                grid.add(booked,2,6);
                            }
                         
                        
                            grid.add(room_number,2,1);
                            grid.add(size,2,2);
                            grid.add(beds,2,3);
                            grid.add(location,2,4);
                            grid.add(other_info,2,5);

                            Text roomText  = new Text("Room Number: ");
                            Text sizeText  = new Text("Size: ");
                            Text bedsText  = new Text("Beds: ");
                            Text locationText  = new Text("Location: ");
                            Text otherInfoText  = new Text("Other information: ");
                            Text bookedText  = new Text("Room status: ");
                            
                            grid.add(roomText,1,1);
                            grid.add(sizeText,1,2);
                            grid.add(bedsText,1,3);
                            grid.add(locationText,1,4);
                            grid.add(otherInfoText,1,5);
                            grid.add(bookedText,1,6);
                            Button editButton = new Button("Edit");
                            grid.add(editButton, 1, 8);
                            grid.add(backButton(root), 2, 8);
                            editButton.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    root.getChildren().clear();
                                    root.getChildren().add(updateRoomUI(root, list));
                                }
                            });
                        }else{
                            label = new Label("Room not found!");
                            grid.add(label, 1,2);
                            root.getChildren().clear();
                           root.getChildren().add(grid);
                        }
                } catch(Exception e){
                        label = new Label(e.getMessage());
                        grid.add(label, 1,2);
                        root.getChildren().clear();
                       root.getChildren().add(grid);
                } 
            }
        });
    }

    private Button backButton(VBox layout) {
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GridPane grid = new GridPane();
                addRoom addRoom = new addRoom();
                addRoom.createNewRoom(layout, grid);
                EditRoom edit = new EditRoom();
                edit.editRoom(layout, grid);
                layout.getChildren().clear();
                layout.getChildren().add(grid);
            }});
        return backButton;
    }
    
}