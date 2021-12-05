import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HealthCareScreenController {
    public Label workerFirstAndLastLabel;

    public TextField usernameField;
    public TextArea userInformationTextArea;
    public TextArea companyInformationTextArea;
    public TableColumn<User,String> userNameCol;
    public TableColumn<User,String>  FirstNameCol;
    public TableColumn<User,String>  lastNameCol;
    public TableColumn<User,String>  statusCol;
    public Label currentView;
    public TableView table;

    public void submitVaccination(ActionEvent actionEvent) {
        String username = usernameField.getText();
        //Change vaccination
    }

    @FXML
    public void initialize() throws SQLException, NoSuchAlgorithmException {/*
        //HI joslin I am your friend
        workerFirstAndLastLabel.setText("Admin Acess");
       // workerFirstAndLastLabel.set

        String message="";
        ResultSet rs= Database.getAll("Users");

        message+=String.format("%20s %25s %25s %25s", "Username", "First Name", "Last Name", "Status")+"\n";
        //        "UserName    FirstName    LastName     Status\n";
        while (rs.next())
        {
            message+=String.format("%20s %30s %25s %30s",rs.getString("UserName"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("LoginType"))+"\n";*/
                /*    "     "+rs.getString("UserName")+"          "+rs.getString("FirstName")+"       "+rs.getString("LastName"
            )+"       "+rs.getString("Status")+"\n";*/
        /*}

        userInformationTextArea.setText(message);

        message="";
        rs= Database.getAll("Organizations");

        message+=String.format("%10s %20s %20s %40s", "Username", "Name", "Status", "Regulations")+"\n";
        //        "UserName    FirstName    LastName     Status\n";
        while (rs.next())
        {
            try
            {
                rs.getString("Regulations");
                message+=String.format("%10s %20s %20s %40s",rs.getString("UserName"),rs.getString("OrgName"),rs.getString("LoginType"),rs.getString("Regulations"))+"\n";
            }
            catch (NullPointerException e)
            {
                message+=String.format("%10s %20s %20s %40s",rs.getString("UserName"),rs.getString("OrgName"),rs.getString("LoginType"),rs.getString("regulations"))+"\n";
            }*/
                /*    "     "+rs.getString("UserName")+"          "+rs.getString("FirstName")+"       "+rs.getString("LastName"
            )+"       "+rs.getString("Status")+"\n";*/
        /*}
        companyInformationTextArea.setText(message);*/

        //userNameCol.("Testermctersterson");
        //lastNameCol.setText("Mcdonalds");
        //FirstNameCol.setText("Joe");
        //statusCol.setText("Vaccinated");

        userNameCol.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        statusCol.setCellValueFactory(new PropertyValueFactory<User,String>("vaccine"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<User,String>("last"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory<User,String>("first"));
        table.setItems(list);
    }
    public ObservableList<User> list = FXCollections.observableArrayList(
            new User("joey123#","Joe","Biden","Not Vaccinated"),
            new User("benny123#","Ben","Joe","Vaccinated")
    );





    /**
     * Backarrow to other page
     * @param mouseEvent on click
     * @throws IOException Didnt load correctly
     */
    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogInScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchForUser(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            //Search for username in database on enter

        }

    }

    public void switchToOrgView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogInScreen.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
//Ben1 abcd