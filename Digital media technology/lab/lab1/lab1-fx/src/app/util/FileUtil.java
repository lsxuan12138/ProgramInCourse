package app.util;

import java.io.BufferedInputStream;
import java.io.IOException;

import static sun.nio.ch.IOStatus.EOF;

public class FileUtil {
    public static String readString(BufferedInputStream bis, int len) {
        byte[] buf = new byte[len];
        try {
            if (bis.read(buf) != len)
                throw new IOException("no more data!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buf);
    }

    public static short readShort(BufferedInputStream bis) {
        byte[] buf = new byte[2];
        int res = 0;
        try {
            if (bis.read(buf) != 2)
                throw new IOException("no more data!!!");
            res = (buf[0] & 0xFF) | (((int) buf[1]) << 8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (short) res;
    }

    public static Byte readByte(BufferedInputStream bis) {
        byte[] buf = new byte[1];
        byte res = 0;
        try {
            if (bis.read(buf) != 1)
                throw new IOException("no more data!!!");
            res = buf[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static int readInt(BufferedInputStream bis) {
        int res = 0;
        try {
            int[] l = new int[4];
            for (int i = 0; i < 4; ++i) {
                l[i] = bis.read();
                if (l[i] == EOF) {
                    throw new IOException("no more data!!!");
                }
            }
            res = l[0] | (l[1] << 8) | (l[2] << 16) | (l[3] << 24);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static byte[] readBytes(BufferedInputStream bis, int len) {
        byte[] buf = new byte[len];
        try {
            if (bis.read(buf) != len)
                throw new IOException("no more data!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf;
    }
}
