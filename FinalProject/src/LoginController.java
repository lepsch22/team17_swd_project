import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
}
