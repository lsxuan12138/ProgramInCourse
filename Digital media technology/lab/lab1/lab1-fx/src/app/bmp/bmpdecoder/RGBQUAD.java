package app.bmp.bmpdecoder;

import app.util.FileUtil;

import java.io.BufferedInputStream;

public class RGBQUAD {
    private byte rgbBlue;       //指定蓝色强度
    private byte rgbGreen;      //指定绿色强度
    private byte rgbRed;        //指定红色强度
    private byte rgbReserved;  //保留，设置为0

    public RGBQUAD() {
    }

    public RGBQUAD(BufferedInputStream bis) {

        byte[] buff = FileUtil.readBytes(bis, 4);
        this.rgbBlue = buff[0];
        this.rgbGreen = buff[1];
        this.rgbRed = buff[2];
        this.rgbReserved = buff[3];

    }

    public Pixel toPixel() {
        return new Pixel(rgbBlue < 0 ? rgbBlue + 256 : rgbBlue,
                rgbGreen < 0 ? rgbGreen + 256 : rgbGreen,
                rgbRed < 0 ? rgbRed + 256 : rgbRed);
    }

    public byte getRgbBlue() {
        return rgbBlue;
    }

    public void setRgbBlue(byte rgbBlue) {
        this.rgbBlue = rgbBlue;
    }

    public byte getRgbGreen() {
        return rgbGreen;
    }

    public void setRgbGreen(byte rgbGreen) {
        this.rgbGreen = rgbGreen;
    }

    public byte getRgbRed() {
        return rgbRed;
    }

    public void setRgbRed(byte rgbRed) {
        this.rgbRed = rgbRed;
    }

    public byte getRgbReserved() {
        return rgbReserved;
    }

    public void setRgbReserved(byte rgbReserved) {
        this.rgbReserved = rgbReserved;
    }
}
