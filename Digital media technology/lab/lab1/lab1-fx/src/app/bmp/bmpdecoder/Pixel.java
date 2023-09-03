package app.bmp.bmpdecoder;

import org.opencv.core.Mat;

import java.awt.*;

public class Pixel {
    private int rgbBlue;       //指定蓝色强度
    private int rgbGreen;      //指定绿色强度
    private int rgbRed;        //指定红色强度
    private int alpha;

    public Pixel() {
    }

    public Pixel(int rgbRed, int rgbGreen, int rgbBlue) {
        this.rgbBlue = rgbBlue;
        this.rgbGreen = rgbGreen;
        this.rgbRed = rgbRed;
        this.alpha = Integer.MAX_VALUE;
    }

    public Pixel(int rgbRed, int rgbGreen, int rgbBlue, int alpha) {
        this.rgbBlue = rgbBlue;
        this.rgbGreen = rgbGreen;
        this.rgbRed = rgbRed;
        this.alpha = alpha;
    }

    public Color toColor() {
        if (alpha > 255 || alpha < 0) return new Color(rgbRed, rgbGreen, rgbBlue);
        else return new Color(rgbRed, rgbGreen, rgbBlue, alpha);
    }

    @Override
    public String toString() {
        return "Pixel{RGB:" + rgbRed + "," + rgbGreen + "," + rgbBlue + ((alpha > 255 || alpha < 0) ? "" : "alpha=" + alpha) + "}";
    }

    public int getRgbBlue() {
        return rgbBlue;
    }

    public void setRgbBlue(int rgbBlue) {
        this.rgbBlue = rgbBlue;
    }

    public int getRgbGreen() {
        return rgbGreen;
    }

    public void setRgbGreen(int rgbGreen) {
        this.rgbGreen = rgbGreen;
    }

    public int getRgbRed() {
        return rgbRed;
    }

    public void setRgbRed(int rgbRed) {
        this.rgbRed = rgbRed;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public Mat.Tuple4<Byte> toMatTuple4() {
        return new Mat.Tuple4<>((byte) rgbBlue, (byte) rgbGreen, (byte) rgbRed, (byte) alpha);
    }

    public Mat.Tuple3<Byte> toMatTuple3() {
        return new Mat.Tuple3<>((byte) rgbBlue, (byte) rgbGreen, (byte) rgbRed);
    }
}
