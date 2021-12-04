import com.mysql.cj.protocol.Resultset;
import javafx.scene.control.Tab;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class Database {


    public static void insertUser( String user,String pwd, String fName, String lName) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        System.out.println("");
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO  Users (UserName,Password,FirstName,LastName) VALUE ('"+user+"','"+Hasher.hash(pwd)+"','"+fName+"','"+lName+"')");
     }
    public static void insertOrg( String user,String pwd, String name, String Regulations) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        System.out.println("");
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO  Organizations (UserName,Password,OrgName,Regulations) VALUE ('"+user+"','"+Hasher.hash(pwd)+"','"+name+"','"+Regulations+"')");
    }
    public static void insertHealtCare( String user,String pwd) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        System.out.println("");
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO  JBCovidSolutions (UserName,Password) VALUE ('"+user+"','"+Hasher.hash(pwd)+"')");
    }


     public static void delete(String Table) throws SQLException {
         {
             final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
             // Change query
             System.out.println("");
             Connection connection = DriverManager.getConnection(
                     DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
             Statement statement = connection.createStatement();
             statement.executeUpdate("DELETE From "+ Table);
     }


     }
    public static void changeStatus( String user) throws SQLException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        System.out.println("");
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Users SET Status = TRUE WHERE  UserName= '"+user+"'");
    }

    public static boolean checkPassword(String Table,String username,String pwd ) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        System.out.println("");
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT Password FROM "+Table+" WHERE UserName ='"+username+"'");
        rs.next();

        return rs.getString("Password").equals(Hasher.hash(pwd));
    }

}



