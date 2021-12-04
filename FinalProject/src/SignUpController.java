import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.UnaryOperator;

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
    private Label signUpErrorLabel;
    /**
     * passwordSignUpField for sign up field
     */
    @FXML
    private  TextField passwordSignUpField1;
    private ObservableSet<CheckBox> selectedBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedBoxes = FXCollections.observableSet();
    private IntegerBinding numBoxesSelected = Bindings.size(selectedBoxes);
    private final int maxBoxSelect = 1;
    private Stage stage;
    private Scene scene;

    public void signUpAction(ActionEvent actionEvent) throws IOException {
        String username = usernameSignUpField.getText();
        String password = passwordSignUpField.getText();
        String password2 = passwordSignUpField1.getText();
        String invalidText = " +=-?().-{}[]~`,<>./*";
        if(password.length() > 3 && username.length() > 3){
            if(username.contains(invalidText)||password.contains(invalidText)||password2.contains(invalidText))
            {
                signUpErrorLabel.setText("Invalid character in Login.");
            }
            else {
                if (password.equals(password2)) {//Passwords match and login is longer than 3 chars
                    Parent root = FXMLLoader.load(getClass().getResource("fxml/SignUpScreenVaccination.fxml"));
                    stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                    //USER SUCCESSFULLY CREATED ACCOUNT SEND TO SERVER.
                    //SEND TO NEXT SCREEN EMPLOYER or EMPLOYEE

                } else {//Passwords do not match but are longer than 3 chars
                    signUpErrorLabel.setStyle("-fx-font-size: 12px");
                    signUpErrorLabel.setText("Passwords do not match.");
                    usernameSignUpField.setStyle("-fx-border-color: red");
                    passwordSignUpField.setStyle("-fx-border-color: red");
                    passwordSignUpField1.setStyle("-fx-border-color: red");
                }
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
    /*
        UnaryOperator<TextFormatter.Change> numberValidationFormatter = change -> {
            if (change.getText().matches("\\d+")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                change.setRange( //don't remove any selected text either.
                        change.getRangeStart(),
                        change.getRangeStart()
                );
                return change;
            }
        };


        TextFormatter tf = new TextFormatter(numberValidationFormatter);


        usernameSignUpField.setTextFormatter(tf);

     */

    }

    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/StartUpScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
