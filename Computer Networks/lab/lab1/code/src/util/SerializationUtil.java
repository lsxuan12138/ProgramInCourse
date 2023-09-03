package util;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SerializationUtil
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-10-04 19:21
 */
public class SerializationUtil {
    /**
     * 序列化,List
     */
    public static <T> boolean writeObject(List<T> list, File file) {
        T[] array = (T[]) list.toArray();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(Files.newOutputStream(file.toPath()));
            out.writeObject(array);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 反序列化,List
     */
    public static <E> List<E> readObjectForList(File file) {
        E[] object;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(Files.newInputStream(file.toPath()));
            object = (E[]) in.readObject();
            return new ArrayList<E>(Arrays.asList(object));
        } catch (EOFException e) {
            return new ArrayList<E>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }
}