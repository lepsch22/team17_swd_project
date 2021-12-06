import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

/**
 * Controller for the sign up FXML
 */
public class SignUpController {
    /**
     * VBOX
     */
    @FXML
    private VBox vbox;
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
    /**
     * Observable set used to check selected boxes
     */
    private ObservableSet<CheckBox> selectedBoxes = FXCollections.observableSet();
    /**
     * Observable set used to check the unselected boxes
     */
    private ObservableSet<CheckBox> unselectedBoxes = FXCollections.observableSet();
    /**
     * An integer binding to keep truck of number of boxes
     */
    private IntegerBinding numBoxesSelected = Bindings.size(selectedBoxes);
    /**
     * maximum amount of boxes able to be selected
     */
    private final int maxBoxSelect = 1;
    /**
     * stage to switch
     */
    private Stage stage;
    /**
     * Scene to switch
     */
    private Scene scene;
    private CheckBox selectedBox;

    /**
     * Use for styling an error
     */
    public void setupError(String errorMessage){

        signUpErrorLabel.setText(errorMessage);
        usernameSignUpField.setStyle("-fx-border-color: red");
        passwordSignUpField.setStyle("-fx-border-color: red");
        passwordSignUpField1.setStyle("-fx-border-color: red");
    }

    /**
     * Valid chars for username/password
     * @param string Username/password
     * @return is the string acceptable
     */
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

    /**
     * Checking all requirements for making an account, based off what account they create they will be sent to a different page.
     * @param actionEvent on button click
     * @throws IOException If FXML file does not load
     * @throws SQLException Issue with DQL database
     * @throws NoSuchAlgorithmException algorithm exception
     */
    public void signUpAction(ActionEvent actionEvent) throws IOException, SQLException, NoSuchAlgorithmException {
        setUpBoxes(organizationButton);
        setUpBoxes(userButton);
        String username = usernameSignUpField.getText();
        String password = passwordSignUpField.getText();
        String password2 = passwordSignUpField1.getText();
        boolean isValidChars1,isValidChars2,isValidChars3;
        isValidChars1 = validChars(username);
        isValidChars2 = validChars(password);
        isValidChars3 = validChars(password2);
        if(password.length() > 3 && username.length() > 3){//Username and password must be larger thn three chars
            if(!isValidChars1||!isValidChars2||!isValidChars3) {//Make sure all three inputs are valid
                setupError("No special characters.");
            }
            else {
                if (password.equals(password2)) {//Passwords match and login is longer than 3 chars
                    if (organizationButton.isSelected()) {
                        //If username unique doo this
                        if (Database.isUniqueUser(username)) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SignUpScreenOrg.fxml"));
                            Parent root = loader.load();
                            SignUpScreenOrgController scene2Controller = loader.getController();

                            ArrayList<String> list = new ArrayList<>();
                            list.add(username);
                            list.add(password);

                            scene2Controller.setInfo(list);

                            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }else{
                            setupError("Username taken.");
                        }
                        //End this

                    }
                    else if (userButton.isSelected()) {
                        //If username unique doo this
                        if (Database.isUniqueUser(username)) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SignUpScreenUser.fxml"));
                            Parent root = loader.load();
                            SignUpScreenUserController scene2Controller = loader.getController();

                            ArrayList<String> list = new ArrayList<>();
                            list.add(username);
                            list.add(password);

                            scene2Controller.setInfo(list);

                            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }else{
                            setupError("Username taken.");
                        }
                        //End this
                    } else if(!organizationButton.isSelected() && !userButton.isSelected()){//No box is selected
                        setupError("Please check an account type.");
                    }

                } else {//Passwords do not match but are longer than 3 chars
                    setupError("Passwords do not match.");
                }
            }
        }
        else{
            //PASSWORD IS NOT LONG ENOUGH
            setupError("Login credentials must be more than 3 characters.");
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

    /**
     * Set up the check boxes to only allow one selected at a time also add listener for future events.
     */
    @FXML
    public void initialize(){
        ObjectProperty<Color> baseColor = new SimpleObjectProperty<>();

        KeyValue keyValue1 = new KeyValue(baseColor, Color.rgb(124, 98, 186));
        KeyValue keyValue2 = new KeyValue(baseColor, Color.rgb(99, 194, 195));
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(2500), keyValue2);
        Timeline timeline = new Timeline(keyFrame1, keyFrame2);

        baseColor.addListener((obs, oldColor, newColor) -> {
            vbox.setStyle(String.format("-gradient-base: #%02x%02x%02x; ",
                    (int)(newColor.getRed()*255),
                    (int)(newColor.getGreen()*255),
                    (int)(newColor.getBlue()*255)));
        });
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


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
