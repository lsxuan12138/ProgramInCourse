package sixth;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class demo2 {
    public static void main(String[] args) {
        String str = "规格严格，功夫到家";
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        try {
            //创建输出流
            dataOutputStream = new DataOutputStream(new FileOutputStream("d:\\1.txt",false));
            //以UTF写入str
            dataOutputStream.writeUTF(str);
            //写入str的byte数组
            //防止writeBytes方法将中文字符的高8位截去，造成乱码
            dataOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
            //以字符写入str
            dataOutputStream.writeChars(str);
            //刷新缓冲区
            dataOutputStream.flush();
            System.out.println("输出完毕");
            //创建输入流
            dataInputStream = new DataInputStream(new FileInputStream("d:\\1.txt"));
            //以UTF读入
            System.out.println(dataInputStream.readUTF());
            //读入byte数组
            byte[] b = new byte[3*str.length()];
            for(int i = 0;i<3*str.length();i++){
                b[i]= dataInputStream.readByte();
            }
            //将byte数组转换成字符串String
            System.out.println(new String(b));

            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<9;i++){//读取字符
                sb.append(dataInputStream.readChar());
            }
            //输出
            System.out.println(sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭流
            try{if (dataOutputStream != null) {
                dataOutputStream.close();
            }
                if(dataInputStream !=null ){
                    dataInputStream.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
