import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpScreenUserController {

    public ImageView userImage;
    @FXML
    private TextField locationField;
    private String username;
    private String password;

    public void setInfo(ArrayList<String> info){
        username = info.get(0);
        password = info.get(1);
    }

    public void setupError(String errorMessage){
        nameInfoWrong.setText(errorMessage);
        firstNameField.setStyle("-fx-border-color: red");
        lastNameField.setStyle("-fx-border-color: red");
    }


    @FXML
    private Label nameInfoWrong;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    private String URL;
    /**
     * Backarrow to other page
     * @param mouseEvent on click
     * @throws IOException Didnt load correctly
     */
    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/SignUpScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This method sets the URL value
     * @param URL
     */
    public void setURL(Image Image, String URL)
    {
        this.URL=URL;
        this.userImage.setImage(Image);

    }

    public void signUp(ActionEvent actionEvent) throws SQLException, NoSuchAlgorithmException, IOException {
        System.out.println(username);
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(firstName);
        Matcher matcher2 = pattern.matcher(lastName);
        Matcher matcher3 = pattern.matcher(locationField.getText());

        if(matcher.matches() && matcher2.matches()){
            if(matcher3.matches()) {
                if(userImage.getImage() != null) {
                    //CREATE ACCOUNT
                    Database.insertUser(username, password, firstName, lastName,locationField.getText());






                    //Insert image to database







                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Shutting down app");
                    alert.setContentText("App needs a restart to apply image.");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.showAndWait();

                    //fileOutputStream.write(arr);
                    //fileOutputStream.close();
                    System.exit(0);

                }
                else{
                    setupError("Must select an image.");
                }
            }
            else{
                setupError("Invalid location");
            }
        }else{
            setupError("Only alphabetical characters are allowed.");
        }

    }

    public void addImage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FileChooser.fxml"));
        Parent root = loader.load();
        FileChooserController controller= loader.getController();
        controller.passClass(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
