package hotelproject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

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
 * User
 */
public class NewUserDialog {
    private Connection conn = null;


    
    protected void createNewUser(GridPane grid, VBox root) {
            root.getChildren().add(addUser(root));
    }

    public GridPane addUser(VBox root){
        GridPane grid = new GridPane();
        final TextField userName = new TextField();
        Text user  = new Text(" Username");
        userName.setPromptText("Insert username here");
        userName.setPrefColumnCount(10);
        userName.getText();
        final TextField password  = new TextField();
        Text userPassword  = new Text(" Password");
        password.setPromptText("Insert password here");
        password.getText();
        Text role  = new Text(" Role");

        ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Admin",
        "Staff"
    );
        final ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setValue("Staff");
        
        Button addButton = new Button("Add User");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] list = {userName.getText(),password.getText(),comboBox.getValue()};
                root.getChildren().clear();
                if (checkListForBlanks(list)){
                    root.getChildren().add(insertintoDb(list, conn, root));

                } else{
                    GridPane grid = new GridPane();
                    Text text = new Text(" Operation failed: Missing fields or wrong input format provided.");
                    grid.add(text, 2, 3);
                    root.getChildren().add(grid);
                    
                }
            }

        });  
        grid.add(user, 0, 0);
        grid.add(userName, 1, 0);
        grid.add(userPassword, 0, 1);
        grid.add(password, 1, 1);
        grid.add(role, 0, 2);
        grid.add(comboBox, 1, 2);
      
        grid.add(addButton, 1, 4);
        return grid;
    }
    private GridPane insertintoDb(String[] list, Connection conn, VBox layout){
        String values = "\"" + list[0]+ "\"" + ","+"\"" + list[2]+ "\"" + "," + "\"" + list[1]+ "\"";
        GridPane grid = new GridPane();
        Label label = new Label();
        String sql = "INSERT INTO user(account, identification, password) VALUES("+ values +");";
        
        try{
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost/userinfo?user=root&password=root&useSSL=false");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            if(!conn.getAutoCommit()){
                conn.commit();
            }
            label = new Label("User has been added successfully!");

        } catch(SQLException e){
            label = new Label("SQL fault.");

        }
        GridPane.setConstraints(label ,10, 10);
        return grid;
    }
    private boolean checkListForBlanks(String[] list) {
        Integer count = 0;
        boolean isListOk = false;
        for (int i = 0; i < list.length; i++) {
            if (!list[i].isBlank()){
                count++;
            }
            
        }
        if(count == list.length) isListOk = true;
        return isListOk;
            }
    private String[] populateList(String[] list){
        String role = "2";
        for (int i = 0; i < list.length; i++) {
            
            try {
                if(i == list.length-1){

                    if(list[i].equals("admin")){
                        role = "1";
                    }
                }
               
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        String[] new_list = {list[0],list[1], role};
        return new_list;

    }

}
