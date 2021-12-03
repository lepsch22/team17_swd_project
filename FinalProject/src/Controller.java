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
import java.util.ArrayList;

public class Controller{
    /**
     * Error signing up
     */
    private Label signUpErrorLabel;
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

    private ArrayList<Scene> sceneVisits;

    /**
     * Sign in button on startupscreen, changes the scene to the login scene
     * @param actionEvent event
     * @throws IOException excepetion
     */
    public void signInSceneSwitch(ActionEvent actionEvent) throws IOException {
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
    public void signUpSceneSwitch(ActionEvent actionEvent) throws IOException {
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

        //If username/password is wrong.
        usernameSignInField.setStyle("-fx-border-color: red");
        passwordSignInField.setStyle("-fx-border-color: red");
        loginInfoWrong.setStyle("-fx-font-size: 12px");
        loginInfoWrong.setText("Login info is incorrect.");

        /*
        if(usernameData.contains(enteredUsername)){
            //grab associated user password
            if(enteredPassword.equals(userPassword)){
                //GOT TO THE NEXT SCENE based on if employer or employee
            }
            else{

            }
        else{

        }
         */

    }


    public void backArrow(MouseEvent mouseEvent) {
        System.out.println("Test");

    }

    public void signUpAction(ActionEvent actionEvent) {
        String username = usernameSignUpField.getText();
        String password = passwordSignUpField.getText();
        String password2 = passwordSignUpField1.getText();
        if(password.length() > 3){

            //PASSWORD MUST BE MORE THAN THREE CHARACTERS
            if(password.equals(password2)){

                //USER SUCCESSFULLY CREATED ACCOUNT SEND TO SERVER.
                //SEND TO NEXT SCREEN EMPLOYER or EMPLOYEE

            }
            else{
                //PASSWORDS ARE NOT THE SAME
            }
        }
        else{
            //PASSWORD IS NOT LONG ENOUGH
        }
    }
}
