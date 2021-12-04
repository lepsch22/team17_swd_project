import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    public void submitVaccination(ActionEvent actionEvent) {
        String username = usernameField.getText();
        //Change vaccination
    }
    @FXML
    public void initialize() throws SQLException, NoSuchAlgorithmException {
        //HI joslin I am your friend
        workerFirstAndLastLabel.setText("Admin Acess");
       // workerFirstAndLastLabel.set

        String message="";
        ResultSet rs= Database.getAll("Users");

        message+=String.format("%20s %25s %25s %25s", "Username", "First Name", "Last Name", "Status")+"\n";
        //        "UserName    FirstName    LastName     Status\n";
        while (rs.next())
        {
            message+=String.format("%20s %30s %25s %30s",rs.getString("UserName"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("LoginType"))+"\n";
                /*    "     "+rs.getString("UserName")+"          "+rs.getString("FirstName")+"       "+rs.getString("LastName"
            )+"       "+rs.getString("Status")+"\n";*/
        }
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
            }
                /*    "     "+rs.getString("UserName")+"          "+rs.getString("FirstName")+"       "+rs.getString("LastName"
            )+"       "+rs.getString("Status")+"\n";*/
        }
        companyInformationTextArea.setText(message);
    }

    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogInScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
//Ben1 abcd