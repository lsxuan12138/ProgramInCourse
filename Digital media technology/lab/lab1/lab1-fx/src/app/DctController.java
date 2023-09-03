package app;

import app.util.ImageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;

import static org.opencv.core.Core.getOptimalDFTSize;

public class DctController {
    // images to show in the view
    @FXML
    private ImageView originalImage;
    @FXML
    private ImageView transformedImage;
    @FXML
    private ImageView antitransformedImage;
    // a FXML button for performing the transformation
    @FXML
    private Button transformButton;
    // a FXML button for performing the antitransformation
    @FXML
    private Button antitransformButton;

    // the main stage
    private Stage stage;
    // the JavaFX file chooser
    private FileChooser fileChooser;
    // support variables
    private Mat image;
    private Mat transformedImg;

    /**
     * Init the needed variables
     */
    protected void init() {
        this.fileChooser = new FileChooser();
        this.transformedImg = new Mat();
        this.image = new Mat();
    }

    /**
     * Load an image from disk
     */
    @FXML
    protected void loadImage() {
        // show the open dialog window
        File file = this.fileChooser.showOpenDialog(this.stage);
        if (file != null) {
            // read the image in gray scale
            this.image = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
            // show the image
            this.updateImageView(originalImage, ImageUtil.mat2Image(this.image));
            // set a fixed width
            this.originalImage.setFitWidth(250);
            // preserve image ratio
            this.originalImage.setPreserveRatio(true);
            // update the UI
            this.transformButton.setDisable(false);


        }
    }

    /**
     * The action triggered by pushing the button for apply the dft to the
     * loaded image
     */
    @FXML
    protected void transformImage() {
        Mat floatMat = new Mat();
        image.convertTo(floatMat, CvType.CV_32FC1);
        Mat padded = optimizeImageDim(floatMat);
        Core.dct(padded, transformedImg);
        Mat tmp = new Mat();
        transformedImg.convertTo(tmp, CvType.CV_8U);
        // show the result of the transformation as an image
        this.updateImageView(transformedImage, ImageUtil.mat2Image(tmp));
        // set a fixed width
        this.transformedImage.setFitWidth(250);
        // preserve image ratio
        this.transformedImage.setPreserveRatio(true);

        // enable the button for performing the antitransformation
        this.antitransformButton.setDisable(false);
        // disable the button for applying the dft
        this.transformButton.setDisable(true);
    }

    private static int getOptimalDCTSize(int N) {
        return 2 * getOptimalDFTSize((N + 1) / 2);
    }

    /**
     * The action triggered by pushing the button for apply the inverse dft to
     * the loaded image
     */
    @FXML
    protected void antitransformImage() {


        Mat restoredImage = new Mat();
        Core.idct(transformedImg, restoredImage);
        // move back the Mat to 8 bit, in order to proper show the result
        restoredImage.convertTo(restoredImage, CvType.CV_8U);

        this.updateImageView(antitransformedImage, ImageUtil.mat2Image(restoredImage));
        // set a fixed width
        this.antitransformedImage.setFitWidth(250);
        // preserve image ratio
        this.antitransformedImage.setPreserveRatio(true);

        // disable the button for performing the antitransformation
        this.antitransformButton.setDisable(true);
    }


    /**
     * Optimize the image dimensions
     *
     * @param image the {@link Mat} to optimize
     * @return the image whose dimensions have been optimized
     */
    private Mat optimizeImageDim(Mat image) {
        // init
        Mat padded = new Mat();
        // get the optimal rows size for dft
        int addPixelRows = getOptimalDCTSize(image.rows());
        // get the optimal cols size for dft
        int addPixelCols = getOptimalDCTSize(image.cols());
        // apply the optimal cols and rows size to the image
        Core.copyMakeBorder(image, padded, 0, addPixelRows - image.rows(), 0, addPixelCols - image.cols(),
                Core.BORDER_CONSTANT, Scalar.all(0));

        return padded;
    }

    /**
     * Update the {@link ImageView} in the JavaFX main thread
     *
     * @param view  the {@link ImageView} to update
     * @param image the {@link Image} to show
     */
    private void updateImageView(ImageView view, Image image) {
        ImageUtil.onFXThread(view.imageProperty(), image);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
