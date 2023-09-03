package app;

import app.comp.DctCompress;
import app.util.ImageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

public class DctCompressController {
    @FXML
    private ImageView srcImg;
    @FXML
    private ImageView compressedImg;
    @FXML
    private Label psnr;
    @FXML
    private Label ssim;
    private FileChooser fileChooser;
    private Stage stage;
    private Mat image;

    public void init() {
        this.fileChooser = new FileChooser();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void loadImage(ActionEvent actionEvent) {
        File file = this.fileChooser.showOpenDialog(this.stage);
        if (file != null) {
            this.image = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
            // show the image
            this.updateImageView(srcImg, ImageUtil.mat2Image(this.image));
            Mat comp = DctCompress.compress(this.image);
            Mat competition = DctCompress.completion(this.image, 8);
            this.updateImageView(compressedImg, ImageUtil.mat2Image(comp));
            double psnr = DctCompress.getPSNR(competition.clone(), comp.clone());
            Scalar ssim = DctCompress.getSSIM(competition.clone(), comp.clone());
            this.psnr.setText("PSNR值为" + psnr);
            this.ssim.setText("SSIM值为" + ssim.val[0]);
        }
    }

    private void updateImageView(ImageView view, Image image) {
        ImageUtil.onFXThread(view.imageProperty(), image);
    }
}
