package app.bmp.bmpdecoder;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

public class BmpColorPlatte {
    private List<RGBQUAD> items;

    public BmpColorPlatte() {
    }

    public BmpColorPlatte(BufferedInputStream bis, Short biBitCount) {
        items = new ArrayList<>();
        int itemCount;
        switch (biBitCount) {
            case 1:
                itemCount = 2;
                break;
            case 4:
                itemCount = 16;
                break;
            case 8:
                itemCount = 256;
                break;
            case 16:
            case 24:
            case 32:
            default:
                itemCount = 0;
        }
        for (int i = 0; i < itemCount; i++) {
            items.add(new RGBQUAD(bis));
        }
    }

    public List<RGBQUAD> getItems() {
        return items;
    }

    public void setItems(List<RGBQUAD> items) {
        this.items = items;
    }
}
