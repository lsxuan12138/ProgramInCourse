package practice3;

import practice2.Queue;
import practice2.QueueNode;
import practice2.Stack;
import practice2.StackNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//无向图
public class Graph {
    private int n;
    private Matrix matrix;
    private AdjList adjList;
    private boolean[] visited;
    public Graph() {
    }
    public Graph(boolean file) throws IOException {
        File in =new File("src\\practice3\\in.txt");
        //System.out.println("文件读入");
        FileReader fr = null;
        BufferedReader brd = null;
        try{
            fr=new FileReader(in);
            brd = new BufferedReader(fr);

            String str =null;
            str= brd.readLine();
            int v = Integer.valueOf(str);
            str= brd.readLine();
            int e = Integer.valueOf(str);
            this.n=v;
            this.visited = new boolean[v];
            this.matrix=new Matrix(v);
            this.adjList=new AdjList(v);

            for(int i =0;i<v;i++){
                //System.out.println("输入点"+(i)+"的名称（只有输入的第一个字符有效）");
                str= brd.readLine();
                char c =str.charAt(0);
                this.matrix.getVList()[i]=c;
                this.adjList.getVList()[i]=new AdjListNodeSpot(c);
            }
            for(int i =0;i<e;i++){
                //System.out.println("请输入边");
                str= brd.readLine();

//                if(str.length()!=5){
//                    System.out.println("输入错误，重新输入");
//                    i--;
//                    continue;
//                }
                char a =str.charAt(1);
                char b =str.charAt(3);
                boolean[][] ET = this.matrix.getETable();
                int n=-1,m=-1;
                for(int j =0;j<v;j++){
                    if(this.matrix.getVList()[j]==a)n=j;
                    if(this.matrix.getVList()[j]==b)m=j;
                }
                if(n==-1||m==-1){
                    //System.out.println("输入错误，再输一次");
                    i--;
                    continue;
                }
                ET[n][m]=true;
                ET[m][n]=true;

                AdjList.addSide(this.adjList.getVList()[n],new AdjLIstNodeSide(m));
                AdjList.addSide(this.adjList.getVList()[m],new AdjLIstNodeSide(n));
            }

        }catch (Exception e){
            e.getStackTrace();
        }finally {
            brd.close();
            fr.close();
        }

    }
    /**
     * 生成一个无向图
     * 以邻接矩阵存储
     * @param v 图的节点数
     * @param e 图的边数
     */
    public Graph(int v,int e) {
        this.n=v;
        this.visited = new boolean[v];
        this.matrix=new Matrix(v);
        this.adjList=new AdjList(v);
        Scanner scanner = new Scanner(System.in);
        for(int i =0;i<v;i++){
            System.out.println("输入点"+(i)+"的名称（只有输入的第一个字符有效）");
            char c =scanner.next().charAt(0);
            this.matrix.getVList()[i]=c;
            this.adjList.getVList()[i]=new AdjListNodeSpot(c);
        }
        for(int i =0;i<e;i++){
            System.out.println("请输入边");
            String str = scanner.next();
            if(str.length()!=5){
                System.out.println("输入错误，重新输入");
                i--;
                continue;
            }
            char a =str.charAt(1);
            char b =str.charAt(3);
            boolean[][] ET = this.matrix.getETable();
            int n=-1,m=-1;
            for(int j =0;j<v;j++){
                if(this.matrix.getVList()[j]==a)n=j;
                if(this.matrix.getVList()[j]==b)m=j;
            }
            if(n==-1||m==-1){
                System.out.println("输入错误，再输一次");
                i--;
                continue;
            }
            ET[n][m]=true;
            ET[m][n]=true;

            AdjList.addSide(this.adjList.getVList()[n],new AdjLIstNodeSide(m));
            AdjList.addSide(this.adjList.getVList()[m],new AdjLIstNodeSide(n));
        }
    }

    /**
     * 把图的 邻接矩阵 转换为 邻接表
     * @param matrix
     * @return
     */
    public static AdjList MtoA(Matrix matrix){
        int n =matrix.getVList().length;
        AdjList result = new AdjList(n);
        for(int i=0;i<n;i++){
            result.getVList()[i]=new AdjListNodeSpot(matrix.getVList()[i]);
            AdjLIstNodeSide side = null;
            for(int j=0;j<n;j++){
                if(matrix.getETable()[i][j]){
                    if(side==null){
                        result.getVList()[i].setFirstEdge(new AdjLIstNodeSide(j));
                        side=result.getVList()[i].getFirstEdge();
                    }else {
                        side.setNext(new AdjLIstNodeSide(j));
                        side = side.getNext();
                    }
                }
            }
        }
        return result;
    }

