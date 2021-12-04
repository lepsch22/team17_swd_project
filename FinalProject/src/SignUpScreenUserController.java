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

    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/SignUpScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signUp(ActionEvent actionEvent) {
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
            //nameInfoWrong.setText("POGGERS");

        }else{
            nameInfoWrong.setText("Only alphabetical characters are allowed.");
        }

    }
}
