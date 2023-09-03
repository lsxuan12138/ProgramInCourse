import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/lsxuan/log");
        if(!file.exists()) {
            throw new RuntimeException();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));

        for (int i = 0; i < 200; i++) {
            String temp ;
            if((temp=reader.readLine())!=null)System.out.println(temp);
        }
    }
}
