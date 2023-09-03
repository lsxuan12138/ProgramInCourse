import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        File file = new File("/home/lsxuan/log");
        if(file.exists()) {
            if(!file.delete()){
                throw new RuntimeException();
            }
        }
        if(!file.createNewFile()){
            throw new RuntimeException();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date start = dateFormat.parse("2022-12-01 00:00:00.000");
        //System.out.println(start.getTime());
        long startTimeStamp = start.getTime();
        long end = startTimeStamp + 1000000;
        //long end = startTimeStamp + 10;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file));){
            for (long i = startTimeStamp; i < end; i++) {
                writer.write(dateFormat.format(new Date(i)));
                writer.write(" [INFO] ");
                writer.write(String.format("user%05d",(i-startTimeStamp)/100+1));
                writer.write(" request.\n");
            }

        }

    }
}