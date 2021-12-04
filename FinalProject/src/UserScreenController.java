import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class UserScreenController {
    public Label firstNameLastName1;

    public Label vaccinated;
    public TextField orgNameSearch;
    public ListView listOfCompanies;

    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogInScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchCompany(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            if(orgNameSearch.getText().equals("SEARCH ALL ORGS TO FIND ORG")){
                //GO TO ORG PAGE
            }
        }
    }
    @FXML
    public void initialize(){
        for (int i = 0; i < 20; i++) { //List of all orgs
            listOfCompanies.getItems().add("Company"+i);
        }
        //SET FIRST NAME AND LAST NAME
        firstNameLastName1.setText("FIRST NAME HERE" +" "+"LAST NAME HERE");
        //SET Vaccine status
        vaccinated.setText("Vaccinated");

        if (vaccinated.getText().equals("Vaccinated")){
            vaccinated.setStyle(
                    "-fx-text-fill: #58D68D"
            );
        }
        else if(vaccinated.getText().equals("Not Vaccinated")){
            vaccinated.setStyle(
                    "-fx-text-fill: red"
            );
        }
    }
}
