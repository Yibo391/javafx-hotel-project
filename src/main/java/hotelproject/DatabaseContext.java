package hotelproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DatabaseContext
 */
public class DatabaseContext {
    Connection conn;
    protected Boolean insertRoomIntoDb(String[] list){
        boolean isSuccessfull = false;
        String values = list[0]+","+list[1] + "," + list[2]+","+"'"+list[3]+"'"+","+"'"+list[4]+"'";
        // GridPane grid = new GridPane();
        // Label label = new Label();
        String sql = "INSERT INTO room(size, beds,room_number,location,other_info) VALUES("+values+");";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            if(!conn.getAutoCommit()){
                conn.commit();
            }
            isSuccessfull = true;
        // label = new Label("Room has been added successfully!");

        } catch(SQLException e){
            // label = new Label("Sorry an error occured, ring 112 for support!");
        }

        return isSuccessfull;
    }
    protected Boolean updateDb(ArrayList<String> list){
        boolean isDbUpdated = false;
        String sql = "UPDATE room SET size="+list.get(1)+","+
        "beds="+list.get(2)+","+
        "room_number="+list.get(3)+","+
        "location="+"'"+list.get(4)+"',"+
        "other_info="+"'"+list.get(5)+"',"+
        "booked="+list.get(6)+" "+
        "WHERE room_id="+list.get(0)+";";
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            if(!conn.getAutoCommit()){
                conn.commit();
            }
            isDbUpdated = true;

        } catch(SQLException e){
            System.out.println(sql);
        }
        return isDbUpdated;
    }

    protected ArrayList<String> getRoom(int id) {
        ArrayList<String> list = new ArrayList<>();

        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hoteldb?user=root&password=root&useSSL=false");
            Statement stmt = conn.createStatement();
            // Integer input = Integer.valueOf(roomNumber.getText());
            String sql = "SELECT * FROM room where room_number="+id+";";
                ResultSet result = stmt.executeQuery(sql);
                while(result.next()){
                    list.add(result.getString(1));
                    list.add(result.getString(2));
                    list.add(result.getString(3));
                    list.add(result.getString(4));
                    list.add(result.getString(5));
                    list.add(result.getString(6));
                    list.add(result.getString(7));
                }
        } catch (Exception e) {
            //TODO: handle exception
        }
      return list;  
    }
}