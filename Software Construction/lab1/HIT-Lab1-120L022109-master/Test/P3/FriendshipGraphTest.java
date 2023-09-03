package P3;

import org.junit.After;
import org.junit.Before;

import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class FriendshipGraphTest extends FriendshipGraph {
    private PrintStream console = null;
    private ByteArrayOutputStream bytes = null;

    @Before
    public void setUp() {
        bytes = new ByteArrayOutputStream();// 把标准输出指定到ByteArrayOutputStream中
        console = System.out;// 获取System.out 输出流的句柄
        System.setOut(new PrintStream(bytes));// 将原本输出到控制台Console的字符流重定向到bytes
    }

    @After
    public void tearDown() {
        System.setOut(console);
    }


    /**
     * 测试方法
     * 测试当节点重复的情况下，程序的反应
     */
    @Test
    public void testAddVertex() {
        FriendshipGraph graph = new FriendshipGraph();
        //若name重复，那么程序将退出运行，这里不测试
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        assertEquals(true,graph.addVertex(rachel));
        assertEquals(true,graph.addVertex(ross));
        assertEquals(true,graph.addVertex(ben));
        assertEquals(true,graph.addVertex(kramer));

        //当节点重复的情况下，应返回false，并输出提示信息
        assertEquals(false,graph.addVertex(kramer));
        assertEquals(kramer.getName()+"已存在", bytes.toString().split("\r\n")[0]);

    }



    @Test(expected = IllegalArgumentException.class)
    public void testAddEdge() {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Racheld");
        Person ross = new Person("Rossd");
        Person ben = new Person("Bend");
        Person kramer = new Person("Kramerd");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);

        assertEquals(true,graph.addEdge(rachel, ross));
        assertEquals(true,graph.addEdge(ross, rachel));
        assertEquals(true,graph.addEdge(ross, ben));
        assertEquals(true,graph.addEdge(ben, ross));

        assertEquals(false,graph.addEdge(rachel,rachel));
        assertEquals("你不能添加自己为好友",bytes.toString().split("\r\n")[0]);

        assertEquals(false,graph.addEdge(rachel,ross));
        assertEquals("你们已经是好友了，无需再次添加",bytes.toString().split("\r\n")[1]);


        graph.addEdge(null,new Person());
    }

    @Test
    public void testGetDistance() {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachels");
        Person ross = new Person("Rosss");
        Person ben = new Person("Bens");
        Person kramer = new Person("Kramers");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);

        //rachel和ross只有单向的关系
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);

        assertEquals(-1,graph.getDistance(rachel, ross));
        //should print -1
        assertEquals(-1,graph.getDistance(rachel, ben));
        //should print -1
        assertEquals(0,graph.getDistance(rachel, rachel));
        //should print 0
        assertEquals(-1,graph.getDistance(rachel, kramer));
        //should print -1

        graph.addEdge(rachel, ross);
        assertEquals(1,graph.getDistance(rachel, ross));
        //should print 1
        assertEquals(2,graph.getDistance(rachel, ben));
        //should print 2
        assertEquals(0,graph.getDistance(rachel, rachel));
        //should print 0
        assertEquals(-1,graph.getDistance(rachel, kramer));
        //should print -1
    }
}