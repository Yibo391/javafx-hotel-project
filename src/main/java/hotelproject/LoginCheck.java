package hotelproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.TextField;

//yibo 1234
public class LoginCheck {

  public static Connection connection(int port, String database, String user, String password) {
    Connection conn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
      conn = DriverManager
          .getConnection("jdbc:mysql://localhost:" + port + "/" + database, user, password);
    } catch (Exception e) {
      System.out.println(e);
    }
    return conn;
  }

  static Map<String, String> initUI(TextField account, TextField pwd) {
    String userName = account.getText();
    String userPwd = pwd.getText();
    Map<String, String> a = new HashMap<>();
    a.put("loginName", userName);
    a.put("loginPwd", userPwd);
    return a;
  }

  public static boolean checkLogin(Map<String, String> info) {
    boolean loginSucess = false;
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    String name = info.get("loginName");
    String pwd = info.get("loginPwd");
    String database = "userInfo";
    String user = "root";
    String password = "wangyiboo";
    Connection conn = null;
    int port = 3306; //default port, change it depending on your setup
    try {
      conn = connection(3306,database,user,password);
      String sql = "select account,password from user where account=? and password=?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, name);
      ps.setString(2, pwd);
      resultSet = ps.executeQuery();

      while (resultSet.next()) {
        System.out.println(resultSet.getString(1) + "," + resultSet.getString(2));
        loginSucess = true;
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      try {
        conn.close();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      try {
        ps.close();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      try {
        resultSet.close();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return loginSucess;
  }


  public static void changePwd(Map<String, String> info) {
    Connection conn = null;
    PreparedStatement ps = null;
    String name = info.get("loginName");
    String pwd = info.get("loginPwd");
    String user = "root";
    String password = "wangyiboo";
    String database = "userInfo";
    int port = 3306; //default port, change it depending on your setup
    try {
      conn = connection(3306,database,user,password);
      String sql = "update user set password=? where account=?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, pwd);
      ps.setString(2, name);
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      try {
        ps.close();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
  }


}