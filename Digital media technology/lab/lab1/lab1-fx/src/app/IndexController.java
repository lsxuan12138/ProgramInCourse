package app;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class IndexController {
    private BorderPane borderPane;
    private Stage stage;

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void loadBmp(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("fxml/bmp.fxml"));
        TabPane pane = loader.load();
        BmpController bmpController = loader.getController();
        bmpController.setStage(stage);
        bmpController.init();
        this.borderPane.setCenter(pane);
    }

    public void loadWav(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("fxml/wav.fxml"));
        BorderPane pane = loader.load();
        WavController controller = loader.getController();
        controller.setStage(stage);
        controller.init();
        controller.setSwingNode((SwingNode) pane.getCenter());
        this.borderPane.setCenter(pane);
    }

    public void loadFace(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("fxml/FaceDetection.fxml"));
        BorderPane pane = loader.load();
        FaceDetectionController controller = loader.getController();
        controller.init();
        // set the proper behavior on closing the application
        this.stage.setOnCloseRequest((new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                controller.setClosed();
            }
        }));
        this.borderPane.setCenter(pane);
    }

    public void loadDFT(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("fxml/Fourier.fxml"));
        BorderPane pane = loader.load();
        FourierController controller = loader.getController();
        controller.setStage(this.stage);
        controller.init();
        this.borderPane.setCenter(pane);
    }

    public void loadDCT(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("fxml/dct.fxml"));
        BorderPane pane = loader.load();
        DctController controller = loader.getController();
        controller.setStage(this.stage);
        controller.init();
        this.borderPane.setCenter(pane);
    }

    public void loadDctCom(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("fxml/dctCompress.fxml"));
        BorderPane pane = loader.load();
        DctCompressController controller = loader.getController();
        controller.setStage(this.stage);
        controller.init();
        this.borderPane.setCenter(pane);
    }
}
