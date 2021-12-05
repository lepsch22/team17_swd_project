import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
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

    public void signUp(ActionEvent actionEvent) throws SQLException, NoSuchAlgorithmException, IOException {
        System.out.println(username);
        String companyNameIn = companyName.getText();

        Boolean isGood = true;
        //CharSequence inputStr = expression;
        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(companyNameIn);
        if(matcher.matches()){
            //CREATE COMPANY
            if (Database.isUniqueOrg(companyNameIn))
            {
                Database.insertOrg(username,password,companyNameIn,new FileInputStream("/iahome/s/ss/ssome/Desktop/team17_swd/FinalProject/Spotify.jpg"));
                ResultSet rs=Database.returnUserInfo(username);
                rs.next();
                Blob blob= rs.getBlob("Logo");
                if (blob!=null)
                {
                    System.out.println("gyftdcfvgbhjgvf");
                    byte[] arr=blob.getBytes(1,(int)blob.length());

                    FileOutputStream fileOutputStream = new FileOutputStream("FinalProject/src/resource/images/"+companyNameIn+".jpg");

                    fileOutputStream.write(arr);
                    fileOutputStream.close();
                    System.exit(0);
                }
                // Database.insertOrg(username,password,companyNameIn);
                Parent root = FXMLLoader.load(getClass().getResource("fxml/StartUpScreen.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                nameInfoWrong.setText("This name is already taken");
            }
        }else{
            nameInfoWrong.setText("Only alphabetical characters are allowed.");
        }

    }

    public void addImage(ActionEvent actionEvent) throws IOException{


    }}
