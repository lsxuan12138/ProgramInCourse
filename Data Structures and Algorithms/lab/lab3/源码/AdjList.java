package practice3;

public class AdjList {
    private AdjListNodeSpot[] VList;

    public AdjList() {
    }

    public AdjList(int n) {
        this.VList = new AdjListNodeSpot[n];
    }

    public AdjListNodeSpot[] getVList() {
        return VList;
    }

    public void setVList(AdjListNodeSpot[] VList) {
        this.VList = VList;
    }

    public static void addSide(AdjListNodeSpot spot,AdjLIstNodeSide n ){
        AdjLIstNodeSide side = spot.getFirstEdge();
        if(side==null)spot.setFirstEdge(n);
        else {
            while (side!=null){
                if(side.getNext()==null){
                    side.setNext(n);
                    break;
                }
                side=side.getNext();
            }
        }
    }
    /**
     * 打印邻接表
     * @param adjList
     */
    public static void print(AdjList adjList){
        int n = adjList.VList.length;
        for(int i=0;i<n;i++){
            System.out.print("("+i+")"+adjList.VList[i].getVertex());
            AdjLIstNodeSide side = adjList.VList[i].getFirstEdge();
            while (side!=null){
                int v = side.getAdjvex();
                //System.out.print("->"+this.VList[v].getVertex());
                System.out.print("->"+v);
                side=side.getNext();
            }
            System.out.print("\n");
        }
    }
}
