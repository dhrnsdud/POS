package post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;


public class UserConnection {
private static Connection con;
   public static Connection getConnection() {
      // TODO Auto-generated method stub
      try{
         Class.forName("com.mysql.jdbc.Driver");
         con =DriverManager.getConnection("jdbc:mysql://localhost:3306/test3","root","1111");
            
         if(con != null){
            System.out.println("연결성공");
         }else{
            System.out.println("연결 실패");
         }
      }catch (Exception e){
         e.printStackTrace();
      }
      return con;

   }
}