import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Label;

public class UserScreenController {
    @FXML
    private HashMap<String,String> map;

    //BELOW METHODS HAVE TO BE PUBLIC
    /**
     * fistname last name
     */
    public Label firstNameLastName1;
    /**
     * Vaccinated status
     */
    public Label vaccinated;
    /**
     * Search name for org
     */
    public TextField orgNameSearch;
    /**
     * companyCol
     */
    public TableColumn companyCol;
    /**
     * locationCol
     */
    public TableColumn locationCol;
    /**
     * Tableview
     */
    public TableView table;
    /**
     * username
     */
    private String username;
    /**
     * location of user
     */
    private String userLoc;
    /**
     * picture Image
     */
    public ImageView picture;

    /**
     * user screen controller
     * @param userLoc
     * @param map
     */
    public UserScreenController(String userLoc,HashMap map){
        this.map=map;
        this.userLoc = userLoc;
    }

    /**
     * set info
     * @param info
     */
    public void setInfo(HashMap<String,String> info){
        username = info.get("UserName");

        firstNameLastName1.setText(info.get("FirstName")+" "+info.get("LastName"));
        userLoc=info.get("Location");
        if (info.get("Status").equals("FALSE"))
        {
            vaccinated.setText("Not Vaccinated");
            vaccinated.setStyle(
                    "-fx-text-fill: red;"+
                    "-fx-font-size: 18px"
            );
        }
        else
        {
            vaccinated.setText("Vaccinated");
            vaccinated.setStyle(
                    "-fx-text-fill: #58D68D;"+
                    "-fx-font-size: 18px"
            );
        }
    }

    /**
     * backArrow
     * @param mouseEvent mouseevent
     * @throws IOException eeee
     */
    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogInScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * searchcompany
     * @param keyEvent mouse button
     * @throws IOException error
     */
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

    /**
     * list to get values
     */
    private ObservableList<UserOrg> orglist = FXCollections.observableArrayList(
    );

    /**
     * run on start
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    @FXML
    public void initialize() throws SQLException, NoSuchAlgorithmException {
        ResultSet rs= Database.getAll("Organizations");

        setInfo(map);
        System.out.println(map);
        System.out.println(String.valueOf("images/"+username+".jpg"));

        //picture.setImage(new Image(String.valueOf(getClass().getResource("images/"+map.get("UserName")+".jpg"))) );

        while (rs.next()) {
            orglist.add(new UserOrg(rs.getString("OrgName"),rs.getString("Location")));
        }

        //SET FIRST NAME AND LAST NAME
        companyCol.setCellValueFactory(new PropertyValueFactory<UserOrg,String>("orgName"));
        locationCol.setCellValueFactory(new PropertyValueFactory<UserOrg,String>("location"));
        //table.setItems(orglist);

        FilteredList<UserOrg> filtered = new FilteredList<UserOrg>(orglist , b -> true);

        orgNameSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filtered.setPredicate(user -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (user.getOrgName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<UserOrg> sortedData = new SortedList<>(filtered);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);




        table.setOnMouseClicked(new EventHandler<MouseEvent>(){
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

                        try {
                            OrganizationInfoController controller = loader.getController();
                            UserOrg temp = (UserOrg) table.getSelectionModel().getSelectedItem();

                            controller.setInfo(temp.getOrgName(),userLoc,temp.getLocation(),map);

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //PASS IN THE NAME AND REQUIREMENTS


                        Stage stage = new Stage();
                        stage =   (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                }
            }
        });

    }

}
