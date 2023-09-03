package practice3;

public class AdjLIstNodeSide {
    private int adjvex;
    private AdjLIstNodeSide next;

    public AdjLIstNodeSide() {
    }

    public AdjLIstNodeSide(int adjvex) {
        this.adjvex = adjvex;
    }

    public int getAdjvex() {
        return adjvex;
    }

    public void setAdjvex(int adjvex) {
        this.adjvex = adjvex;
    }

    public AdjLIstNodeSide getNext() {
        return next;
    }

    public void setNext(AdjLIstNodeSide next) {
        this.next = next;
    }
}
