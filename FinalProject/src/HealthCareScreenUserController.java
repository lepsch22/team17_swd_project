import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

public class HealthCareScreenUserController {
    /**
     * Full name of workr
     */
    public Label workerFirstAndLastLabel;

    /**
     * username text field
     */
    public TextField usernameField;
    /**
     * User info text area
     */
    public TextArea userInformationTextArea;
    /**
     * Company info text area
     */
    public TextArea companyInformationTextArea;
    /**
     * Username column for table
     */
    public TableColumn<User,String> userNameCol;
    /**
     * Full name column for table
     */
    public TableColumn<User,String>  FirstNameCol;
    /**
     * last name column for table
     */
    public TableColumn<User,String>  lastNameCol;
    /**
     *  Status column for table
     */
    public TableColumn<User,String>  statusCol;
    /**
     *   type of view
     */
    public Label currentView;
    /**
     * Table variable
     */
    public TableView table;
    @FXML
    private TextField searchUserName;
    /**
     * This variable contains all the user objects for the table
     */
    private ObservableList<User> userlist = FXCollections.observableArrayList();

    /**
     *  This method changes vaccination status
     * @param actionEvent
     */
    public void submitVaccination(ActionEvent actionEvent) throws SQLException, NoSuchAlgorithmException {
        String username = usernameField.getText();
        //Change vaccination

        Database.changeStatus(username);
        ResultSet rs= Database.getAll("Users");
        userlist.clear();
        while (rs.next()) {
            userlist.add(new User(rs.getString("UserName"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Status")));
        }
    }


    /**
     * This method initializes the Javafx Gui page
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    @FXML
    public void initialize() throws SQLException, NoSuchAlgorithmException {

        ResultSet rs= Database.getAll("Users");
        workerFirstAndLastLabel.setText("ADMIN");
        while (rs.next()) {
            userlist.add(new User(rs.getString("UserName"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Status")));
        }


        /*}
        /*
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

        FilteredList<User> filtered = new FilteredList<>(userlist , b -> true);

        searchUserName.textProperty().addListener((observable, oldValue, newValue) -> {
            filtered.setPredicate(user -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<User> sortedData = new SortedList<>(filtered);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }





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

    /**
     * This method searches for a user
     * @param keyEvent
     */
    public void searchForUser(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            //Search for username in database on enter

        }

    }

    /**
     * This method switches to organization view
     * @param actionEvent
     * @throws IOException
     */
    public void switchToOrgView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/HealthCareScreenOrg.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
//Ben1 abcd