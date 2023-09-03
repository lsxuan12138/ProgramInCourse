package demo1.src;

import java.util.Scanner;

public class demo1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = 0;
        int[] array = new int[101];
        while ((i = in.nextInt())!=0){
            array[i]++;
        }
        for(int k=1;k<array.length;k++){
            if(array[k]!=0){
                System.out.println(k+"出现了"+array[k]+"次");
            }
        }
    }
}
