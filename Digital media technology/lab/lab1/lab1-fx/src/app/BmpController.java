package app;

import app.bmp.arnold.Arnold;
import app.bmp.bmpdecoder.Bitmap;
import app.bmp.bmpdecoder.Pixel;
import app.util.ImageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class BmpController {
    @FXML
    private Label tip;
    @FXML
    private ChoiceBox blockSize;
    @FXML
    private ImageView srcImg1;
    @FXML
    private Button loadImgBtn;
    @FXML
    private ImageView arnoldImg;
    @FXML
    private Button arnoldBtn;
    @FXML
    private ImageView antiArnoldImg;
    @FXML
    private Button antiArnoldBtn;
    @FXML
    private TextField startX;
    @FXML
    private TextField endX;
    @FXML
    private TextField startY;
    @FXML
    private TextField endY;
    @FXML
    private ImageView srcImg;
    @FXML
    private Button srcImgBtn;
    @FXML
    private ImageView histImg;
    @FXML
    private Button loadHistBtn;
    @FXML
    private Label entropy;
    @FXML
    private Label pixel;
    @FXML
    private TextField xLocation;
    @FXML
    private TextField yLocation;
    @FXML
    private Button pixelBtn;
    private FileChooser fileChooser;
    private Stage stage;
    private Mat image;
    private Bitmap bitmap;
    private Arnold arnold;
    private Mat imageWithArnold;
    private Rect arnoldRect;

    public void init() {
        this.srcImgBtn.setDisable(false);
        this.loadHistBtn.setDisable(true);
        this.pixelBtn.setDisable(true);
        this.xLocation.setDisable(true);
        this.yLocation.setDisable(true);
        this.fileChooser = new FileChooser();
        List<String> blockSizes = asList("4", "8", "16", "32", "64");
        this.blockSize.getItems().addAll(blockSizes);
        this.blockSize.setValue("4");
        this.startX.setText("0");
        this.startY.setText("0");
        this.endX.setText("0");
        this.endY.setText("0");
        this.arnoldBtn.setDisable(true);
        this.antiArnoldBtn.setDisable(true);
        //this.fileChooser.setInitialDirectory(new File("E:\\Project\\JavaProjects\\lab1\\src\\image"));
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void loadImg() throws IOException {
        // show the open dialog window
        File file = this.fileChooser.showOpenDialog(this.stage);
        if (file != null) {
            // read the image in gray scale
            //this.image = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
            if (!file.getName().endsWith(".bmp")) throw new RuntimeException();
            this.bitmap = new Bitmap(file);
            this.image = bitmap.toMat();
            // show the image
            this.updateImageView(this.srcImg, ImageUtil.mat2Image(this.image));
            this.updateImageView(this.srcImg1, ImageUtil.mat2Image(this.image));
            this.loadHistBtn.setDisable(false);
            this.pixelBtn.setDisable(false);
            this.xLocation.setDisable(false);
            this.yLocation.setDisable(false);

            Scalar scalar = calcEntropy(image);

            this.entropy.setText(String.format("信息熵(bgr)为:[%.2f,%.2f,%.2f]", scalar.val[0], scalar.val[1], scalar.val[2]));

            this.arnoldBtn.setDisable(false);
        }
    }

    public void loadHist(ActionEvent actionEvent) {
        // split the frames in multiple images
        List<Mat> images = new ArrayList<Mat>();
        Core.split(image, images);

        // set the number of bins at 256
        MatOfInt histSize = new MatOfInt(256);
        // only one channel
        MatOfInt channels = new MatOfInt(0);
        // set the ranges
        MatOfFloat histRange = new MatOfFloat(0, 256);

        // compute the histograms for the B, G and R components
        Mat hist_b = new Mat();
        Mat hist_g = new Mat();
        Mat hist_r = new Mat();

        // B component or gray image
        Imgproc.calcHist(images.subList(0, 1), channels, new Mat(), hist_b, histSize, histRange, false);
        // G and R components (if the image is not in gray scale)
        Imgproc.calcHist(images.subList(1, 2), channels, new Mat(), hist_g, histSize, histRange, false);
        Imgproc.calcHist(images.subList(2, 3), channels, new Mat(), hist_r, histSize, histRange, false);


        // draw the histogram
        int hist_w = 150; // width of the histogram image
        int hist_h = 150; // height of the histogram image
        int bin_w = (int) Math.round(hist_w / histSize.get(0, 0)[0]);

        Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3, new Scalar(0, 0, 0));
        // normalize the result to [0, histImage.rows()]
        Core.normalize(hist_b, hist_b, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
        // for G and R components
        Core.normalize(hist_g, hist_g, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
        Core.normalize(hist_r, hist_r, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());

        // effectively draw the histogram(s)
        for (int i = 1; i < histSize.get(0, 0)[0]; i++) {
            // B component or gray image
            Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(hist_b.get(i - 1, 0)[0])),
                    new Point(bin_w * (i), hist_h - Math.round(hist_b.get(i, 0)[0])), new Scalar(255, 0, 0), 2, 8, 0);
            // G and R components (if the image is not in gray scale)
            Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(hist_g.get(i - 1, 0)[0])),
                    new Point(bin_w * (i), hist_h - Math.round(hist_g.get(i, 0)[0])), new Scalar(0, 255, 0), 2, 8, 0);
            Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(hist_r.get(i - 1, 0)[0])),
                    new Point(bin_w * (i), hist_h - Math.round(hist_r.get(i, 0)[0])), new Scalar(0, 0, 255), 2, 8, 0);

        }

        // display the histogram...
        Image histImg = ImageUtil.mat2Image(histImage);
        updateImageView(this.histImg, histImg);
    }

    public void getPixel(ActionEvent actionEvent) {

        String xStr = this.xLocation.getText();
        String yStr = this.yLocation.getText();
        int x = 0, y = 0;
        try {
            x = Integer.parseInt(xStr);
            y = Integer.parseInt(yStr);
        } catch (NumberFormatException e) {
            this.pixel.setText("坐标输入不合理");
        }
        if (x < 0 || x >= this.image.width() || y < 0 || y >= this.image.height()) this.pixel.setText("坐标输入不合理");
        Pixel pixel1 = this.bitmap.getData()[y][x];
        this.pixel.setText("选取的像素值为" + pixel1);

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

    /**
     * calcEntropy
     *
     * @param input
     * @return bgr
     */

    public static Scalar calcEntropy(Mat input) {
        List<Mat> images = new ArrayList<Mat>();
        Core.split(input, images);

        // set the number of bins at 256
        MatOfInt histSize = new MatOfInt(256);
        // only one channel
        MatOfInt channels = new MatOfInt(0);
        // set the ranges
        MatOfFloat histRange = new MatOfFloat(0, 256);

        // compute the histograms for the B, G and R components
        Mat hist_b = new Mat();
        Mat hist_g = new Mat();
        Mat hist_r = new Mat();

        Imgproc.calcHist(images.subList(0, 1), channels, new Mat(), hist_b, histSize, histRange, false);
        Imgproc.calcHist(images.subList(1, 2), channels, new Mat(), hist_g, histSize, histRange, false);
        Imgproc.calcHist(images.subList(2, 3), channels, new Mat(), hist_r, histSize, histRange, false);
        //frequency
        float f0 = 0, f1 = 0, f2 = 0;
        for (int i = 0; i < 256; i++) {
            f0 += hist_b.at(Float.class, i, 0).getV();
            f1 += hist_g.at(Float.class, i, 0).getV();
            f2 += hist_r.at(Float.class, i, 0).getV();
        }
        //entropy
        Scalar e = new Scalar(0, 0, 0);
        float p0 = 0, p1, p2;
        for (int i = 0; i < 256; i++) {
            p0 = Math.abs(hist_b.at(Float.class, i, 0).getV()) / f0;
            p1 = Math.abs(hist_g.at(Float.class, i, 0).getV()) / f1;
            p2 = Math.abs(hist_r.at(Float.class, i, 0).getV()) / f2;
            if (p0 != 0) e.val[0] -= p0 * Math.log(p0) / Math.log(2);
            if (p1 != 0) e.val[1] -= p1 * Math.log(p1) / Math.log(2);
            if (p2 != 0) e.val[2] -= p2 * Math.log(p2) / Math.log(2);
        }
        return e;
    }

    public void loadSrcImg(ActionEvent actionEvent) throws IOException {
        this.loadImg();
    }

    public void arnold(ActionEvent actionEvent) {
        String blockSizeStr = (String) this.blockSize.getValue();
        int blockSize = Integer.parseInt(blockSizeStr);
        //System.out.println(blockSize);
        String startXStr = startX.getText();
        String startYStr = startY.getText();
        String endXStr = endX.getText();
        String endYStr = endY.getText();
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        try {
            startX = Integer.parseInt(startXStr);
            startY = Integer.parseInt(startYStr);
            endX = Integer.parseInt(endXStr);
            endY = Integer.parseInt(endYStr);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            this.tip.setText("坐标信息错误");
        }
        Mat image = this.image.clone();
        if (startX < 0 || startX >= image.width()
                || startY < 0 || startY >= image.height()
                || endX < 0 || endX >= image.width()
                || endY < 0 || endY >= image.height()
                || startX > endX
                || startY > endY) this.tip.setText("坐标信息错误");

        else if ((endX - startX) / blockSize == 0 || (endY - startY) / blockSize == 0) {
            this.arnold = new Arnold(image, blockSize, blockSize, 5, 3, 4);
            arnold.completion();
            Mat ret = arnold.arnold();
            imageWithArnold = ret.clone();
            arnoldRect = new Rect(0, 0, imageWithArnold.width(), imageWithArnold.height());
            updateImageView(arnoldImg, ImageUtil.mat2Image(ret));
        } else {
            if ((endX - startX) % blockSize != 0) {
                endX = endX - ((endX - startX) % blockSize);
            }
            if ((endY - startY) % blockSize != 0) {
                endY = endY - ((endY - startY) % blockSize);
            }
            if (endX - startX < endY - startY) endY = startY + (endX - startX);
            else endX = startX + (endY - startY);
            this.endX.setText(String.valueOf(endX));
            this.endY.setText(String.valueOf(endY));
            Mat in = image.submat(startY, endY, startX, endX);
            this.arnold = new Arnold(in, blockSize, blockSize, 5, 3, 4);
            Mat ret = arnold.arnold();
            Mat tmp = image.clone();
            arnoldRect = new Rect(startX, startY, (endX - startX), (endY - startY));
            ret.copyTo(new Mat(tmp, new Rect(startX, startY, (endX - startX), (endY - startY))));
            updateImageView(arnoldImg, ImageUtil.mat2Image(tmp));
            imageWithArnold = tmp.clone();
        }
        this.arnoldBtn.setDisable(true);
        this.antiArnoldBtn.setDisable(false);
        this.blockSize.setDisable(true);
        this.startX.setDisable(true);
        this.startY.setDisable(true);
        this.endX.setDisable(true);
        this.endY.setDisable(true);
    }

    public void antiArnold(ActionEvent actionEvent) {
        Mat in = imageWithArnold.submat(arnoldRect);
        Mat ret = this.arnold.inverseArnold(in, 5);
        ret.copyTo(new Mat(imageWithArnold, arnoldRect));
        updateImageView(antiArnoldImg, ImageUtil.mat2Image(imageWithArnold));
        this.arnoldBtn.setDisable(false);
        this.antiArnoldBtn.setDisable(true);
        this.blockSize.setDisable(false);
        this.startX.setDisable(false);
        this.startY.setDisable(false);
        this.endX.setDisable(false);
        this.endY.setDisable(false);
    }
}
