import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

public class OrganizationScreenController {
    @FXML
    private Label orgName;
    @FXML
    private TextArea requirementsField;
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
    public void setInfo(HashMap<String,String> info){
       orgName.setText(info.get("OrgName"));
       requirementsField.setText(info.get("Regulations"));
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
        Database.addRegulation(requirements,orgName.getText());

    }

}
