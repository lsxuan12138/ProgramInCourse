package P1;

import java.io.*;

/**
 * @author Xuan
 */
public class MagicSquare {
    public static boolean isLegalMagicSquare(String fileName)  {
        String[] array=null;
        int cnt=0;
        int[][] magic=null;
        //文件输入流
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = bufferedReader.readLine();
            //若分隔符不是'\t'
            if (!line.contains("\t")) {
                System.out.println("请用‘\\t’分割字符串");
                //throw new IllegalArgumentException();
                return false;
            }
            array = line.split("\t");
            //得到循环次数，即读入第一行的分割后的数
            cnt = array.length;
            magic= new int[cnt][cnt];
            for (int i = 0; i < cnt; i++) {
                //第一行已读，当i>0时再读入
                if(i>0){
                    line = bufferedReader.readLine();
                    //若文件中还有元素行数比列数少
                    if (line == null) {
                        System.out.println("请保持输入行列数相等");
                        return false;
                    }
                    array = line.split("\t");
                    //若某行长度与第一行不同
                    if (cnt != array.length) {
                        System.out.println("请保持每行元素个数相等，出现问题的为第"+(i+1)+"行，应以\\t分割元素");
                        return false;
                    }
                }
                for (int j = 0; j < cnt; j++) {
                    try {//若这里的字符串是小数形式或其他则报错
                        int temp = Integer.parseInt(array[j]);
                        //若这里的字符串是0或负数
                        if (temp <= 0) {
                            System.out.println("请输入正整数，零和负数都是不合法的,出现问题的为第 "+(i+1)+" 行,第 "+(j+1)+" 列");
                            return false;
                        }
                        if(temp>cnt*cnt){
                            return false;
                        }
                        magic[i][j] = temp;
                    } catch (NumberFormatException e) {
                        //e.printStackTrace();
                        System.out.println("输入格式错误：出现问题的为第 "+(i+1)+" 行,第 "+(j+1)+" 列"+e.getMessage());
                        return false;
                    }
                }

            }
            //若在读完cnt行后，文件中还有元素
            if (bufferedReader.readLine() != null) {
                System.out.println("请保持输入行列数相等");
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //现在取到整数二维数组，接下来判断
        //判断二维数组中元素是否重复
        boolean[] isRepeat = new boolean[cnt*cnt+1];
        for (int i =0 ;i<cnt;i++){
            for (int j=0;j<cnt;j++){
                if(isRepeat[magic[i][j]]) {
                    return false;
                }
                else {
                    isRepeat[magic[i][j]]=true;
                }
            }
        }
        int sum1 = 0, sum2 = 0;
        //算出第一行的和，之后的每行，每列，写对角线的和值都应该与它相等
        for (int i = 0; i < cnt; i++) {
            sum1 += magic[0][i];
        }
        //每行
        for (int i = 1; i < cnt; i++) {
            for (int j = 0; j < cnt; j++) {
                sum2 += magic[i][j];
            }
            if (sum2 != sum1) {
                return false;
            }
            sum2 = 0;
        }
        //每列
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < cnt; j++) {
                sum2 += magic[j][i];
            }
            if (sum2 != sum1) {
                return false;
            }
            sum2 = 0;
        }
        int sum3 = 0;
        //对角线
        for (int i = 0; i < cnt; i++) {
            sum2 += magic[i][i];
            sum3 += magic[i][cnt - 1 - i];
        }
        if (sum2 != sum1||sum3!=sum1) {
            return false;
        }

        return true;
    }

    public static boolean generateMagicSquare(int n) {
        int[][] magic = new int[n][n];
        int row = 0, col = n / 2, i, j, square = n * n;

        try{
            //共向矩阵中填写n*n个数
            for (i = 1; i <= square; i++) {
                magic[row][col] = i;
                if (i % n == 0) {
                    //每放置n个数，row+1，防止覆盖前面已放置的数
                    row++;
                } else {

                    //让row从n-1到0从大到小不断循环，且初值为0
                    if (row == 0) {
                        row = n - 1;
                    } else {
                        row--;
                    }
                    //让col从0到n-1从小到大不断循环，且初值为n/2
                    if (col == (n - 1)) {
                        col = 0;
                    } else {
                        col++;
                    }
                }
            }

        }catch (NegativeArraySizeException e){
            System.out.println("数组尺寸不应为负数，请确认输入n为正整数");
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("请确认输入n为奇数");
            return false;
        }
        BufferedWriter fileWriter=null;
        try {
             fileWriter= new BufferedWriter(new FileWriter("src/P1/txt/6.txt"));
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    fileWriter.write(magic[i][j] + "\t");
                }
                fileWriter.write('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(isLegalMagicSquare("src/P1/txt/1.txt"));
        System.out.println(isLegalMagicSquare("src/P1/txt/2.txt"));
        System.out.println(isLegalMagicSquare("src/P1/txt/3.txt"));
        System.out.println(isLegalMagicSquare("src/P1/txt/4.txt"));
        System.out.println(isLegalMagicSquare("src/P1/txt/5.txt"));
        generateMagicSquare(7);
        System.out.println(isLegalMagicSquare("src/P1/txt/6.txt"));
    }
}