    /**
     * 把图的 邻接表 转换为 邻接矩阵
     * @param adjList
     * @return
     */
    public static Matrix AtoM(AdjList adjList){
        int n = adjList.getVList().length;
        Matrix result = new Matrix(n);
        for(int i=0;i<n;i++){
            result.getVList()[i]=adjList.getVList()[i].getVertex();
            AdjLIstNodeSide side = adjList.getVList()[i].getFirstEdge();
            while (side!=null){
                int v = side.getAdjvex();
                result.getETable()[i][v]=true;
                side=side.getNext();
            }
        }
        return result;
    }

    /**
     * 深度优先遍历
     * @param G
     */
    public static void DFSTraverse(Graph G) {
        int[] count = {1};
        int n = G.getN();

        int[] dfn = new int[n];
        //深度优先 递归 邻接表
        System.out.println("深度优先 递归 邻接表");
        for (int i = 0; i < n; i++) {
            if (!G.getVisited()[i]){
                DFS1(G,i,count,dfn);
                System.out.print("\n");
            }
        }
        for (int i=0;i<n;i++) System.out.print(" "+dfn[i]);
        System.out.print("\n");
        //for(int i=0;i<n;i++)visited[i]=false;
        G.setVisited(new boolean[G.getN()]);
        count[0]=1;
        //深度优先 递归 邻接矩阵
        System.out.println("深度优先 递归 邻接矩阵");
        for (int i = 0; i < n; i++) {
            if (!G.getVisited()[i]){
                DFS2(G,i,count,dfn);
                System.out.print("\n");
            }
        }
        for (int i=0;i<n;i++) System.out.print(" "+dfn[i]);
        System.out.print("\n");
        G.setVisited(new boolean[G.getN()]);
        count[0]=1;
        //深度优先 非递归 邻接表
        System.out.println("深度优先 非递归 邻接表");
        for (int i = 0; i < n; i++) {
            if (!G.getVisited()[i]){
                DFS3(G,i,count,dfn);
                System.out.print("\n");
            }
        }
        for (int i=0;i<n;i++) System.out.print(" "+dfn[i]);
        System.out.print("\n");
        count[0]=1;
        G.setVisited(new boolean[G.getN()]);

        //深度优先 非递归 邻接矩阵
        System.out.println("深度优先 非递归 邻接矩阵");
        for (int i = 0; i < n; i++) {
            if (!G.getVisited()[i]){
                DFS4(G,i,count,dfn);
                System.out.print("\n");
            }
        }
        for (int i=0;i<n;i++) System.out.print(" "+dfn[i]);
        System.out.print("\n");
        System.out.println("深度优先生成树");
//        for(int i=0;i<n;i++) System.out.print(" "+dfnt[i]);
//        System.out.print("\n");
        G.setVisited(new boolean[G.getN()]);
        for (int i = 0; i < n; i++) {
            if (!G.getVisited()[i]){
                printDFSTree(G,i);
                System.out.print("\n");
            }
        }
    }

