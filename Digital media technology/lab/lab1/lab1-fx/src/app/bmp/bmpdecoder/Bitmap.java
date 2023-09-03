package app.bmp.bmpdecoder;

import app.util.FileUtil;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Bitmap {
    private BmpHeader header;
    private BmpInfo info;
    private BmpColorPlatte platte;
    //BGR顺序
    private Pixel[][] data;

    public Bitmap() {
    }

    public Bitmap(String url) throws IOException {
        this(new BufferedInputStream(Files.newInputStream(Paths.get(url))));
    }

    public Bitmap(File file) throws IOException {
        this(new BufferedInputStream(Files.newInputStream(file.toPath())));
    }

    public Bitmap(BufferedInputStream bis) throws IOException {
        this.header = new BmpHeader(bis);
        this.info = new BmpInfo(bis);
        this.platte = new BmpColorPlatte(bis, info.getBiBitCount());

        int bytesPerLine = ((info.getBiWidth() * info.getBiBitCount() + 31) >> 5) << 2;
        int imageSize = bytesPerLine * info.getBiHeight();
        if (info.getBiSizeImages() != 0 && imageSize != info.getBiSizeImages()) {
            throw new RuntimeException("BmpHeader error");
        }
        data = generateData(bis);
        if (bis != null) bis.close();
    }

    private Pixel[][] generateData(BufferedInputStream bis) throws IOException {
        int biBitCount = info.getBiBitCount();
        //int biCompression = info.getBiCompression();
        int height = info.getBiHeight();
        int width = info.getBiWidth();

        Pixel[][] pixels = new Pixel[height][width];
        int skip = 4 - ((width * biBitCount) >> 3) & 3;

        switch (biBitCount) {
            case 1:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j += 8) {
                        byte bit = FileUtil.readByte(bis);
                        int b1 = (bit >> 7) & 0b1;
                        int b2 = (bit >> 6) & 0b1;
                        int b3 = (bit >> 5) & 0b1;
                        int b4 = (bit >> 4) & 0b1;
                        int b5 = (bit >> 3) & 0b1;
                        int b6 = (bit >> 2) & 0b1;
                        int b7 = (bit >> 1) & 0b1;
                        int b8 = (bit) & 0b1;
                        RGBQUAD rgbGuad1 = platte.getItems().get(b1);
                        RGBQUAD rgbGuad2 = platte.getItems().get(b2);
                        RGBQUAD rgbGuad3 = platte.getItems().get(b3);
                        RGBQUAD rgbGuad4 = platte.getItems().get(b4);
                        RGBQUAD rgbGuad5 = platte.getItems().get(b5);
                        RGBQUAD rgbGuad6 = platte.getItems().get(b6);
                        RGBQUAD rgbGuad7 = platte.getItems().get(b7);
                        RGBQUAD rgbGuad8 = platte.getItems().get(b8);
                        pixels[i][j] = rgbGuad1.toPixel();
                        pixels[i][j + 1] = rgbGuad2.toPixel();
                        pixels[i][j + 2] = rgbGuad3.toPixel();
                        pixels[i][j + 3] = rgbGuad4.toPixel();
                        pixels[i][j + 4] = rgbGuad5.toPixel();
                        pixels[i][j + 5] = rgbGuad6.toPixel();
                        pixels[i][j + 6] = rgbGuad7.toPixel();
                        pixels[i][j + 7] = rgbGuad8.toPixel();

                    }
                    if (bis.read(new byte[skip]) < 0) throw new RuntimeException("BmpHeader error");
                }
                break;
            case 2:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j += 4) {
                        byte bit = FileUtil.readByte(bis);
                        int b1 = (bit >> 6) & 0b11;
                        int b2 = (bit >> 4) & 0b11;
                        int b3 = (bit >> 2) & 0b11;
                        int b4 = (bit) & 0b11;
                        RGBQUAD rgbGuad1 = platte.getItems().get(b1);
                        RGBQUAD rgbGuad2 = platte.getItems().get(b2);
                        RGBQUAD rgbGuad3 = platte.getItems().get(b3);
                        RGBQUAD rgbGuad4 = platte.getItems().get(b4);
                        pixels[i][j] = rgbGuad1.toPixel();
                        pixels[i][j + 1] = rgbGuad2.toPixel();
                        pixels[i][j + 2] = rgbGuad3.toPixel();
                        pixels[i][j + 3] = rgbGuad4.toPixel();
                    }
                    if (bis.read(new byte[skip]) < 0) throw new RuntimeException("BmpHeader error");
                }
                break;
            case 4:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j += 2) {
                        byte bit = FileUtil.readByte(bis);
                        int b1 = (bit >> 4) & 0b1111;
                        int b2 = (bit) & 0b1111;
                        RGBQUAD rgbGuad1 = platte.getItems().get(b1);
                        RGBQUAD rgbGuad2 = platte.getItems().get(b2);
                        pixels[i][j] = rgbGuad1.toPixel();
                        pixels[i][j + 1] = rgbGuad2.toPixel();
                    }
                    if (bis.read(new byte[skip]) < 0) throw new RuntimeException("BmpHeader error");
                }
                break;
            case 8:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        byte bit = FileUtil.readByte(bis);
                        int b1 = (bit) & 0xff;
                        RGBQUAD rgbGuad1 = platte.getItems().get(b1);
                        pixels[i][j] = rgbGuad1.toPixel();
                    }
                    if (bis.read(new byte[skip]) < 0) throw new RuntimeException("BmpHeader error");
                }
                break;
            case 16:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        byte[] bytes = FileUtil.readBytes(bis, 2);
                        if ((bytes[3] & 0b1) == 0) {
                            int b = (bytes[0] & 0xFF) | (((int) bytes[1]) << 8);
                            int b1 = (b >> 11) & 0b11111;
                            int b2 = (b >> 6) & 0b11111;
                            int b3 = (b >> 1) & 0b11111;
                            pixels[i][j] = new Pixel(b3, b2, b1);
                        } else {
                            int b = (bytes[0] & 0xFF) | (((int) bytes[1]) << 8);
                            int b1 = (b >> 11) & 0b11111;
                            int b2 = (b >> 5) & 0b111111;
                            int b3 = (b) & 0b11111;
                            pixels[i][j] = new Pixel(b3, b2, b1);
                        }
                    }
                    if (bis.read(new byte[skip]) < 0) throw new RuntimeException("BmpHeader error");
                }
                break;
            case 24:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        byte[] bytes = FileUtil.readBytes(bis, 3);
                        pixels[i][j] = new Pixel(
                                bytes[2] < 0 ? bytes[2] + 256 : bytes[2],
                                bytes[1] < 0 ? bytes[1] + 256 : bytes[1],
                                bytes[0] < 0 ? bytes[0] + 256 : bytes[0]
                        );
                    }
                    if (bis.read(new byte[skip]) < 0) throw new RuntimeException("BmpHeader error");
                }
                break;
            case 32:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        byte[] bytes = FileUtil.readBytes(bis, 4);
                        pixels[i][j] = new Pixel(
                                bytes[2] < 0 ? bytes[2] + 256 : bytes[2],
                                bytes[1] < 0 ? bytes[1] + 256 : bytes[1],
                                bytes[0] < 0 ? bytes[0] + 256 : bytes[0],
                                bytes[3] < 0 ? bytes[3] + 256 : bytes[3]
                        );
                    }
                    if (bis.read(new byte[skip]) < 0) throw new RuntimeException("BmpHeader error");
                }
                break;
        }
        return pixels;
    }

    public static final int BLUE = 0;
    public static final int GREEN = 1;
    public static final int RED = 2;

    public byte[][] getHeft(int color) {
        byte[][] result = new byte[info.getBiHeight()][info.getBiWidth()];
        for (int i = 0; i < info.getBiHeight(); i++) {
            for (int j = 0; j < info.getBiWidth(); j++) {
                if (color == BLUE) result[i][j] = (byte) data[i][j].getRgbBlue();
                if (color == GREEN) result[i][j] = (byte) data[i][j].getRgbGreen();
                if (color == RED) result[i][j] = (byte) data[i][j].getRgbRed();
            }
        }
        return result;
    }

    public BmpHeader getHeader() {
        return header;
    }

    public void setHeader(BmpHeader header) {
        this.header = header;
    }

    public BmpInfo getInfo() {
        return info;
    }

    public void setInfo(BmpInfo info) {
        this.info = info;
    }

    public BmpColorPlatte getPlatte() {
        return platte;
    }

    public void setPlatte(BmpColorPlatte platte) {
        this.platte = platte;
    }

    public Pixel[][] getData() {
        return data;
    }

    public void setData(Pixel[][] data) {
        this.data = data;
    }

    public Mat toMat() {
        Mat mat;
        int height = info.getBiHeight();
        int width = info.getBiWidth();
        if (info.getBiBitCount() == 32) {
            mat = new Mat(height, width, CvType.CV_8UC4);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    mat.at(Byte.class, height - i - 1, j).setV4c(data[i][j].toMatTuple4());
                }
            }
        } else {
            mat = new Mat(height, width, CvType.CV_8UC3);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    mat.at(Byte.class, height - i - 1, j).setV3c(data[i][j].toMatTuple3());
                }
            }
        }
        return mat;
    }

    public static void main(String[] args) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        File file = new File("src/image/test.bmp");
        Bitmap bitmap = new Bitmap(file);
        Mat img = Imgcodecs.imread("src/image/test.bmp");
        Mat test = bitmap.toMat();
        System.out.println("img=" + img.dump());
        System.out.println("test=" + test.dump());
        Imgcodecs.imwrite("src/image/test-1.bmp", test);
    }
}







