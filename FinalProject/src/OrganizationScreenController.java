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
    public Label orgName;

    public TextArea requirementsField;
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
    public void setInfo(HashMap<String,String> info){
       orgName.setText(info.get("OrgName"));
       requirementsField.setText(info.get("Regulations"));
    }

    public void addImage(ActionEvent actionEvent) {
    }

    public void submitRequirements(ActionEvent actionEvent) throws SQLException, NoSuchAlgorithmException {
        String requirements = requirementsField.getText();
        Database.addRegulation(requirements,orgName.getText());

    }
    @FXML
    public void initialize(){
    }
}
