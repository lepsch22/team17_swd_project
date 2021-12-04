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
import java.util.HashMap;

public class OrganizationInfoController {
    public ImageView orgIcon;
    public Label orgName;
    public TextArea requirementTextArea;
    private String nameOfOrg;
    private String requirementText;

    public void setInfo(String nameOfOrg ){
        this.nameOfOrg=nameOfOrg;
        System.out.println("dsfdsedfsewd "+nameOfOrg);
    }

    public void backArrow(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/UserScreen.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize(){
        System.out.println("heeeeereeee "+nameOfOrg);
        String yourmom = "Heldsf dsf ert erg rsgte ry  ery 54y fdg ertrt4 gewdsg er6y regds grt vdsv rtu ftdv dfhyt hbcv tyj tygfv rtyj uyjnbg fbn uikh jhbf jiu kghf bnytu loiuhhng bfj";
        orgName.setText(nameOfOrg);
        requirementTextArea.appendText(yourmom);

    }

}
