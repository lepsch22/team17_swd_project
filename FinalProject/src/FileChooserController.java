
import javafx.event.ActionEvent;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

public class FileChooserController{


    public ImageView image;

    public void chooseFile(ActionEvent actionEvent) {
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
            image.setImage(new Image(file.getAbsolutePath()));
        }
        String filePath = file.getPath();


    }

}
