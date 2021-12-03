import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTester {

     public static void main(String args[]) throws SQLException {
        final String DATABASE_URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db017";
        // Change query
        final String SELECT_QUERY =
                "INSERT INTO  Users (Id,UserName,Password,FirstName,LastName) " +
                        "VALUES (2,'jDoe','where','John','Doe')"
                ;
        Connection connection = DriverManager.getConnection(
                DATABASE_URL, "swd_group017", "swd_group017-xyz-21");
        Statement statement = connection.createStatement();
        statement.executeUpdate(SELECT_QUERY);
        // use try-with-resources to connect to and query the database

    }
}



