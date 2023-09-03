/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //  测试空图
    //  测试有节点但没有边的图
    //  测试有边有点的图
    
    //tests for ConcreteVerticesGraph.toString()
    @Test
    public void testToString(){
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
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   测试vertex的各种方法
    
    //  tests for operations of Vertex
    @Test
    public void testGetVertex(){
        Vertex vertex =new Vertex("XiaoMing",new HashMap<>());
        assertEquals("XiaoMing",vertex.getVertex());
    }
    @Test
    public void testGetEdges(){
        Vertex vertex = new Vertex("XiaoMing",new HashMap<>());
        Map<String,Integer> map = new HashMap<>();
        assertEquals(map,vertex.getEdges());
    }
    @Test
    public void testAddEdge(){
        Vertex vertex = new Vertex("XiaoMing",new HashMap<>());
        Map<String,Integer> map = new HashMap<>();
        assertEquals(map,vertex.getEdges());
        vertex.addEdge("HanMei",3);
        map.put("HanMei",3);
        assertEquals(map,vertex.getEdges());
    }
    @Test
    public void testRemoveEdge(){
        Vertex vertex = new Vertex("XiaoMing",new HashMap<>());
        Map<String,Integer> map = new HashMap<>();
        assertEquals(map,vertex.getEdges());
        vertex.addEdge("HanMei",3);
        map.put("HanMei",3);
        assertEquals(map,vertex.getEdges());
        map.remove("HanMei");
        vertex.removeEdge("HanMei");
        assertEquals(map,vertex.getEdges());
    }
    @Test
    public void testVertexToString(){
        Vertex vertex =new Vertex("XiaoMing",new HashMap<>());
        //Vertex vertex1 = new Vertex("XiaoHong",new HashMap<>());
        vertex.addEdge("XiaoHong",4);
        vertex.addEdge("XiaoQiang",5);
        assertEquals("XiaoMing --4--> XiaoHong\nXiaoMing --5--> XiaoQiang\n",vertex.toString());
    }
}
