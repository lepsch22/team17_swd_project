import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.crypto.Data;

public class Driver extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

       // Database.checkPassword("Organizations","4","ewr");
       // Database.delete("Users");
        //Database.delete("Organizations");
       // Database.delete("JBCovidSolutions");

        //
        // Database.insertHealtCare("benyboo","abcde");
//        Database.insertOrg("1","abcdvdfsdaadf","hello","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");
  // Database.insertOrg("4","ewr","there","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");
//        Database.insertOrg("3","abcdvdfdffsdaadf","bye","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");
//        Database.insertOrg("22","3","test","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");
//        Database.insertOrg("21","abc23dvdfsdaadf","this","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");
//        Database.insertOrg("14","ewrrwerw","boop","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");

        //Database.changeStatus("Users","test12");
        // Database.delete("Users");
        Database.insertUser("2","abcsdf","test22","mctesterson");
        //Database.changeStatus("test12");
        Parent root = FXMLLoader.load((getClass().getResource("resource/fxml/StartUpScreen.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
