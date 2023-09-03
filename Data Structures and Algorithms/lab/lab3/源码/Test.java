package practice3;

import java.io.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
       // Scanner scanner = new Scanner(System.in);

        //Graph graph = new Graph(scanner.nextInt(),scanner.nextInt());
        Graph graph = new Graph(true);
        System.setOut(new PrintStream(new FileOutputStream("src\\practice3\\out.txt")));
        System.out.println("邻接矩阵");
        Matrix.print(graph.getMatrix());

        System.out.println("邻接表");
        AdjList.print(graph.getAdjList());
        System.out.println("邻接表转矩阵");
        Matrix.print(Graph.AtoM(graph.getAdjList()));
        System.out.println("邻接矩阵转表");
        AdjList.print(Graph.MtoA(graph.getMatrix()));

        Graph.DFSTraverse(graph);
        Graph.BFSTraverse(graph);


    }
}
