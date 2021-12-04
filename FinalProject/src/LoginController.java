import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginController {
    /**
     * Id for signInButton
     */
    @FXML
    private Button signInButton;
    /**
     * Id for button sign up
     */
    @FXML
    private  Button signUpButton;
    /**
     * Id for the sign in username
     */
    @FXML
    private TextField usernameSignInField;
    /**
     * Id for the sign in password
     */
    @FXML
    private  TextField passwordSignInField;
    /**
     * Id for label that displays if login info is wrong
     */
    @FXML
    private Label loginInfoWrong;

    /**
     * log in button on Log in screen
     * @param actionEvent button
     */
    public void loginAction(ActionEvent actionEvent) throws SQLException, NoSuchAlgorithmException, IOException {
        String enteredUsername = usernameSignInField.getText();
        String enteredPassword = passwordSignInField.getText();




        if (Database.checkPassword(enteredUsername,enteredPassword)) {
            ResultSet rs = Database.returnUserInfo(enteredUsername);
            try {
                rs.next();


            if (rs.getString("LoginType").equals("User")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UserScreen.fxml"));
                Parent root = loader.load();
                UserScreenController controller = loader.getController();

                HashMap<String, String> map = new HashMap<>();


                map.put("UserName", enteredUsername);
                map.put("FirstName", rs.getString("FirstName"));
                map.put("LastName", rs.getString("LastName"));
                map.put("Status", rs.getString("Status"));

                controller.setInfo(map);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

            else if(rs.getString("LoginType").equals("Org"))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrganizationScreen.fxml"));
                Parent root = loader.load();
                OrganizationScreenController controller = loader.getController();

                HashMap<String, String> map = new HashMap<>();

               // map.put()

               // controller.setInfo(map);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }

            else if(rs.getString("LoginType").equals("Admin"))
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HealthCareScreen.fxml"));
                Parent root = loader.load();
                HealthCareScreenController controller = loader.getController();

                HashMap<String, String> map = new HashMap<>();

                // map.put()

                // controller.setInfo(map);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }
        catch (NullPointerException e)
        {
            loginInfoWrong.setText("Check your syntax");
            usernameSignInField.setStyle("-fx-border-color: red");
            passwordSignInField.setStyle("-fx-border-color: red");
            loginInfoWrong.setStyle("-fx-font-size: 12px");
        }
        }
        else
        {
            loginInfoWrong.setText("Login info is incorrect.");
            usernameSignInField.setStyle("-fx-border-color: red");
            passwordSignInField.setStyle("-fx-border-color: red");
            loginInfoWrong.setStyle("-fx-font-size: 12px");
        }

    }

    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/StartUpScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

