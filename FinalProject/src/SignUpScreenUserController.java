import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignUpScreenUserController {
    private String username;
    private String password;

    public void setInfo(ArrayList<String> info){
        username = info.get(0);
        password = info.get(1);
    }



    @FXML
    private Label nameInfoWrong;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
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
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        Boolean isGood = true;
        for(int i = 0; i < firstName.length(); i++) {
            if (!Character.isLetter(firstName.charAt(i))){
                isGood = false;
            }
        }
        for(int i = 0; i < lastName.length(); i++) {
            if (!Character.isLetter(lastName.charAt(i))){
                isGood = false;
            }
        }
        if(isGood){
            //CREATE ACCOUNT
            Database.insertUser(username,password,firstName,lastName);
            Parent root = FXMLLoader.load(getClass().getResource("fxml/StartUpScreen.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }else{
            nameInfoWrong.setText("Only alphabetical characters are allowed.");
        }

    }
}
