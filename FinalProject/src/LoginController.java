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
import java.sql.SQLException;
import java.util.ArrayList;

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




        if (Database.checkPassword(enteredUsername,enteredPassword))
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UserScreen.fxml"));
            Parent root = loader.load();
            UserScreenController controller = loader.getController();

            ArrayList<String> list = new ArrayList<>();
            list.add(usernameSignInField.getText());

            controller.setInfo(usernameSignInField.getText());

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
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
