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
        Database.delete("Users");
        Database.delete("Organizations");
        Database.delete("JBCovidSolutions");

        //
        // Database.insertHealtCare("benyboo","abcde");
        //Database.insertOrg("kser","abcd","AMERICANEAGLE","sdkjfbgkajhfkjshbnasjkhvfbdkasjgrejknbufkdsajbfjkldshfbkdsjlgfjkaslbkjasdbnfdlkasn.fbdsajkmnflds");
        //Database.changeStatus("Users","test12");
        // Database.delete("Users");
        //Database.insertUser("test12","abc","test","mctesterson");
        //Database.changeStatus("test12");
        Parent root = FXMLLoader.load((getClass().getResource("resource/fxml/StartUpScreen.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
