import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
        String message="";
        ResultSet rs= Database.getAll("Users");

        while (rs.next())
        {
            message+=rs.getString("UserName")+" "+rs.getString("FirstName")+" "+rs.getString("LastName"
            )+" "+rs.getString("Status")+"\n";

        }
        userInformationTextArea.setText(message);
    }

    public void backArrow(MouseEvent mouseEvent) {
    }
}
//Ben1 abcd