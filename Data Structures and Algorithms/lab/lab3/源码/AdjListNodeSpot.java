package practice3;

public class AdjListNodeSpot {
    private char vertex;
    private AdjLIstNodeSide firstEdge;

    public AdjListNodeSpot() {
    }

    public AdjListNodeSpot(char vertex) {
        this.vertex = vertex;
    }

    public char getVertex() {
        return vertex;
    }

    public void setVertex(char vertex) {
        this.vertex = vertex;
    }

    public AdjLIstNodeSide getFirstEdge() {
        return firstEdge;
    }

    public void setFirstEdge(AdjLIstNodeSide firstEdge) {
        this.firstEdge = firstEdge;
    }
}
