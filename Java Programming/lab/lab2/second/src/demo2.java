package second.src;

import java.util.Scanner;

public class demo2 {
    public static void main(String[] args) {
        //创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        //读入一行，以“ ”分割，并放入words
        String[] words = scanner.nextLine().split(" ");

        //循环判断 是否为子串
        for(int i = 0;i<words.length;i++){
            for(int j = 0;j< words.length;j++){
                //避免与本身判断
                if(i==j)continue;
                //利用contains方法判断
                if(words[j].contains(words[i])) {
                    System.out.println(words[i]);
                    break;
                }
            }

        }
    }
}