    /**
     * 深度优先 递归 邻接表
     * @param G
     * @param i
     * @param count
     * @param dfn
     */
    public static void DFS1(Graph G,int i,int[] count,int[] dfn){
        System.out.print(" "+G.getAdjList().getVList()[i].getVertex());
        G.getVisited()[i]=true;
        dfn[i]=count[0];
        count[0]++;
        AdjLIstNodeSide p = G.adjList.getVList()[i].getFirstEdge();
        while (p!=null){
            if(!G.getVisited()[p.getAdjvex()]){
                DFS1(G,p.getAdjvex(),count,dfn);
            }
            p=p.getNext();
        }
    }
    /**
     * 深度优先 递归 邻接矩阵
     * @param G
     * @param i
     * @param count
     * @param dfn
     */
    public static void DFS2(Graph G,int i,int[] count,int[] dfn){
        int j;
        System.out.print(" "+G.getMatrix().getVList()[i]);
        G.getVisited()[i]=true;
        dfn[i]=count[0]++;

        for(j=0;j<G.getN();j++){
            if(G.getMatrix().getETable()[i][j] && !G.getVisited()[j])
                DFS2(G,j,count,dfn);
        }
    }
    /**
     * 深度优先 非递归 邻接表
     * @param G
     * @param i
     */
    public static void DFS3(Graph G,int i,int[] count,int[] dfn){
        Stack<Integer> s =new Stack<>();
        AdjLIstNodeSide side =null;

        G.getVisited()[i]=true;
        s.push(new StackNode<Integer>(i));
        System.out.print(" "+G.getAdjList().getVList()[i].getVertex());
        dfn[i]=count[0]++;
        side=G.getAdjList().getVList()[i].getFirstEdge();

        while (!s.isEmpty()){
            side = G.getAdjList().getVList()[s.getStackTop().getEle()].getFirstEdge();
            while (side!=null){
                int n = side.getAdjvex();
                if(!G.getVisited()[n]){
                    G.getVisited()[n]=true;
                    System.out.print(" "+G.getAdjList().getVList()[side.getAdjvex()].getVertex());
                    dfn[n]=count[0]++;

                    s.push(new StackNode<>(n));
                    side = G.getAdjList().getVList()[n].getFirstEdge();

                }else {
                    side=side.getNext();
                }
            }
            if(side==null){s.pop();}
        }
    }
    /**
     * 深度优先 非递归 邻接矩阵
     * @param G
     * @param i
     */
    public static void DFS4(Graph G,int i,int[] count,int[] dfn){
        Stack<Integer> s = new Stack<>();

        dfn[i]=count[0]++;
        System.out.print(" "+G.getMatrix().getVList()[i]);

        G.getVisited()[i]=true;
        s.push(new StackNode<Integer>(i));

        while (!s.isEmpty()){
            int data;
            int v;
            data=s.getStackTop().getEle();
            for(v=0;v<G.getN();v++){
                if(G.getMatrix().getETable()[data][v] && !G.getVisited()[v]){

                    dfn[v]=count[0]++;
                    System.out.print(" "+G.getMatrix().getVList()[v]);
                    G.getVisited()[v]=true;
                    s.push(new StackNode<>(v));
                    break;
                }
            }
            if(v==G.getN()){
                s.pop();
            }
        }
    }

    /**
     * 生成深度优先生成树
     * @param G
     * @param i
     */
    public static void printDFSTree(Graph G,int i){
        Stack<Integer> s = new Stack<>();
        int sn = 0;
        System.out.print(" "+G.getMatrix().getVList()[i]);

        G.getVisited()[i]=true;
        s.push(new StackNode<Integer>(i));
        sn++;

        while (!s.isEmpty()){
            int data;
            int v;
            data=s.getStackTop().getEle();
            for(v=0;v<G.getN();v++){

                if(G.getMatrix().getETable()[data][v] && !G.getVisited()[v]){
                    System.out.print("->"+G.getMatrix().getVList()[v]);
                    G.getVisited()[v]=true;
                    s.push(new StackNode<>(v));
                    sn++;
                    break;
                }
            }

            if(v==G.getN()){
                s.pop();
                sn--;
                if(s.getStackTop()!=null){
                    if(!isAllVisited(G,s.getStackTop().getEle())) {
                        System.out.println();
                        for(int j=0;j<sn;j++){
                            if(j==0) System.out.print("  ");
                            else System.out.print("   ");
                        }

                    }
                }
            }
        }
    }
    public static boolean isAllVisited(Graph graph,int n){
        for(int i = 0;i< graph.getN();i++){
            if(graph.getMatrix().getETable()[n][i]){
                if(!graph.getVisited()[i])return false;
            }
        }
        return true;
    }

