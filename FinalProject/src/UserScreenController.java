import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserScreenController {
    public Label firstNameLastName1;

    public Label vaccinated;
    public TextField orgNameSearch;
    public ListView listOfCompanies;
    private String username;

    public void setInfo(HashMap<String,String> info){
        username = info.get("UserName");
        firstNameLastName1.setText(info.get("FirstName")+" "+info.get("LastName"));
        if (info.get("Status").equals("FALSE"))
        {
            vaccinated.setText("Status: Not Vaccinated");
            vaccinated.setStyle(
                    "-fx-text-fill: red"
            );
        }
        else
        {
            vaccinated.setText("Status: Vaccinated");
            vaccinated.setStyle(
                    "-fx-text-fill: #58D68D"
            );
        }
    }

    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogInScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchCompany(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            if(orgNameSearch.getText().equals("SEARCH ALL ORGS TO FIND ORG")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrganizationInformation.fxml"));
                Parent root = loader.load();
                OrganizationInfoController controller = loader.getController();


                //PASS IN THE NAME AND REQUIREMENTS


                Stage stage = (Stage) ((Node) keyEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }
    }

    @FXML
    public void initialize() throws SQLException, NoSuchAlgorithmException {
        ResultSet rs=Database.getDatabaseNames();
        Integer orgCount=Database.count("Organizations");
        for (int i = 0; i < orgCount; i++) { //List of all orgs
            if (rs.next())
            {
                listOfCompanies.getItems().add(rs.getString("OrgName"));
            }
        }
        //SET FIRST NAME AND LAST NAME




        listOfCompanies.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrganizationInformation.fxml"));
                        Parent root=null ;
                        try {
                             root = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        OrganizationInfoController controller = loader.getController();
                        controller.setInfo((String) listOfCompanies.getSelectionModel().getSelectedItem());

                        //PASS IN THE NAME AND REQUIREMENTS


                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                }
            }
        });

    }
}
