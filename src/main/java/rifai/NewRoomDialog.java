package hotelproject;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

/**
 * addRoom
 */
public class NewRoomDialog {
    Connection conn;
    protected void createNewRoom(VBox root, GridPane grid) {
            root.getChildren().add(addroom(root,conn));
    }

    private GridPane addroom(VBox root, Connection conn){
        GridPane grid = new GridPane();
        final TextField roomNumber = new TextField();
        Text room  = new Text(" Room Number");
        roomNumber.setPromptText("Enter room number.");
        roomNumber.setPrefColumnCount(10);
        roomNumber.getText();
        final TextField size  = new TextField();
        Text roomSize  = new Text(" Room size(KVM)");
        size.setPromptText("Enter room size(kvm)");
        size.getText();
        final TextField beds  = new TextField();
        Text bedNumber  = new Text(" Number of beds");
        beds.setPromptText("Enter number of beds");
        beds.getText();
        final TextField location  = new TextField();
        Text roomLocation  = new Text(" Location");
        location.setPromptText(" Enter location");
        location.getText();
        final TextField other_info  = new TextField();
        Text otherInfo  = new Text(" Other information");
        other_info.setPromptText("Enter text here");
        Button addButton = new Button(" Submit");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] list = {size.getText(),beds.getText(),roomNumber.getText(),location.getText(), other_info.getText()};
                root.getChildren().clear();
                if (checkIfIntegerAndNotBlank(list)){
                    root.getChildren().add(insertintoDb(list));

                } else{
                    GridPane grid = new GridPane();
                    Text text = new Text("Operation failed: Missing fields or wrong input format provided.");
                    grid.add(text, 2, 3);
                    root.getChildren().add(grid);
                    
                }
            }

        });  
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
        grid.add(addButton, 1, 5);
        return grid;
    }
    private GridPane insertintoDb(String[] list){
        String values = list[0]+","+list[1] + "," + list[2]+","+"'"+list[3]+"'"+","+"'"+list[4]+"'";
        GridPane grid = new GridPane();
        Label label = new Label();
        String sql = "INSERT INTO room(size, beds, room_number,location,other_info) VALUES("+values+");";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            if(!conn.getAutoCommit()){
                conn.commit();
            }
        label = new Label(" Room has been added successfully! You may safely close this dialog.");

        } catch(SQLException e){
            label = new Label("SQL fault!");
        }
        GridPane.setConstraints(label ,10, 10);
        grid.getChildren().add(label);
        return grid;
    }
    private Boolean checkIfIntegerAndNotBlank(String[] list){

        boolean pass = false;
        Integer count = 0;
        for (int i = 0; i < list.length; i++) {
            
            try {
                if(i < list.length-2){

                    Integer.parseInt(list[i]);
                }
                if (!list[i].isBlank()){
                    count++;
                }
            } catch (Exception e) {
                pass = false;
            }
            pass = count == list.length? true : false;
        }

        return pass;

    }
   
}
