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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpScreenUserController {
    /**
     * user sign up image.
     */
    public ImageView userImage;
    /**
     * locationField
     */
    @FXML
    private TextField locationField;
    /**
     * Username
     */
    private String username;
    /**
     * Password
     */
    private String password;

    /**
     * seting info
     * @param info map
     */
    public void setInfo(ArrayList<String> info){
        username = info.get(0);
        password = info.get(1);
    }

    /**
     * setuperror
     * @param errorMessage
     */
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

    /**
     * Signup method button tis passes in a lot of data
     * @param actionEvent actionevent
     * @throws SQLException excpetion
     * @throws NoSuchAlgorithmException algorith exception
     * @throws IOException io excepetion
     */
    public void signUp(ActionEvent actionEvent) throws SQLException, NoSuchAlgorithmException, IOException {
        System.out.println(username);
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(firstName);
        Matcher matcher2 = pattern.matcher(lastName);
        Matcher matcher3 = pattern.matcher(locationField.getText());

        if(matcher.matches() && matcher2.matches() && !firstName.equals("") && !lastName.equals("")){
            if(matcher3.matches() && !locationField.getText().equals("")) {
                if(userImage.getImage() != null) {
                    //CREATE ACCOUNT
                    if (Database.isUniqueUser(username)) {
                        Database.insertUser(username, password, firstName, lastName, locationField.getText(), new FileInputStream(URL));
                        ResultSet rs = Database.returnUserInfo(username);
                        rs.next();
                        Blob blob = rs.getBlob("Picture");
                        if (blob != null) {
                            System.out.println("gyftdcfvgbhjgvf");
                            byte[] arr = blob.getBytes(1, (int) blob.length());

                            FileOutputStream fileOutputStream = new FileOutputStream("FinalProject/src/resource/images/" + username + ".jpg");

                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning");
                            alert.setHeaderText("Shutting down app");
                            alert.setContentText("App needs a restart to apply image.");
                            alert.initModality(Modality.APPLICATION_MODAL);
                            alert.showAndWait();

                            fileOutputStream.write(arr);
                            fileOutputStream.close();
                            System.exit(0);
                        }
                        // Database.insertOrg(username,password,companyNameIn);
                    } else {
                        setupError("This name is already taken");
                    }
                }
             else {
                setupError("Must select an image.");
            }
        } else {
                setupError("Invalid location");
            }
        }
    else{
            setupError("Only alphabetical characters are allowed.");
        }

    }

    /**
     * add the image
     * @param actionEvent button
     * @throws IOException error
     */
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
