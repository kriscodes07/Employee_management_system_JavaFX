package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

 static   final String url="jdbc:mysql://localhost:3306/employee_db";
   static final String username="root";
   static  final String password="kkr2025";




   public  static  Connection Connect(){
       try{
           return   DriverManager.getConnection(url,username,password);


       }catch (SQLException e){
           e.printStackTrace();
       }

       return null;
   }


}
