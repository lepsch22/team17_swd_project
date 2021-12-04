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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class UserScreenController {
    @FXML
    private Label firstNameLastName1;
    @FXML
    private Label vaccinated;
    @FXML
    private TextField orgNameSearch;
    @FXML
    private ListView listOfCompanies;
    @FXML
    private ScrollPane scroll;
    @FXML
    private String username;

    public void setInfo(String info){
        username = info;
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
    public void initialize(){
        /*
        scroll.prefWidthProperty().bind(listOfCompanies.widthProperty());
        scroll.prefHeightProperty().bind(listOfCompanies.heightProperty());
        scroll.setContent(listOfCompanies);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

         */


        for (int i = 0; i < 1000; i++) { //List of all orgs
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

        listOfCompanies.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OrganizationInformation.fxml"));
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        OrganizationInfoController controller = loader.getController();


                        //PASS IN THE NAME AND REQUIREMENTS


                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        System.out.println("Double clicked");
                    }
                }
            }
        });


    }
}
