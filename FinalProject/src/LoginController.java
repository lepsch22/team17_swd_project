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
     * log in button on Log in screen, checking to make sure user information is in database.
     * @param actionEvent button
     */
    public void loginAction(ActionEvent actionEvent) throws SQLException, NoSuchAlgorithmException, IOException {
        String enteredUsername = usernameSignInField.getText();
        String enteredPassword = passwordSignInField.getText();

        if (Database.checkPassword(enteredUsername,enteredPassword)) {
            ResultSet rs = Database.returnUserInfo(enteredUsername);
            if (rs==null)
            {
                loginInfoWrong.setText("Check case sensitivity");
                usernameSignInField.setStyle("-fx-border-color: red");
                passwordSignInField.setStyle("-fx-border-color: red");
                loginInfoWrong.setStyle("-fx-font-size: 12px");
            }
            else {
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
                } else if (rs.getString("LoginType").equals("Org")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrganizationScreen.fxml"));
                    Parent root = loader.load();
                    OrganizationScreenController controller = loader.getController();

                    HashMap<String, String> map = new HashMap<>();

                    map.put("OrgName", rs.getString("OrgName"));
                    map.put("Regulations", rs.getString("Regulations"));

                    controller.setInfo(map);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HealthCareScreen.fxml"));
                    Parent root = loader.load();
                    System.out.println("Here");
                    HealthCareScreenController controller = loader.getController();


                    // map.put()

                    // controller.setInfo(map);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
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
    /**
     * Backarrow to other page
     * @param mouseEvent on click
     * @throws IOException Didnt load correctly
     */
    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/StartUpScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