    /**
     * 广度优先遍历
     * @param G
     */
    public static void BFSTraverse(Graph G){
        int n = G.getN();
        int[] count ={1};
        int[] bfn = new int[n];
        int[] bfnt = new int[n];
        G.setVisited(new boolean[n]);
        System.out.println("广度优先 非递归 邻接矩阵");
        for(int i=0;i<n;i++){
            if(!G.getVisited()[i]){
                BFS1(G,i,count,bfn,bfnt);
                System.out.print("\n");
            }
        }
        for(int i=0;i<n;i++) System.out.print(" "+bfn[i]);
        System.out.print("\n");

        count[0]=1;
        G.setVisited(new boolean[n]);
        System.out.println("广度优先 非递归 邻接表");
        for(int i=0;i<n;i++){
            if(!G.getVisited()[i]){
                BFS2(G,i,count,bfn);
                System.out.print("\n");
            }
        }
        for(int i=0;i<n;i++) System.out.print(" "+bfn[i]);
        System.out.print("\n");
//        for(int i=0;i<n;i++) System.out.print(" "+bfnt[i]);
//        System.out.print("\n");
        G.setVisited(new boolean[n]);
        System.out.println("广度优先生成树");
        for(int i=0;i<n;i++){
            if(!G.getVisited()[i]){
                printBFSTree(G,i,bfnt);
            }
        }

    }
    /**
     * 广度优先 非递归 邻接矩阵
     * @param G
     * @param i
     * @param count
     * @param bfn
     */
    public static void BFS1(Graph G,int i,int[] count,int[]bfn,int[] bfnt){
        Queue<Integer> q = new Queue<>();
        bfnt[count[0]-1]=i;
        bfn[i]=count[0]++;
        System.out.print(" "+G.getMatrix().getVList()[i]);
        G.getVisited()[i]=true;

        q.enQueue(new QueueNode<>(i));
        while (!q.isEmpty()){
            Integer v = q.deQueue().getElement();
            for(int j=0;j<G.getN();j++){

                if(G.getMatrix().getETable()[v][j] && !G.getVisited()[j]){
                    bfnt[count[0]-1]=j;
                    bfn[j]=count[0]++;

                    System.out.print(" "+G.getMatrix().getVList()[j]);
                    G.getVisited()[j]=true;
                    q.enQueue(new QueueNode<>(j));
                }
            }
        }
    }
    /**
     * 广度优先 非递归 邻接表
     * @param G
     * @param i
     * @param count
     * @param bfn
     */
    public static void BFS2(Graph G,int i,int[] count,int[]bfn){
        Queue<Integer> q = new Queue<>();
        bfn[i]=count[0]++;
        System.out.print(" "+G.getAdjList().getVList()[i].getVertex());
        G.getVisited()[i]=true;

        q.enQueue(new QueueNode<>(i));
        while (!q.isEmpty()){
            Integer v = q.deQueue().getElement();
            AdjLIstNodeSide side = G.getAdjList().getVList()[v].getFirstEdge();
            while (side!=null){
                int j =side.getAdjvex();
                if( !G.getVisited()[j]){
                    bfn[j]=count[0]++;
                    System.out.print(" "+G.getMatrix().getVList()[j]);
                    G.getVisited()[j]=true;
                    q.enQueue(new QueueNode<>(j));
                }
                side=side.getNext();
            }

        }
    }

    /**
     * 打印广度优先生成树
     * @param G
     */
    public static void printBFSTree(Graph G,int i,int[] bfn){
//        Queue<Integer> q = new Queue<>();
//
//
//        System.out.print(" "+G.getMatrix().getVList()[i]);
//        G.getVisited()[i]=true;
//
//        q.enQueue(new QueueNode<>(i));
//        while (!q.isEmpty()){
//            Integer v = q.deQueue().getElement();
//            for(int j=0;j<G.getN();j++){
//
//                if(G.getMatrix().getETable()[v][j] && !G.getVisited()[j]){
//
//                    System.out.print(" "+G.getMatrix().getVList()[j]);
//                    G.getVisited()[j]=true;
//                    q.enQueue(new QueueNode<>(j));
//                }
//            }
//        }
        Queue<Integer> q = new Queue<>();
        int n=i;
        G.getVisited()[n]=true;
        char[] VList = G.getMatrix().getVList();
        q.enQueue(new QueueNode<>(bfn[n]));
        while (!q.isEmpty()){

            int v = q.deQueue().getElement();

            //System.out.print(VList[v]+"的孩子有：");
            StringBuilder sb = new StringBuilder(VList[v]+"的孩子有：");
            for (n=v+1; n < bfn.length; n++) {
                if((!G.getVisited()[bfn[n]]) && (G.getMatrix().getETable()[v][bfn[n]])){
                    sb.append(" "+VList[bfn[n]]);
                    q.enQueue(new QueueNode<>(bfn[n]));
                    G.getVisited()[n]=true;
                }
                //else break;
            }
            if(sb.length()!=6) System.out.println(sb);
        }
    }
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean[] getVisited() {
        return visited;
    }

    public void setVisited(boolean[] visited) {
        this.visited = visited;
    }
    public Matrix getMatrix() {
        return matrix;
    }
    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public AdjList getAdjList() {
        return adjList;
    }

    public void setAdjList(AdjList adjList) {
        this.adjList = adjList;
    }


}
