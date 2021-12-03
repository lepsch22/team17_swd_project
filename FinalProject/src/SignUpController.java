import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpController {
    /**
     * User button
     */
    @FXML
    private CheckBox userButton;
    /**
     * organization button
     */
    @FXML
    private CheckBox organizationButton;
    /**
     * Id for the sign in username
     */
    @FXML
    private TextField usernameSignUpField;
    /**
     * Id for the sign in password
     */
    @FXML
    private  TextField passwordSignUpField;
    @FXML
    public Label signUpErrorLabel;
    /**
     * passwordSignUpField for sign up field
     */
    @FXML
    private  TextField passwordSignUpField1;
    private ObservableSet<CheckBox> selectedBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedBoxes = FXCollections.observableSet();
    private IntegerBinding numBoxesSelected = Bindings.size(selectedBoxes);
    private final int maxBoxSelect = 1;

    public void signUpAction(ActionEvent actionEvent) {
        String username = usernameSignUpField.getText();
        String password = passwordSignUpField.getText();
        String password2 = passwordSignUpField1.getText();
        if(password.length() > 3 && username.length() > 3){

            if(password.equals(password2)){//Passwords match and login is longer than 3 chars

                //USER SUCCESSFULLY CREATED ACCOUNT SEND TO SERVER.
                //SEND TO NEXT SCREEN EMPLOYER or EMPLOYEE

            }
            else{//Passwords do not match but are longer than 3 chars
                signUpErrorLabel.setStyle("-fx-font-size: 12px");
                signUpErrorLabel.setText("Passwords do not match.");
                usernameSignUpField.setStyle("-fx-border-color: red");
                passwordSignUpField.setStyle("-fx-border-color: red");
                passwordSignUpField1.setStyle("-fx-border-color: red");
            }
        }
        else{
            //PASSWORD IS NOT LONG ENOUGH
            signUpErrorLabel.setStyle("-fx-font-size: 12px");
            signUpErrorLabel.setText("Login credentials must be at least 3 characters.");
            usernameSignUpField.setStyle("-fx-border-color: red");
            passwordSignUpField.setStyle("-fx-border-color: red");
            passwordSignUpField1.setStyle("-fx-border-color: red");
        }
    }
    public void setUpBoxes(CheckBox checkBox){
        if(checkBox.isSelected()){
            selectedBoxes.add(checkBox);
        }else {
            unselectedBoxes.add(checkBox);
        }
        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedBoxes.remove(checkBox);
                selectedBoxes.add(checkBox);
            } else {
                selectedBoxes.remove(checkBox);
                unselectedBoxes.add(checkBox);
            }

        });
    }
    @FXML
    public void initialize(){
        setUpBoxes(organizationButton);
        setUpBoxes(userButton);
        numBoxesSelected.addListener((obs, oldAmount, newAmount) -> {
            if(newAmount.intValue() >= maxBoxSelect){
                unselectedBoxes.forEach(cb -> cb.setDisable(true));
            }
            else{
                unselectedBoxes.forEach(cb -> cb.setDisable(false));
            }
        });

    }
}
