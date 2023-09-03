package demo1.src;

import java.util.Scanner;

public class demo2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array1=new int[101];
        int[] array2 = new int[101];
        int mi =0;
        int xi = 0;
        do{
            mi = scanner.nextInt();
            xi = scanner.nextInt();
            array1[mi]=xi;
        }while (mi !=0);
        do{
            mi = scanner.nextInt();
            xi = scanner.nextInt();
            array2[mi]=xi;
        }while (mi !=0);
        for (int i=0;i<101;i++){
            array1[i] += array2[i];
        }
        int k = 100;
        for (;k>0;k--){
            if(array1[k]!=0) {
                System.out.print(array1[k] +"x"+ k);
                k--;
                break;
            }

        }
        for (;k>0;k--){
            if(array1[k]!=0) {
                System.out.print( "+" + array1[k] +"x"+ k);

            }
        }
        if(array1[0]!=0) System.out.println("+"+array1[0]);

    }
}
