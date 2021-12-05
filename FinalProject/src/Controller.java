import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Startup screen controller
 */
public class Controller{

    /**
     * stage used for change
     */
    private Stage stage;
    /**
     * scene used for change
     */
    private Scene scene;


    /**
     * Sign in button on startupscreen, changes the scene to the login scene
     * @param actionEvent event
     * @throws IOException excepetion
     */
    public void signInSceneSwitch(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/LogInScreen.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sig up button on startup screen, switch scenes to signup scene
     * @param actionEvent event
     */
    public void signUpSceneSwitch(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/SignUpScreen.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    public void backArrow(MouseEvent mouseEvent) {
        System.out.println("Test");

    }



    @FXML
    public void initialize(){

    }

}
