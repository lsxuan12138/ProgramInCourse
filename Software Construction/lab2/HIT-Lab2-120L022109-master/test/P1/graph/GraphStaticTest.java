/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // test other vertex label types in Problem 3.2
    @Test
    public void testAdd(){
        Graph<Integer> graph = Graph.empty();
        //test normal case
        assertTrue(graph.add(new Integer(7)));
        //test case that the vertex label is repeated
        assertFalse(graph.add(new Integer(7)));
    }
    @Test
    public void testSet(){
        Graph<Long> graph = Graph.empty();
        Long liLei = new Long(0xffff);
        Long hanMeiMei = new Long(0);
        Long xiaoMing = new Long(500);
        graph.add(liLei);
        graph.add(hanMeiMei);
        graph.add(xiaoMing);
        //add edge nonzero
        assertEquals(0,graph.set(liLei,hanMeiMei,5));
        assertEquals(0,graph.set(liLei,xiaoMing,4));
        //添加一个节点不存在的边
        Long xiaoHong = new Long(77);
        assertEquals(0,graph.set(xiaoHong,xiaoMing,6));
        assertEquals(0,graph.set(new Long(88),new Long(99),6));
        //chang edge's weight
        assertEquals(5,graph.set(liLei,hanMeiMei,7));
        assertEquals(4,graph.set(liLei,xiaoMing,2));
        //remove edge
        assertEquals(7,graph.set(liLei,hanMeiMei,0));
        assertEquals(2,graph.set(liLei,xiaoMing,0));
        //删去一个节点不存在的边
        assertEquals(0,graph.set(new Long(233),new Long(22),0));
    }
    @Test
    public void testRemove(){
        Graph<Character> graph = Graph.empty();
        Character liLei = new Character('a');
        Character hanMeiMei = new Character('b');
        Character xiaoMing = new Character('c');
        graph.add(liLei);
        graph.add(hanMeiMei);
        graph.add(xiaoMing);

        graph.set(liLei,hanMeiMei,5);
        graph.set(liLei,xiaoMing,4);
        Character xiaoHong = new Character('d');
        graph.set(xiaoHong,xiaoMing,6);

        //remove a vertex included
        assertTrue(graph.remove(liLei));
        assertTrue(graph.remove(hanMeiMei));
        //remove a vertex not included
        assertFalse(graph.remove(liLei));
        Character xiaoWang =new Character('e');
        assertFalse(graph.remove(xiaoWang));
    }
    @Test
    public void testVertices(){
        Graph<Byte> graph = Graph.empty();
        Byte liLei = new Byte("123");
        Byte hanMeiMei = new Byte("111");
        Byte xiaoMing = new Byte("78");
        Byte xiaoHong = new Byte("27");
        graph.add(liLei);
        graph.add(hanMeiMei);
        graph.add(xiaoMing);
        graph.add(xiaoHong);
        Set<Byte> vertices =graph.vertices();
        Set<Byte> set = new HashSet<>();
        set.add(liLei);
        set.add(hanMeiMei);
        set.add(xiaoMing);
        set.add(xiaoHong);
        //normal test case
        assertEquals(vertices,set);
        //remove a vertex and test again
        graph.remove(liLei);
        set.remove(liLei);
        vertices = graph.vertices();
        assertEquals(vertices,set);
        //add a vertex and test again
        Byte liLei1 = new Byte("33");
        graph.add(liLei1);
        set.add(liLei1);
        vertices =graph.vertices();
        assertEquals(vertices,set);
    }
    @Test
    public void testSources(){
        Graph<BigDecimal> graph = Graph.empty();
        BigDecimal liLei = new BigDecimal(777);
        BigDecimal hanMeiMei = new BigDecimal(888);
        BigDecimal xiaoMing = new BigDecimal(999);
        graph.add(liLei);
        graph.add(hanMeiMei);
        graph.add(xiaoMing);

        graph.set(liLei,hanMeiMei,5);
        graph.set(liLei,xiaoMing,4);
        BigDecimal xiaoHong = new BigDecimal(3333);
        graph.set(xiaoHong,xiaoMing,6);
        Map<BigDecimal,Integer> sources= graph.sources(xiaoMing);
        Map<BigDecimal,Integer> map = new HashMap<>();
        //Test a normal case
        map.put(liLei,4);
        map.put(xiaoHong,6);
        assertEquals(map,sources);
        //test remove a vertex
        graph.set(liLei,xiaoMing,0);
        map.remove(liLei);
        sources=graph.sources(xiaoMing);
        assertEquals(map,sources);
        //test add an edge
        graph.set(hanMeiMei,xiaoMing,7);
        map.put(hanMeiMei,7);
        sources=graph.sources(xiaoMing);
        assertEquals(map,sources);
    }
    @Test
    public void testTargets(){
        Graph<BigInteger> graph = Graph.empty();
        BigInteger liLei = new BigInteger("777");
        BigInteger hanMeiMei = new BigInteger("888");
        BigInteger xiaoMing = new BigInteger("999");
        graph.add(liLei);
        graph.add(hanMeiMei);
        graph.add(xiaoMing);

        graph.set(liLei,hanMeiMei,5);
        graph.set(liLei,xiaoMing,4);
        BigInteger xiaoHong = new BigInteger("1000");
        graph.set(xiaoHong,xiaoMing,6);
        Map<BigInteger,Integer> sources= graph.targets(liLei);
        Map<BigInteger,Integer> map = new HashMap<>();
        map.put(hanMeiMei,5);
        map.put(xiaoMing,4);
        assertEquals(map,sources);
        //测试删去一条边
        graph.set(liLei,xiaoMing,0);
        map.remove(xiaoMing);
        sources= graph.targets(liLei);
        assertEquals(map,sources);
        //test add an edge
        graph.set(liLei,xiaoMing,7);
        map.put(xiaoMing,7);
        sources= graph.targets(liLei);
        assertEquals(map,sources);
    }
}
