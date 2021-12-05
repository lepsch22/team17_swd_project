import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HealthCareOrgController {
    @FXML
    private TableColumn userNameCol;
    @FXML
    private TableColumn orgName;
    @FXML
    private TableColumn requirementsCol;
    @FXML
    private Label currentView;
    @FXML
    private TableView table;
    private ObservableList<UserOrg> orglist = FXCollections.observableArrayList(
            new UserOrg("mcdonaldsorgusername","McDonalds","Iowa City"),
            new UserOrg("mcdonaldsorgusername","McDonalds","Iowa City")
    );

    public void submitVaccination(ActionEvent actionEvent) {
    }

    public void switchToOrgView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/HealthCareScreenUser.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchForUser(KeyEvent keyEvent) {
    }
    public void initialize(){
        userNameCol.setCellValueFactory(new PropertyValueFactory<UserOrg,String>("user"));
        orgName.setCellValueFactory(new PropertyValueFactory<UserOrg,String>("orgName"));
        requirementsCol.setCellValueFactory(new PropertyValueFactory<UserOrg,String>("location"));

        table.setItems(orglist);
    }

    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/StartUpScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
