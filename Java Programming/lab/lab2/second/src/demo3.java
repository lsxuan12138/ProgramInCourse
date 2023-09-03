package second.src;

import java.util.Scanner;

public class demo3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //读入字符串
        String str = scanner.next();
        //分别记录L数量，R数量，子串数量
        int l=0,r=0,n=0;
        for(int i=0;i<str.length();i++){//对每个字符遍历
            if('R'==str.charAt(i)){
                //如果这个字符是R，
                r++;
            }else if('L'==str.charAt(i)){
                //如果这个字符是L
                l++;
            }
            if(r==l){
                //如果r和l数量一样多，即满足子串条件
                r=0;l=0;//初始化
                n++;//记录子串数量
            }
        }
        System.out.println(n);
    }

}
