package app.bmp.bmpdecoder;

import app.util.FileUtil;

import java.io.BufferedInputStream;

public class BmpHeader {

    private static final String BF_TYPE = "BM";
    private String bfType;//2字节，文件类型；
    private Integer bfSize;//4字节，文件大小；
    private Short bfReserved1;//2字节，保留，必须设置为0；
    private Short bfReserved2;//2字节，保留，必须设置为0；
    private Integer bfOffBits;//4字节，从头到位图数据的偏移；

    public BmpHeader() {
    }

    public BmpHeader(BufferedInputStream bis) {
        this.bfType = FileUtil.readString(bis, 2);
        if (!BF_TYPE.equals(this.bfType)) {
            throw new RuntimeException("BmpHeader error");
        }
        bfSize = FileUtil.readInt(bis);
        bfReserved1 = FileUtil.readShort(bis);
        bfReserved2 = FileUtil.readShort(bis);
        if (bfReserved1 != 0 || bfReserved2 != 0) {
            throw new RuntimeException("BmpHeader error");
        }
        bfOffBits = FileUtil.readInt(bis);
    }

    public String getBfType() {
        return bfType;
    }

    public void setBfType(String bfType) {
        this.bfType = bfType;
    }

    public Integer getBfSize() {
        return bfSize;
    }

    public void setBfSize(Integer bfSize) {
        this.bfSize = bfSize;
    }

    public Short getBfReserved1() {
        return bfReserved1;
    }

    public void setBfReserved1(Short bfReserved1) {
        this.bfReserved1 = bfReserved1;
    }

    public Short getBfReserved2() {
        return bfReserved2;
    }

    public void setBfReserved2(Short bfReserved2) {
        this.bfReserved2 = bfReserved2;
    }

    public Integer getBfOffBits() {
        return bfOffBits;
    }

    public void setBfOffBits(Integer bfOffBits) {
        this.bfOffBits = bfOffBits;
    }
}

