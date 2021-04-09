package hotelproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.TextField;

public class LoginCheck {

  static Map<String, String> initUI(TextField account, TextField pwd) {
    String userName = account.getText();
    String userPwd = pwd.getText();
    Map<String, String> a = new HashMap<>();
    a.put("loginName", userName);
    a.put("loginpwd", userPwd);
    return a;
  }

  public static boolean checkLogin(Map<String, String> info) {
    boolean loginSucess = false;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String name = info.get("loginName");
      String pwd = info.get("loginpwd");
      conn = DriverManager.getConnection("jdbc:mysql://localhost/userInfo?user=root&password=wangyiboo");

      String sql = "select account,password from user where account=? and password=?";
      ps = conn.prepareStatement(sql);
      ps.setString(1,name);
      ps.setString(2,pwd);
      resultSet = ps.executeQuery();
      System.out.println(conn);

      while (resultSet.next()) {
        System.out.println(resultSet.getString(1)+","+resultSet.getString(2));
        loginSucess = true;
      }
    } catch (Exception e) {
      System.out.println(e);
    }finally {
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
}
