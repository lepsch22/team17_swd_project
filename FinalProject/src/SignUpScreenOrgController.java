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

public class SignUpScreenOrgController {
    private String username;
    private String password;

    public void setInfo(ArrayList<String> info){
        username = info.get(0);
        password = info.get(1);
    }
    @FXML
    private Label nameInfoWrong;
    @FXML
    private TextField companyName;

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
        for(int i = 0; i < companyNameIn.length(); i++) {
            if (!Character.isLetter(companyNameIn.charAt(i))){
                isGood = false;
            }
        }

        if(isGood){
            //CREATE COMPANY
            if (Database.isUniqueOrg(companyNameIn))
            {
                Database.insertOrg(username,password,companyNameIn);
            }
            else
            {
                nameInfoWrong.setText("This name is already taken");
            }
        }else{
            nameInfoWrong.setText("Only alphabetical characters are allowed.");
        }

    }
}
