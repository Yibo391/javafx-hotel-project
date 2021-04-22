package hotelproject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
public class User {
    private Connection conn = null;

    protected void createNewUser(GridPane grid, VBox root) {
        Button submit = new Button("Create new user");
        submit.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            root.getChildren().add(addUser(root));
        }
        });
        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);
    }
    private Button backButton(VBox layout) {
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GridPane grid = new GridPane();
                User user = new User();
                user.createNewUser(grid, layout);
                layout.getChildren().clear();
                layout.getChildren().add(grid);
            }});

        return backButton;
    }
    private GridPane addUser(VBox root){
        GridPane grid = new GridPane();
        final TextField userName = new TextField();
        Text user  = new Text("User Name");
        userName.setPromptText("user name");
        userName.setPrefColumnCount(10);
        userName.getText();
        final TextField password  = new TextField();
        Text userPassword  = new Text("Password");
        password.setPromptText("password");
        password.getText();
        Text role  = new Text("Role");

        ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Administrator",
        "Reception"
    );
        final ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setValue("Reception");
        
        Button addButton = new Button("Add User");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] list = {userName.getText(),password.getText(),comboBox.getValue()};
                root.getChildren().clear();
                if (checkListForBlanks(list)){
                    list = populateList(list);
                    
                    conn = LoginCheck.connection(3306, "hoteldb", "root","root");
                    root.getChildren().add(insertintoDb(list,conn,root));

                } else{
                    GridPane grid = new GridPane();
                    Text text = new Text("Failed :Missing fields or wrongtext format provided, please check again.");
                    grid.add(text, 2, 3);

                    Button backButton = backButton(root);
                    root.getChildren().addAll(grid, backButton);
                    
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
        String values = "'"+list[0]+"'"+","+"'"+list[1]+"'"+ "," + list[2];
        GridPane grid = new GridPane();
        Label label = new Label();
        String sql = "INSERT INTO user(account, password,role_id) VALUES("+values+");";
        Button backButton = backButton(layout);
        try{
            
            conn = LoginCheck.connection(3306, "hoteldb", "root","root");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            if(!conn.getAutoCommit()){
                conn.commit();
            }
        label = new Label("User has been added successfully!");

        } catch(SQLException e){
            label = new Label("Sorry an error occured, ring 112 for support!");

        }
        GridPane.setConstraints(label ,10, 10);
        grid.getChildren().addAll(label,backButton);
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

                    if(list[i].equals("Administrator")){
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
