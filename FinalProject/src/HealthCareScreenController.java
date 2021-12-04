import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    public void initialize(){
        //HI joslin I am your friend

    }

    public void backArrow(MouseEvent mouseEvent) {
    }
}
