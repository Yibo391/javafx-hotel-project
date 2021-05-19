package application;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class customerDataBaseConnection {
	Connection databaseLink;
	public Connection getConnection() {
		String databaseName="customer";
		String username = "root";
		String password ="Hay@4657";
		String url="jdbc:mysql://localhost:3306/customer";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink= DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return databaseLink;
	}
}
