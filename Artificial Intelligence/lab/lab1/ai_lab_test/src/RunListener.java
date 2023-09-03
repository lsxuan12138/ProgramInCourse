import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class RunListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Global.window.setTipText("开始运行");
        //System.out.println("233333333");
        if(!(Global.boxNum==1&&Global.banNum==1&&Global.monNum==1))return;
        Global.action=Action.normal;
        Runnable run=null;
        switch (Global.algorithm){
            case DFS:

            case BFS:

            case A:run = new A();
        }
        List<Point> road1 = run.run(Global.monRow,Global.monCol,Global.boxRow,Global.boxCol);
        System.out.println(road1);
        Thread t1 = new Thread(new java.lang.Runnable() {
            @Override
            public void run() {
                Global.window.setTipText("猴子向箱子寻找");
                Runnable.drawRoad(road1);
            }
        });
        t1.start();

        List<Point> road2 = run.run(Global.boxRow,Global.boxCol,Global.banRow,Global.banCol);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException ex) {
//            throw new RuntimeException(ex);
//        }
        System.out.println(road2);
        Thread t2 = new Thread(new java.lang.Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                Global.window.setTipText("猴子向香蕉寻找");
                Runnable.drawRoad(road2);
                Global.window.setTipText("猴子已找到香蕉");
            }
        });
        t2.start();

    }
}
class Point{
    public int row;
    public int col;
    public Point parent;
    public int F;
    public int G;
    public int H;

    @Override
    public String toString() {
        return "("+row+","+col+")->("+( parent==null?"null":parent.row)+","+(parent==null?"null": parent.col)+")"+F+","+G+","+H;
    }

    public Point(int row, int col, int G, int H, Point parent) {
        this.row = row;
        this.col = col;
        this.G=G;
        this.H = H;
        this.F = G+H;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return row == point.row && col == point.col;
    }

}


abstract class Runnable {
    abstract public List<Point> run(int startRow,int startCol,int endRow,int endCol);
    public static void drawRoad(List<Point> road){
        for (int i = 1; i < road.size()-1; i++) {
            State state = (State) Global.window.getTable().getValueAt(road.get(i).row,road.get(i).col);
            if(state==State.normal)
                Global.window.getTable().setValueAt(State.search,road.get(i).row,road.get(i).col);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        Point p = road.get(road.size()-1);
        while (p.parent!=null){
            p=p.parent;
            State state= (State) Global.window.getTable().getValueAt(p.row,p.col);
            if(state==State.normal||state==State.search)Global.window.getTable().setValueAt(State.road,p.row,p.col);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        JTable table = Global.window.getTable();
        for (int i = 0; i < Global.ROW; i++) {
            for (int j = 0; j < Global.COL; j++) {
                if(table.getValueAt(i,j)==State.search){
                    table.setValueAt(State.normal,i,j);
                }
            }
        }
    }
}
class A extends Runnable{

    @Override
    public List<Point> run(int startRow,int startCol,int endRow,int endCol) {
        //int startRow,startCol,endRow,endCol;
        Point end=null;
        List<Point> close = new ArrayList<Point>();
        List<Point> open = new ArrayList<Point>();
        Point start = new Point(startRow,startCol,0, 10*(Math.abs(startCol-endCol)+Math.abs(startRow-endRow)),null);
        open.add(start);
        while (!open.isEmpty()){
            Point min = open.get(0);
            close.add(min);
            System.out.println(min);
            //Global.window.getTable().setValueAt(State.road,min.row,min.col);
            open.remove(0);
            Set<Point> adjacentPoints= getAdjacentPoints(min,endRow,endCol);
            for (Point temp:
                    adjacentPoints) {
                if(close.contains(temp))continue;

                boolean flag =false;
                Point temp2=null;
                for (Point temp1:
                     open) {
                    if(temp1.equals(temp)){
                        flag=true;
                        temp2=temp1;
                    }
                }
                if (flag&&temp2!=null) {
                    if(temp.G< temp2.G){
                        open.remove(temp2);
                        open.add(temp);

                    }
                }else {
                    open.add(temp);
                }
            }

            open.sort(new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return Integer.compare(o1.F, o2.F);
                }
            });
            end=null;
            for (Point temp:
                 open) {
                if(temp.row==endRow&&temp.col==endCol){
                    end=temp;
                }
            }
            if(end!=null){
                close.add(end);
                break;
            }
        }
        return close;


    }
    public boolean isWalkable(int row,int col){
        if(row < 0 || row > Global.ROW - 1 || col < 0 || col > Global.COL - 1)return false;
        if(Global.window.getTable().getValueAt(row,col)==State.wall)return false;
        return true;
    }
    public Set<Point> getAdjacentPoints(Point point ,int endRow,int endCol){
        Set<Point> result = new HashSet<>();
        if(isWalkable(point.row+1,point.col+1))result.add(new Point(point.row+1,point.col+1, point.G+14, 10*(Math.abs(point.row+1-endRow)+Math.abs(point.col+1-endCol)),point));
        if(isWalkable(point.row+1,point.col))result.add(new Point(point.row+1,point.col, point.G+10, 10*(Math.abs(point.row+1-endRow)+Math.abs(point.col-endCol)),point));
        if(isWalkable(point.row+1,point.col-1))result.add(new Point(point.row+1,point.col-1, point.G+14, 10*(Math.abs(point.row+1-endRow)+Math.abs(point.col-1-endCol)),point));
        if(isWalkable(point.row,point.col+1))result.add(new Point(point.row,point.col+1, point.G+10, 10*(Math.abs(point.row-endRow)+Math.abs(point.col+1-endCol)),point));
        if(isWalkable(point.row,point.col-1))result.add(new Point(point.row,point.col-1, point.G+10, 10*(Math.abs(point.row-endRow)+Math.abs(point.col-1-endCol)),point));
        if(isWalkable(point.row-1,point.col+1))result.add(new Point(point.row-1,point.col+1, point.G+14, 10*(Math.abs(point.row-1-endRow)+Math.abs(point.col+1-endCol)),point));
        if(isWalkable(point.row-1,point.col))result.add(new Point(point.row-1,point.col, point.G+10, 10*(Math.abs(point.row+1-endRow)+Math.abs(point.col-endCol)),point));
        if(isWalkable(point.row-1,point.col-1))result.add(new Point(point.row-1,point.col-1, point.G+14, 10*(Math.abs(point.row-1-endRow)+Math.abs(point.col-1-endCol)),point));
        return result;
    }

}
