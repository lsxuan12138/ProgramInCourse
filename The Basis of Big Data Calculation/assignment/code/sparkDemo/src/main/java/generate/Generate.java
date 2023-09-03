package generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-12-18 14:50
 */
public class Generate {
    private static final int COUNT = 1000;
    public static void main(String[] args) {
        Set<TwoId> set = new HashSet<>();
        for (int i = 0; i < COUNT; i++) {
            set.add(new TwoId());
        }
        FileWriter fileWriter = null;
        try {
            File file = new File("input/graph1.txt");
            if(file.exists()) {
                file.delete();
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            for (TwoId ids:
                    set) {
                fileWriter.write(ids.toString());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileWriter!=null){
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
