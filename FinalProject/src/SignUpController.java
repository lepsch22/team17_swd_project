import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
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
    private CheckBox selectedBox;
    public void setupError(){

    }
    public boolean validChars(String string){
        String invalidText = " "+"+=-?().-{}[]~`,<>./*%"+'"'+"'";
        boolean isGood = true;
        for(int i = 0; i < string.length(); i++) {
            if (invalidText.contains(String.valueOf(string.charAt(i)))){
                System.out.println(string.charAt(i));
                isGood = false;
            }
        }
        return isGood;
    }
    public void signUpAction(ActionEvent actionEvent) throws IOException {
        String username = usernameSignUpField.getText();
        String password = passwordSignUpField.getText();
        String password2 = passwordSignUpField1.getText();
        boolean isValidChars1,isValidChars2,isValidChars3;
        isValidChars1 = validChars(username);
        isValidChars2 = validChars(password);
        isValidChars3 = validChars(password2);
        if(password.length() > 3 && username.length() > 3){
            if(!isValidChars1||!isValidChars2||!isValidChars3) {
                signUpErrorLabel.setStyle("-fx-font-size: 12px");
                signUpErrorLabel.setText("No special characters.");
                usernameSignUpField.setStyle("-fx-border-color: red");
                passwordSignUpField.setStyle("-fx-border-color: red");
                passwordSignUpField1.setStyle("-fx-border-color: red");
            }
            else {
                if (password.equals(password2)) {//Passwords match and login is longer than 3 chars
                    if (organizationButton.isSelected()) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SignUpScreenOrg.fxml"));
                        Parent root = loader.load();
                        SignUpScreenOrgController scene2Controller = loader.getController();

                        ArrayList<String> list =  new ArrayList<>();
                        list.add(username);
                        list.add(password);

                        scene2Controller.setInfo(list);

                        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        //GO TO ORG SCENE
                        //SET ACCOUNT TYPE TO ORG
                    } else if (userButton.isSelected()) {
                        //NEXT STAGE TO USER SCREEN
                        //SET TYPE TO USER
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SignUpScreenUser.fxml"));
                        Parent root = loader.load();
                        SignUpScreenUserController scene2Controller = loader.getController();

                        ArrayList<String> list =  new ArrayList<>();
                        list.add(username);
                        list.add(password);

                        scene2Controller.setInfo(list);

                        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }

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
            selectedBox = checkBox;
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

    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/StartUpScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
