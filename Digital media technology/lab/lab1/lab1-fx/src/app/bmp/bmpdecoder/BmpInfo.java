package app.bmp.bmpdecoder;

import app.util.FileUtil;

import java.io.BufferedInputStream;

public class BmpInfo {
    private Integer biSize;//4字节，信息头的大小，即40；
    private Integer biWidth;//4字节，以像素为单位说明图像的宽度；
    private Integer biHeight;//4字节，以像素为单位说明图像的高度，同时如果为正，说明位图倒立（即数据表示从图像的左下角到右上角），如果为负说明正向；
    private Short biPlanes;//2字节，为目标设备说明颜色平面数，总被设置为1；
    private Short biBitCount;//2字节，说明比特数/像素数，值有1、2、4、8、16、24、32；
    private Integer biCompression;//4字节，说明图像的压缩类型，最常用的就是0（BI_RGB），表示不压缩；位图的压缩类型，必须是0（不压缩）、1（BI-RLE8压缩类型）或2（BI-RLE4压缩类型）
    private Integer biSizeImages;//4字节，说明位图数据的大小，当用BI_RGB格式时，可以设置为0；
    private Integer biXPelsPerMeter;//表示水平分辨率，单位是像素/米，有符号整数；
    private Integer biYPelsPerMeter;//表示垂直分辨率，单位是像素/米，有符号整数；
    private Integer biClrUsed;//说明位图使用的调色板中的颜色索引数，为0说明使用所有；
    private Integer biClrImportant;//说明对图像显示有重要影响的颜色索引数，为0说明都重要；

    public BmpInfo() {
    }

    public BmpInfo(BufferedInputStream bis) {
        biSize = FileUtil.readInt(bis);
        if (0x28 != biSize) {
            throw new RuntimeException("BmpInfo error");
        }
        biWidth = FileUtil.readInt(bis);
        biHeight = FileUtil.readInt(bis);
        biPlanes = FileUtil.readShort(bis);
        if (biPlanes != 0x0001) {
            throw new RuntimeException("BmpInfo error");
        }
        biBitCount = FileUtil.readShort(bis);
        biCompression = FileUtil.readInt(bis);
        biSizeImages = FileUtil.readInt(bis);
        biXPelsPerMeter = FileUtil.readInt(bis);
        biYPelsPerMeter = FileUtil.readInt(bis);
        biClrUsed = FileUtil.readInt(bis);
        if (biClrUsed != 0x00 && biClrUsed != 2 << biBitCount) {
            throw new RuntimeException("BmpInfo error");
        }
        biClrImportant = FileUtil.readInt(bis);

    }

    public Integer getBiSize() {
        return biSize;
    }

    public void setBiSize(Integer biSize) {
        this.biSize = biSize;
    }

    public Integer getBiWidth() {
        return biWidth;
    }

    public void setBiWidth(Integer biWidth) {
        this.biWidth = biWidth;
    }

    public Integer getBiHeight() {
        return biHeight;
    }

    public void setBiHeight(Integer biHeight) {
        this.biHeight = biHeight;
    }

    public Short getBiPlanes() {
        return biPlanes;
    }

    public void setBiPlanes(Short biPlanes) {
        this.biPlanes = biPlanes;
    }

    public Short getBiBitCount() {
        return biBitCount;
    }

    public void setBiBitCount(Short biBitCount) {
        this.biBitCount = biBitCount;
    }

    public Integer getBiCompression() {
        return biCompression;
    }

    public void setBiCompression(Integer biCompression) {
        this.biCompression = biCompression;
    }

    public Integer getBiSizeImages() {
        return biSizeImages;
    }

    public void setBiSizeImages(Integer biSizeImages) {
        this.biSizeImages = biSizeImages;
    }

    public Integer getBiXPelsPerMeter() {
        return biXPelsPerMeter;
    }

    public void setBiXPelsPerMeter(Integer biXPelsPerMeter) {
        this.biXPelsPerMeter = biXPelsPerMeter;
    }

    public Integer getBiYPelsPerMeter() {
        return biYPelsPerMeter;
    }

    public void setBiYPelsPerMeter(Integer biYPelsPerMeter) {
        this.biYPelsPerMeter = biYPelsPerMeter;
    }

    public Integer getBiClrUsed() {
        return biClrUsed;
    }

    public void setBiClrUsed(Integer biClrUsed) {
        this.biClrUsed = biClrUsed;
    }

    public Integer getBiClrImportant() {
        return biClrImportant;
    }

    public void setBiClrImportant(Integer biClrImportant) {
        this.biClrImportant = biClrImportant;
    }
}
