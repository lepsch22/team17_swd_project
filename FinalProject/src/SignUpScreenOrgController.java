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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
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

/**
 * Sign up screen for organizations
 */
public class SignUpScreenOrgController {
    public ImageView companyImage;
    public TextField companyName1;
    /**
     * username passed in
     */
    private String username;
    /**
     * Password passed in
     */
    private String password;

    /**
     * Pass inf from other scene
     * @param info information
     */
    public void setInfo(ArrayList<String> info){
        username = info.get(0);
        password = info.get(1);
    }
    public void setupError(String errorMessage){
        nameInfoWrong.setText(errorMessage);
        companyName.setStyle("-fx-border-color: red");
        companyName1.setStyle("-fx-border-color: red");
    }

    /**
     * This is the image URL
     */
    private String URL;
    /**
     * error checking
     */
    @FXML
    private Label nameInfoWrong;
    /**
     * companyName input
     */
    @FXML
    private TextField companyName;


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
    public void setURL(Image Image,String URL)
    {
        this.URL=URL;
        this.companyImage.setImage(Image);

    }

    /**
     * This method controls the sign up action
     * @param actionEvent button click
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public void signUp(ActionEvent actionEvent) throws SQLException, NoSuchAlgorithmException, IOException {
        String companyNameIn = companyName.getText();


        //CharSequence inputStr = expression;
        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(companyNameIn);
        Matcher matcher2 = pattern.matcher(companyName1.getText());
        if(matcher.matches()&& companyImage.getImage() != null&& !companyNameIn.equals("")){
            if(matcher2.matches() && !companyName1.getText().equals("")) {
                //CREATE COMPANY

                if (Database.isUniqueOrg(companyNameIn)) {
                    Database.insertOrg(username,password,companyNameIn,new FileInputStream(URL),companyName1.getText());
                    ResultSet rs = Database.returnUserInfo(username);
                    rs.next();
                    Blob blob = rs.getBlob("Logo");
                    if (blob != null) {
                        System.out.println("gyftdcfvgbhjgvf");
                        byte[] arr = blob.getBytes(1, (int) blob.length());

                        FileOutputStream fileOutputStream = new FileOutputStream("FinalProject/src/resource/images/" + companyNameIn + ".jpg");

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
            else {//Valid location
                setupError("Enter a valid location.");

            }

        }
        else{
            setupError("Invalid syntax/Add image");
        }


    }

    /**
     * This method adds an image file
     * @param actionEvent
     * @throws IOException
     */
    public void addImage(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FileChooser.fxml"));
        Parent root = loader.load();
        FileChooserController controller= loader.getController();
        controller.passClass(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();


    }}
