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
    public static void insertOrg( String user,String pwd, String name) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        System.out.println("");
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        System.out.println("INSERT INTO  Organizations (UserName,Password,OrgName) VALUE ('"+user+"','"+Hasher.hash(pwd)+"','"+name+"')");
        statement.executeUpdate("INSERT INTO  Organizations (UserName,Password,OrgName) VALUE ('"+user+"','"+Hasher.hash(pwd)+"','"+name+"')");
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
             Connection connection = DriverManager.getConnection(
                     DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
             Statement statement = connection.createStatement();
             statement.executeUpdate("DELETE From "+ Table);
     }


     }
    public static void changeStatus( String user) throws SQLException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Users SET Status = 'Vaccinated' WHERE  UserName= '"+user+"'");
    }

    public static boolean checkPassword(String username,String pwd ) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT Password FROM Users WHERE UserName ='"+username+"'");
        String temp="",temp2="",temp3="";
        if(rs.next()!=false) {
             temp = rs.getString("Password");
        }
        rs=statement.executeQuery("SELECT Password FROM Organizations WHERE UserName ='"+username+"'");
        if(rs.next()!=false) {
             temp2 = rs.getString("Password");
        }
         rs=statement.executeQuery("SELECT Password FROM JBCovidSolutions WHERE UserName ='"+username+"'");
        if(rs.next()!=false) {
            temp3 = rs.getString("Password");
        };
        return temp.equals(Hasher.hash(pwd)) || temp2.equals(Hasher.hash(pwd)) || temp3.equals(Hasher.hash(pwd));
    }
    public static boolean inOrgs(String username) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT UserName FROM Organizations ");
        while (!rs.isLast())
        {
            rs.next();
            if(rs.getString("UserName").equals(username))
            {
                return true;
            }
        }

        return false;
    }
    public static boolean inUser(String username) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT UserName FROM Users ");
        while (!rs.isLast())
        {
            rs.next();
            if(rs.getString("UserName").equals(username))
            {
                return true;
            }
        }

        return false;
    }
    public static boolean isUniqueUser(String username) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT UserName FROM Organizations ");
        while (!rs.isLast())
        {
            rs.next();
            if(rs.getString("UserName").equals(username))
            {
                return false;
            }
        }
        ResultSet rs2=statement.executeQuery("SELECT UserName FROM Users ");

        while (!rs2.isLast())
        {
            rs2.next();
            if(rs2.getString("UserName").equals(username))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isUniqueOrg(String Org) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT OrgName FROM Organizations ");
        while (!rs.isLast())
        {
            rs.next();
            if(rs.getString("OrgName").equals(Org))
            {
                return false;
            }
        }
        return true;
    }

    public static ResultSet returnUserInfo(String username) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=null;
        if (inUser(username))
        {
            System.out.println("User");
             rs=statement.executeQuery("SELECT FirstName,LastName,Status,LoginType " +
                    "FROM Users Where UserName='"+username+"'");
        }
        else if(inOrgs(username))
        {
            System.out.println("Org");
             rs=statement.executeQuery("SELECT OrgName,Regulations,LoginType " +
                    "FROM Organizations Where UserName='"+username+"'");
        }

        return rs;
    }
    public static ResultSet returnOrgInfo(String username) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT OrgName,LoginType,Regulations " +
                "FROM Organizations Where UserName='"+username+"'");
        return rs;
    }

    public static ResultSet getDatabaseNames() throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT OrgName From Organizations");
        return rs;
    }

    public static int count(String Table) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT Count(OrgName) counter From "+Table);
        rs.next();
        return Integer.valueOf(rs.getString("counter"));
    }

    public static void addRegulation(String regulation,String name) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Organizations SET Regulations = '"+regulation+"' WHERE  OrgName= '"+name+"'");


    }

}



