package second.src;

import java.util.Scanner;

public class demo1 {
    public static void main(String[] args) {
        //声明变量存储 数字 与 字符串
        int i =0;
        String str;
        //创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        //读入 字符串 与 数字
        str = scanner.next();
        i=scanner.nextInt();
        //若数字i大于等于 字符串的长度，结束程序，避免空指针异常NullPointerException
        if(i>=str.length())return;
        //使用subString方法获取子串，拼接，输出
        System.out.println(str.substring(i,str.length())+str.substring(0,i));
    }
}
