package sixth;

import java.io.*;

public class demo1 {
    public static void main(String[] args) {
        //输入名字（不带后缀）
        String jpgName = "d:\\1";
        //根据文件路径获取文件
        File file = new File(jpgName+".jpg");
        FileInputStream fi = null;
        FileOutputStream fo = null;
        {
            try {
                //创建输入流
                fi = new FileInputStream(file);
                //创建输出流
                fo = new FileOutputStream(new File(jpgName+"-副本.jpg"));
                int bit = 0 ;
                //循环读取并输出
                while ((bit = fi.read())!=-1){
                    fo.write(bit);
                }
                System.out.println("文件复制完毕");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                {
                    try{//关闭流
                        if(fi != null){
                            fi.close();
                        }
                        if(fo != null){
                            fo.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
