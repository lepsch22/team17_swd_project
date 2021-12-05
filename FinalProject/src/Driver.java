import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Driver extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
      //  getClass().getResource("images/subway.jpg");
       // Database.checkPassword("Organizations","4","ewr");
       // Database.delete("Users");
        //Database.delete("Organizations");
        //Database.insertUser("Ben22","1234","Ben","Lepsch");
       // Database.insertUser("Joslin12","1234","Joslin","Some");
       // Database.insertOrg("monster","1234","Monster",new FileInputStream("/iahome/s/ss/ssome/Desktop/team17_swd/FinalProject/Monster.jpg"));

        // Database.delete("Organizations");
       // Database.delete("JBCovidSolutions");
       // Database.insertOrg("monster","1234","Monster",new FileInputStream("/iahome/s/ss/ssome/Desktop/team17_swd/FinalProject/src/resource/images/monster.jpg"));

       // File file =new File("/iahome/s/ss/ssome/Desktop/team17_swd/FinalProject/src/subway.jpg");
       // InputStream in= new FileInputStream("images/subway.jpg");
       //  Database.insertOrg("arbys","1234","Arbys", (FileInputStream) in);
        // Database.insertOrg("Spotify","1234","Spotify",new FileInputStream("/iahome/s/ss/ssome/Desktop/team17_swd/FinalProject/src/resource/images/spotify.jpg"));
//        Database.insertOrg("thir123d","1234","Starbucks");
//        Database.insertOrg("fourt21h","1234","Steak n Shake");
//        Database.insertOrg("fift2h","1234","Culvers");
//        Database.insertOrg("sixt1h","1234","Airport");
//        Database.insertOrg("seve312nth","1234","Iowa");
//        Database.insertOrg("eig2131th","1234","One Piece");

        // Database.insertOrg("4","ewr","there","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");
//        Database.insertOrg("3","abcdvdfdffsdaadf","bye","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");
//        Database.insertOrg("22","3","test","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");
//        Database.insertOrg("21","abc23dvdfsdaadf","this","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");
//        Database.insertOrg("14","ewrrwerw","boop","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");

        //Database.changeStatus("Users","test12");
        // Database.delete("Users");
        //Database.insertUser("2","abcsdf","test22","mctesterson");
        //Database.changeStatus("test12");
       Parent root = FXMLLoader.load((getClass().getResource("resource/fxml/StartUpScreen.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
