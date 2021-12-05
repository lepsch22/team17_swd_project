
import javafx.event.ActionEvent;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.net.MalformedURLException;

public class FileChooserController{
    private SignUpScreenOrgController orgController;
    private SignUpScreenUserController userController;

    public void passClass(SignUpScreenOrgController orgController){
        this.orgController = orgController;
    }
    public void passClass(SignUpScreenUserController userController){
        this.userController = userController;
    }


    public ImageView image;

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
