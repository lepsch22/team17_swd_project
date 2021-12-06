import com.mysql.cj.protocol.Resultset;
import javafx.scene.control.Tab;

import java.io.*;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    /**
     * This method inserts a row in the User table
     * @param user
     * @param pwd
     * @param fName
     * @param lName
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static void insertUser( String user,String pwd, String fName, String lName,String Location,FileInputStream in) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        System.out.println("");
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO  Users (UserName,Password,FirstName,LastName,Location,Picture) VALUE ('"+user+"','"+Hasher.hash(pwd)+"','"+fName+"','"+lName+"','"+Location+"','"+in+"')");
     }

    /**
     * This method inserts a row in the Organization table
     * @param user
     * @param pwd
     * @param name
     * @param in
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     * @throws FileNotFoundException
     */
    public static void insertOrg(String user, String pwd, String name, FileInputStream in,String loc) throws SQLException, NoSuchAlgorithmException, FileNotFoundException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        System.out.println("");
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Organizations VALUES(?,?,?,?,?,?,?)");
        statement.setString(1,name);
        statement.setString(2,"No regulations set");
        statement.setString(3,user);
        statement.setString(4,Hasher.hash(pwd));
        statement.setString(5,"Org");

        statement.setBlob(6,in);
        statement.setString(7,loc);
        statement.execute();}

    /**
     * This method inserts a row in the Healthcare table
     * @param user
     * @param pwd
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static void insertHealtCare( String user,String pwd) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        System.out.println("");
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO  JBCovidSolutions (UserName,Password) VALUE ('"+user+"','"+Hasher.hash(pwd)+"')");
    }


    /**
     * This method deletes a row of a given table
     * @param Table
     * @throws SQLException
     */
     public static void delete(String Table) throws SQLException {
         {
             final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
             // Change query
             Connection connection = DriverManager.getConnection(
                     DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
             Statement statement = connection.createStatement();
             statement.executeUpdate("DELETE From "+ Table+" Where Username= 'Wendys'");
     }


     }

    /**
     * This method changes the vaccination status of a user
     * @param user
     * @throws SQLException
     */
    public static void changeStatus( String user) throws SQLException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Users SET Status = 'Vaccinated' WHERE  UserName= '"+user+"'");
    }

    /**
     * This method checks username and password validity
     * @param username
     * @param pwd
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
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

    /**
     *  This method checks if a username is in the Organization table
     * @param username
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
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

    /**
     *  This method checks if a username is in the Admin table
     * @param username
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static boolean inAdmin(String username) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT UserName FROM JBCovidSolutions ");

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

    /**
     *  This method checks if a username is in the user table
     * @param username
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
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

    /**
     * This method checks if a username already exists in a table
     * @param username
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static boolean isUniqueUser(String username) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT UserName FROM Organizations ");
        try {
            while (!rs.isLast()) {
                rs.next();
                if (rs.getString("UserName").equals(username)) {
                    return false;
                }
            }
            ResultSet rs2 = statement.executeQuery("SELECT UserName FROM Users ");

            while (!rs2.isLast()) {
                rs2.next();
                if (rs2.getString("UserName").equals(username)) {
                    return false;
                }
            }
        }
        catch (SQLException e)
        {
            return true;
        }

        return true;
    }

    /**
     * This method checks if an Organization already exists in the database
     * @param Org
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static boolean isUniqueOrg(String Org) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT OrgName FROM Organizations ");
        try {


        while (!rs.isLast())
        {
            rs.next();
            if(rs.getString("OrgName").equals(Org))
            {
                return false;
            }
        }
        }
        catch (SQLException e)
        {
            return true;
        }
        return true;
    }

    /**
     * This method returs info from the table a username is found in
     * @param username
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static ResultSet returnUserInfo(String username) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=null;
        if (inUser(username))
        {
             rs=statement.executeQuery("SELECT FirstName,LastName,Status,LoginType,Location,Picture " +
                    "FROM Users Where UserName='"+username+"'");
        }
        else if(inOrgs(username))
        {
             rs=statement.executeQuery("SELECT OrgName,Regulations,LoginType,Logo,Location " +
                    "FROM Organizations Where UserName='"+username+"'");
        }
        else if(inAdmin(username))
        {
            rs=statement.executeQuery("SELECT UserName,LoginType " +
                    "FROM JBCovidSolutions Where UserName='"+username+"'");
        }
        else // incorrect case sensitivity
        {
            return null;
        }


        return rs;
    }

    /**
     * This method returns info from The organazation table with a given org
     * @param org
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static ResultSet returnOrgInfo(String org) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT * FROM Organizations Where OrgName='"+org+"'");
        return rs;
    }

    /**
     * This method returns the organization names from the Organization table
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static ResultSet getDatabaseNames() throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT OrgName From Organizations");
        return rs;
    }

    /**
     * This method
     * @param Table
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */

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

    /**
     * This method adds a regulation to the Organization table
     * @param regulation
     * @param name
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static void addRegulation(String regulation,String name) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE Organizations SET Regulations = '"+regulation+"' WHERE  OrgName= '"+name+"'");


    }

    /**
     * This method returns all info from a given table
     * @param Table
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static ResultSet getAll(String Table) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT * From "+Table);
        return rs;
    }

    /**
     * This method returns the regulations of a specific Organization
     * @param name
     * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static ResultSet getRegulation(String name) throws SQLException, NoSuchAlgorithmException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery("SELECT * From Organizations Where OrgName = '"+name+"'");
        return rs;
    }


}



