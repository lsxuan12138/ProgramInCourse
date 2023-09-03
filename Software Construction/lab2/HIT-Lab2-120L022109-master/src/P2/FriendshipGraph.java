package P2;

import P1.graph.Graph;

import java.util.*;

/**
 * @author Xuan
 */
public class FriendshipGraph {
    private final Graph<Person> friendshipGraph = Graph.empty();

    /**
     * 添加一个节点
     * @param person 任意节点
     * @return 是否添加成功
     */
    public boolean addVertex(Person person){
        if(friendshipGraph.vertices().contains(person)){
            System.out.println(person.getName()+"已存在");
            return false;
        }
        friendshipGraph.add(person);
        return true;
    }

    /**
     * 添加由source指向target的边
     * @param source 边的起点，可以是任意点
     * @param target 边的终点，可以是任意点
     * @return 是否添加成功
     */
    public boolean addEdge(Person source,Person target){
        if(source==null||target==null||source.getName()==null||target.getName()==null){
            throw new IllegalArgumentException();
        }
        if(source.equals(target)){
            System.out.println("你不能添加自己为好友");
            return false;
        }

        if(friendshipGraph.set(source,target,1)!=0){
            System.out.println("你们已经是好友了，无需再次添加");
            return false;
        }
        return true;
    }

    /**
     * 输出从source指向target的最短路径的长度
     * @param source 边的起点，可以是任意点
     * @param target 边的终点，可以是任意点
     * @return 返回最短路径的长度，若图中两个点未连接返回-1
     */
    public int getDistance(Person source,Person target){
        //判断异常情况
        if(source==null||target==null||source.getName()==null||target.getName()==null){
            throw new IllegalArgumentException();
        }
        if(source.equals(target)){
            return 0;
        }
        int cnt=0;
        Queue<Person> queue = new LinkedList<>();
        //标记节点是否访问
        Set<Person> hasAsk= new HashSet<>();
        queue.offer(source);
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
            //找到target节点，返回
            if(current.getName().equals(target.getName())){
                return cnt;
            }
            //广度优先遍历的正常流程
            hasAsk.add(current);
            Map<Person,Integer> targets = friendshipGraph.targets(current);
            for (Person p:
                    targets.keySet()) {
                if(!hasAsk.contains(p)){
                    queue.offer(p);
                }
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

