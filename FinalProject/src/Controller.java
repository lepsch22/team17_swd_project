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

public class Controller{
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
    private  TextField usernameSignInField;
    /**
     * Id for the sign in password
     */
    @FXML
    private  TextField passwordSignInField;
    /**
     * Id for label that displays if login info is wrong
     */
    @FXML
    private  Label loginInfoWrong;
    /**
     * ID for usernameSignUpField
     */
    @FXML
    private  TextField usernameSignUpField;
    @FXML
    private  TextField passwordSignUpField;
    @FXML
    private  TextField passwordSignUpField1;


    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Sign in button on startupscreen, changes the scene to the login scene
     * @param actionEvent event
     * @throws IOException excepetion
     */
    public void signInAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogInScreen.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sig up button on startup screen, switch scenes to signup scene
     * @param actionEvent event
     */
    public void signUpAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/SignUpScreen.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * log in button on Log in screen
     * @param actionEvent button
     */
    public void loginAction(ActionEvent actionEvent) {
        String enteredUsername = usernameSignInField.getText();
        String enteredPassword = passwordSignInField.getText();
        /*
        if(usernameData.contains(enteredUsername)){
            //grab associated user password
            if(enteredPassword.equals(userPassword)){
                //GOT TO THE NEXT SCENE based on if employer or employee
            }
            else{

            }
        }
         */

    }

    public void backArrow(MouseEvent mouseEvent) {
    }
}
