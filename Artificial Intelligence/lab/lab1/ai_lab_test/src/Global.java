
public class Global {

    public static final int ROW = 30;
    public static final int COL = 30;
    public static final int ROW_HEIGHT = 20;
    public static final int COL_WIDTH = 20;
    public static final String SET_MON = "设置猴子";
    public static final String SET_BAN = "设置香蕉";
    public static final String SET_BOX = "设置箱子";
    public static final String SET_WALL = "设置障碍";
    public static final String RECOVERY = "复位";
    public static final String RUN = "Run";
    public static final String DFS = "深度优先";
    public static final String BFS = "广度优先";
    public static final String A = "A*";
    public static Action action=Action.normal;
    public static Algorithm algorithm = Algorithm.A;
    public static Window window;
    public static int monNum =0;
    public static int banNum =0;
    public static int boxNum = 0;
    public static int monRow = 0;
    public static int monCol =0;
    public static int banRow = 0;
    public static int banCol =0;
    public static int boxRow = 0;
    public static int boxCol =0;


}
enum Algorithm{
    DFS,//深度优先
    BFS,//广度优先
    A
}
enum Action{
    normal,
    setMon,
    setBan,
    setBox,
    setWall,
    Recovery,

}
enum State{
    normal,
    mon,
    ban,
    box,
    wall,
    road,
    search
}