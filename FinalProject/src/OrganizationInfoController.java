import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class OrganizationInfoController {
    /**
     * orgIcon image
     */
    @FXML
    private ImageView orgIcon;
    /**
     * name of org
     */
    @FXML
    private Label orgName;
    /**
     * Text area for mask requirements
     */
    @FXML
    private TextArea requirementTextArea;
    private String nameOfOrg;
    private String requirementText;

    /**
     * SetInf from previous controller
     * @param nameOfOrg name of org
     * @throws SQLException exception
     * @throws NoSuchAlgorithmException bad thing happened
     */
    public void setInfo(String nameOfOrg ) throws SQLException, NoSuchAlgorithmException {
        orgName.setText(nameOfOrg);
        ResultSet rs = Database.getRegulation(orgName.getText());
        rs.next();
        requirementTextArea.appendText(rs.getString("Regulations"));
    }

    /**
     * Go back to previous window
     * @param mouseEvent on mouse click
     * @throws IOException couldnt load fxml
     */
    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/UserScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
