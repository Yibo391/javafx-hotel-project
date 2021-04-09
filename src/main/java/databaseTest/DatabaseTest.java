//package databaseTest;
//
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class DatabaseTest {
//
//  // 有sql 注入漏洞
//  public static void main(String[] args) {
//    Map<String,String> info = initUI();
//    boolean loginSucess = login(info);
//    if(loginSucess) {
//      System.out.println("登陆成功");
//    }else {
//      System.out.println("登陆失败");
//    }
//  }
//
//  private static boolean login(Map<String, String> info) {
//    boolean loginSucess = false;
//    Connection conn = null;
//    Statement statement = null;
//    ResultSet resultSet = null;
//    try {
//      Class.forName("com.mysql.jdbc.Driver");
//      String name = info.get("loginName");
//      String pwd = info.get("loginpwd");
//      conn = DriverManager.getConnection("jdbc:mysql://localhost/Wang_Yi?user=root&password=wangyiboo");
//      statement = conn.createStatement();
//      String sql = "select * from user where Name ='" + name + "' and Pwd ='" + pwd + "'";
//      System.out.println(sql);
//      resultSet = statement.executeQuery(sql);
//
//      if (resultSet.next()) {
//        loginSucess = true;
//        System.out.println(name + pwd);
//      }
//
//    } catch (Exception e) {
//      System.out.println(e);
//    }
//
//    return loginSucess;
//  }
//
//  private static Map<String, String> initUI() {
//    Scanner s = new Scanner(System.in);
//    System.out.println("用户名：");
//    String username = s.nextLine();
//    System.out.println("mima：");
//    String userpwd = s.nextLine();
//    Map<String,String> a = new HashMap<>();
//    a.put("loginName",username);
//    a.put("loginpwd",userpwd);
//    return a;
//  }
//}