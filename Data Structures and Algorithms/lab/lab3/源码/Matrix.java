package practice3;

public class Matrix {
    private char[] VList;
    private boolean[][] ETable;

    public Matrix() {
    }

    public Matrix(int n) {
        this.VList = new char[n];
        this.ETable = new boolean[n][n];
    }

    public char[] getVList() {
        return VList;
    }

    public void setVList(char[] VList) {
        this.VList = VList;
    }

    public boolean[][] getETable() {
        return ETable;
    }

    public void setETable(boolean[][] ETable) {
        this.ETable = ETable;
    }


    /**
     * 打印邻接矩阵
     * @param matrix
     */

    public static void print(Matrix matrix){
        int n= matrix.getVList().length;
        System.out.print(" ");
        for (int i=0;i<n;i++){
            System.out.print(" "+matrix.getVList()[i]);
        }
        System.out.print("\n");
        for (int i=0;i<n;i++){
            System.out.print(matrix.getVList()[i]+" ");
            for(int j=0;j<n;j++){
                System.out.print(matrix.ETable[i][j]?"1 ":"0 ");
            }
            System.out.print("\n");
        }

    }
}
