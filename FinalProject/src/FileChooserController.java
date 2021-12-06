
import javafx.event.ActionEvent;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.net.MalformedURLException;

/**
 * File Chooser Controller
 */
public class FileChooserController{
    /**
     * orgController controller to pass info
     */
    private SignUpScreenOrgController orgController;
    /**
     * orgController controller to pass info
     */
    private SignUpScreenUserController userController;

    /**
     * passClass
     * @param orgController Controller if
     */
    public void passClass(SignUpScreenOrgController orgController){ this.orgController = orgController; }
    /**
     *  passclass
     * @param userController pass class
     */
    public void passClass(SignUpScreenUserController userController){
        this.userController = userController;
    }


    public ImageView image;

    /**
     * Make the file chooser
     * @param actionEvent open dfile chooser
     * @throws MalformedURLException bad URL
     */
    public void chooseFile(ActionEvent actionEvent) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        Stage primaryStage = new Stage();
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            System.out.println(file.getPath());
            image.setImage(new Image(file.toURI().toURL().toExternalForm()));
        }
        String filePath = file.toURI().toURL().toExternalForm();

        if(orgController != null) {
            orgController.setURL(new Image(filePath), file.getAbsolutePath());
            orgController.companyImage.setImage(new Image(filePath));
        }
        else{
            userController.setURL(new Image(filePath), file.getAbsolutePath());
            userController.userImage.setImage(new Image(filePath));
        }

    }

}
