package P3;

import java.util.*;

/**
 * @author Xuan
 */
public class FriendshipGraph {
    //储存顶点表
    private Map<String,Vertex> map;

    public FriendshipGraph() {
        this.map=new HashMap<>();
    }

    /**
     * 添加一个顶点
     * @param person
     * @return
     */
    public boolean addVertex(Person person){
        if(map.containsKey(person.getName())){
            System.out.println(person.getName()+"已存在");
            return false;
        }
        map.put(person.getName(), new Vertex(person));
        return true;
    }

    /**
     * 添加由p1指向p2的边
     * @param p1
     * @param p2
     * @return 是否添加成功
     */
    public boolean addEdge(Person p1,Person p2){
        if(p1==null||p2==null||p1.getName()==null||p2.getName()==null){
            throw new IllegalArgumentException();
        }
        if(p1.equals(p2)){
            System.out.println("你不能添加自己为好友");
            return false;
        }
        Vertex vertex = map.get(p1.getName());
        Edge edge = vertex.getFirstEdge();
        if(edge==null){
            vertex.setFirstEdge(new Edge(p2));
            return true;
        }
        if(edge.getData().getName().equals(p2.getName())){
            System.out.println("你们已经是好友了，无需再次添加");
            return false;
        }
        while (true){
            if(edge.getNext()!=null){
                if(edge.getData().getName().equals(p2.getName())){
                    System.out.println("你们已经是好友了，无需再次添加");
                    return false;
                }
                edge=edge.getNext();
            }else {
                break;
            }
        }
        edge.setNext(new Edge(p2));
        return true;
    }

    /**
     * 输出从p1指向p2的最短路径的长度
     * @param p1
     * @param p2
     * @return
     */
    public int getDistance(Person p1,Person p2){
        if(p1.equals(p2)){
            return 0;
        }
        if(p1==null||p2==null||p1.getName()==null||p2.getName()==null){
            throw new RuntimeException();
        }
        int cnt=0;
        Queue<Person> queue = new LinkedList<Person>();
        //标记节点是否访问
        Set<Person> hasAsk= new HashSet<Person>();
        queue.offer(p1);
        //广度优先遍历
        //设置标记，表示读完一层
        //返回值应该为遍历的深度
        boolean flag=true;
        while (!queue.isEmpty()){
            Person current = queue.poll();
            //若读完一层，添加空节点，作为记录
            if(flag){
                queue.offer(new Person());
                flag=false;
            }
            //若读到空节点，表明读完一层
            if(current.getName()==null){
                flag=true;
                cnt++;
                continue;
            }

            if(current.getName().equals(p2.getName())){
                return cnt;
            }
            hasAsk.add(current);
            Vertex vertex = map.get(current.getName());
            Edge edge =vertex.getFirstEdge();
            if(edge==null){continue;}
            while (edge!=null){
                Person data = edge.getData();
                if(!hasAsk.contains(data)){
                    queue.offer(data);

                }
                edge=edge.getNext();

            }
        }
        return -1;

    }
    public static void main(String[] args) {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);

        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);

        System.out.println(graph.getDistance(rachel, ross));
        //should print 1
        System.out.println(graph.getDistance(rachel, ben));
        //should print 2
        System.out.println(graph.getDistance(rachel, rachel));
        //should print 0
        System.out.println(graph.getDistance(rachel, kramer));
        //should print -1
    }
}
class Vertex{
    private Person person;
    private Edge firstEdge;

    public Vertex() {
    }

    public Vertex(Person person) {
        this.person = person;
    }

    public Vertex(Person person, Edge firstEdge) {
        this.person = person;
        this.firstEdge = firstEdge;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Edge getFirstEdge() {
        return firstEdge;
    }

    public void setFirstEdge(Edge firstEdge) {
        this.firstEdge = firstEdge;
    }
}
class Edge{
    private Person data;
    private Edge next;

    public Edge() {
    }

    public Edge(Person data) {
        this.data = data;
        this.next=null;
    }

    public Person getData() {
        return data;
    }

    public void setData(Person data) {
        this.data = data;
    }

    public Edge getNext() {
        return next;
    }

    public void setNext(Edge next) {
        this.next = next;
    }
}