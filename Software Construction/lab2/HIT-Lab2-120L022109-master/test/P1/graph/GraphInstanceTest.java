/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    /*
        testAdd方法
        -测试添加一个普通的节点的情况
        -测试添加一个重复节点的情况
        testSet方法
        -测试添加正常的权值非零的边
        -测试添加节点不存在的边
        -测试改变权值
        -测试删去存在的边
        -测试删去不存在的边
        testRemove方法
        -删去一个存在的节点
        -删去一个不存在的节点
        testVertices方法
        -测试一个构建好的图
        -删除节点后再次测试
        -增加节点后再次测试
        testSources方法
        -测试一个正常情况
        -测试删去边的情况
        -测试增加边的情况
        testTarget方法
        -测试一个正常情况
        -测试删去边的情况
        -测试增加边的情况
    */
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    //  other tests for instance methods of Graph
    @Test
    public void testAdd(){
        Graph<String> graph = emptyInstance();
        //test normal case
        assertTrue(graph.add("hhhh"));
        //test case that the vertex label is repeated
        assertFalse(graph.add("hhhh"));
    }
    @Test
    public void testSet(){
        Graph<String> graph = emptyInstance();
        graph.add("LiLei");
        graph.add("HanMeiMei");
        graph.add("XiaoMing");
        //add edge nonzero
        assertEquals(0,graph.set("LiLei","HanMeiMei",5));
        assertEquals(0,graph.set("LiLei","XiaoMing",4));
        //添加一个节点不存在的边
        assertEquals(0,graph.set("XiaoHong","XiaoMing",6));
        assertEquals(0,graph.set("XiaoHong1","XiaoMing1",6));
        //change edge's weight
        assertEquals(5,graph.set("LiLei","HanMeiMei",7));
        assertEquals(4,graph.set("LiLei","XiaoMing",2));
        //remove edge
        assertEquals(7,graph.set("LiLei","HanMeiMei",0));
        assertEquals(2,graph.set("LiLei","XiaoMing",0));
        //删去一个节点不存在的边
        assertEquals(0,graph.set("LiLei1","XiaoMing2",0));
    }
    @Test
    public void testRemove(){
        Graph<String> graph = emptyInstance();
        graph.add("LiLei");
        graph.add("HanMeiMei");
        graph.add("XiaoMing");

        graph.set("LiLei","HanMeiMei",5);
        graph.set("LiLei","XiaoMing",4);
        graph.set("XiaoHong","XiaoMing",6);

        //remove a vertex included
        assertTrue(graph.remove("LiLei"));
        assertTrue(graph.remove("HanMeiMei"));
        //remove a vertex not included
        assertFalse(graph.remove("LiLei"));
        assertFalse(graph.remove("XiaoWang"));
    }
    @Test
    public void testVertices(){
        Graph<String> graph = emptyInstance();
        graph.add("LiLei");
        graph.add("HanMeiMei");
        graph.add("XiaoMing");
        graph.add("XiaoHong");
        Set<String> vertices =graph.vertices();
        Set<String> set = new HashSet<>();
        set.add("LiLei");
        set.add("HanMeiMei");
        set.add("XiaoMing");
        set.add("XiaoHong");
        //normal test case
        assertEquals(vertices,set);
        //remove a vertex and test again
        graph.remove("LiLei");
        set.remove("LiLei");
        vertices =graph.vertices();
        assertEquals(vertices,set);
        //add a vertex and test again
        graph.add("LiLei1");
        set.add("LiLei1");
        vertices =graph.vertices();
        assertEquals(vertices,set);
    }
    @Test
    public void testSources(){
        Graph<String> graph = emptyInstance();
        graph.add("LiLei");
        graph.add("HanMeiMei");
        graph.add("XiaoMing");

        graph.set("LiLei","HanMeiMei",5);
        graph.set("LiLei","XiaoMing",4);
        graph.set("XiaoHong","XiaoMing",6);
        Map<String,Integer> sources= graph.sources("XiaoMing");
        Map<String,Integer> map = new HashMap<>();
        //Test a normal case
        map.put("LiLei",4);
        map.put("XiaoHong",6);
        assertEquals(map,sources);
        //test remove an edge
        graph.set("LiLei","XiaoMing",0);
        map.remove("LiLei");
        sources=graph.sources("XiaoMing");
        assertEquals(map,sources);
        //test add an edge
        graph.set("HanMeiMei","XiaoMing",7);
        map.put("HanMeiMei",7);
        sources=graph.sources("XiaoMing");
        assertEquals(map,sources);
    }
    @Test
    public void testTargets(){
        Graph<String> graph = emptyInstance();
        graph.add("LiLei");
        graph.add("HanMeiMei");
        graph.add("XiaoMing");

        graph.set("LiLei","HanMeiMei",5);
        graph.set("LiLei","XiaoMing",4);
        graph.set("XiaoHong","XiaoMing",6);
        Map<String,Integer> sources= graph.targets("LiLei");
        Map<String,Integer> map = new HashMap<>();
        map.put("HanMeiMei",5);
        map.put("XiaoMing",4);
        assertEquals(map,sources);
        //测试删去一条边
        graph.set("LiLei","XiaoMing",0);
        map.remove("XiaoMing");
        sources= graph.targets("LiLei");
        assertEquals(map,sources);
        //test add an edge
        graph.set("LiLei","XiaoMing",7);
        map.put("XiaoMing",7);
        sources= graph.targets("LiLei");
        assertEquals(map,sources);
    }
}
