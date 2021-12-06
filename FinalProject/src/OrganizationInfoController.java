import com.google.protobuf.Api;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Controller for organization info controller
 */
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
    /**
     * Name of org
     */
    private String nameOfOrg;
    /**
     * Regulations text
     */
    private String requirementText;
    private String userLoc;

    private HashMap<String,String> Map;
    /**
     * SetInf from previous controller
     * @param nameOfOrg name of org
     * @param map
     * @throws SQLException exception
     * @throws NoSuchAlgorithmException bad thing happened
     */
    public void setInfo(String nameOfOrg, String UserLoc, String OrgLoc, HashMap<String, String> map) throws SQLException, NoSuchAlgorithmException, IOException {
        this.userLoc = UserLoc;
        ResultSet rs = Database.getRegulation(nameOfOrg);
        Map=map;
        rs.next();
        System.out.println(UserLoc);
        HashMap ApiVals=API.getStats(UserLoc,OrgLoc);
        orgName.setText(nameOfOrg+", "+rs.getString("Location"));
        requirementTextArea.appendText(rs.getString("Regulations")+"\n\n Location Information:");

        requirementTextArea.appendText("\n\n You are in :  "+userLoc);

        if(ApiVals.containsKey("time") && ApiVals.containsKey("distance"))
        {
            requirementTextArea.appendText("\n\n Distance:\n" + ApiVals.get("distance"));

            requirementTextArea.appendText("\n Around "+ApiVals.get("time")+" by car");
        }
        requirementTextArea.setEditable(false);

        orgIcon.setImage(new Image(String.valueOf(getClass().getResource("images/"+nameOfOrg+".jpg"))) );
    }

    /**
     * Go back to previous window
     * @param mouseEvent on mouse click
     * @throws IOException couldnt load fxml
     */
    public void backArrow(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UserScreen.fxml"));
        UserScreenController controller = new UserScreenController(userLoc,Map);
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
