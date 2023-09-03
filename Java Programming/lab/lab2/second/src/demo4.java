package second.src;

import java.util.Scanner;

public class demo4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //读入字符串
        String str = scanner.next();
        //分别记录字母数量，数字数量
        int ints = 0;
        int chars = 0;
        //将字符串转换成字符数组
        char[] charArray = str.toCharArray();
        //分别存储字母，数字
        char[] digits = new char[charArray.length];
        char[] letters = new char[charArray.length];
        //统计字母数字，并存储
        for (int i=0;i<str.length();i++){//遍历所有字符
            if(Character.isLetter(charArray[i])){//如果是字母
                letters[chars++]=charArray[i];
            }
            if(Character.isDigit(charArray[i])){//如果是数字
                digits[ints++]=charArray[i];
            }
        }
        if(ints!=chars) {//如果不满足条件，输出空串
            System.out.println("");
            return;
        }
        StringBuilder sb = new StringBuilder("");
        for(int i=0;i<chars;i++){//以目标形式拼接StringBuffer
            sb.append(letters[i]);
            sb.append(digits[i]);
        }
        //输出
        System.out.println(sb);
    }
}
