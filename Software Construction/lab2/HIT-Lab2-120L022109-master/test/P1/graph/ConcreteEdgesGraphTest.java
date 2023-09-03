/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //  测试空图
    //  测试有节点但没有边的图
    //  测试有边有点的图
    
    //  tests for ConcreteEdgesGraph.toString()
    @Test
    public void testGraphToString(){
        Graph<String> graph = emptyInstance();
        assertEquals("vertex:[]\nedges:\n",graph.toString());
        graph.set("LiLei","HanMeiMei",0);
        assertEquals("vertex:[]\nedges:\n",graph.toString());
        graph.set("LiLei","HanMeiMei",4);
        graph.set("LiLei","XiaoHong",7);
        assertEquals("vertex:[HanMeiMei, XiaoHong, LiLei]\nedges:\nLiLei --4--> HanMeiMei\nLiLei --7--> XiaoHong\n",
                graph.toString());
    }
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //  测试各个方法
    
    //  tests for operations of Edge
    @Test
    public void testGetSource() {
        Edge edge = new Edge("LiLei","HanMeiMei",3);
        assertEquals("LiLei",edge.getSource());
    }
    @Test
    public void testGetTarget() {
        Edge edge = new Edge("LiLei","HanMeiMei",3);
        assertEquals("HanMeiMei",edge.getTarget());
    }

    @Test
    public void testGetWeight() {
        Edge edge = new Edge("LiLei","HanMeiMei",3);
        assertEquals(3,edge.getWeight());
    }
    @Test
    public void testEdgeEquals(){
        Edge edge = new Edge("LiLei","HanMeiMei",3);
        Edge edge1 = new Edge("LiLei","HanMeiMei",3);
        Edge edge2 = new Edge("LiLei","HanMeiMei",4);
        assertTrue(edge.equals(edge1));
        assertFalse(edge.equals(edge2));
    }
    @Test
    public void testSetWeight(){
        Edge edge = new Edge("LiLei","HanMeiMei",3);
        assertEquals(new Edge<String>("LiLei","HanMeiMei",2),edge.setWeight(2));
    }
    @Test
    public void testEdgeToString(){
        Edge edge = new Edge("LiLei","HanMeiMei",3);
        assertEquals("LiLei --3--> HanMeiMei",edge.toString());
    }
}
