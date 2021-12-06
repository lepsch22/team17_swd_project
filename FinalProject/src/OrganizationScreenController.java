import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Oragnization Controller
 */
public class OrganizationScreenController {
    @FXML
    private ImageView companyLogo;
    @FXML
    private Label orgName;
    @FXML
    private TextArea requirementsField;

    /**
     * Name of orginization
     */
    private String name;
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
     * Pass info from previous class
     * @param info info from previous class
     */
    public void setInfo(HashMap<String,String> info, Blob blob) throws SQLException, IOException {
        name=info.get("OrgName");
        //System.out.println(info.get("Location"));
       orgName.setText(info.get("OrgName")+", "+info.get("Location"));
       requirementsField.setText(info.get("Regulations"));
       companyLogo.setImage(new Image(String.valueOf(getClass().getResource("images/"+info.get("OrgName")+".jpg"))) );
    }

    /**
     * Add custom images.
     * @param actionEvent button click
     */
    public void addImage(ActionEvent actionEvent) {
    }

    /**
     * submit the requirements sheet.
     * @param actionEvent button click
     * @throws SQLException DQL Error
     * @throws NoSuchAlgorithmException No such algorithm
     */
    public void submitRequirements(ActionEvent actionEvent) throws SQLException, NoSuchAlgorithmException {
        String requirements = requirementsField.getText();
        Database.addRegulation(requirements,name);

    }

}
