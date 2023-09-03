/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   vertices的元素中
    //      vertex为节点
    //      edges表示以vertex为source的边
    // Representation invariant:
    //   vertices中元素不能有相同的节点vertex
    // Safety from rep exposure:
    //   防御式拷贝


    /**
     * constructor
     */
    public ConcreteVerticesGraph() {
    }

    /**
     * checkRep
     * vertices中元素不能有相同的节点vertex
     */
    void checkRep(){
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i+1; j < vertices.size(); j++) {
                if(vertices.get(i).getVertex().equals(vertices.get(j).getVertex())){
                    assert false;
                }
            }
        }
    }
    @Override public boolean add(L vertex) {
        if(vertex==null){
            throw new IllegalArgumentException("vertex is null");
        }
        for (Vertex<L> vertex1:
                vertices) {
            if(vertex1.getVertex().equals(vertex)){
                checkRep();
                return false;
            }
        }
        vertices.add(new Vertex<L>(vertex,new HashMap<>()));
        checkRep();
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
        if(source==null||target==null){
            throw new IllegalArgumentException();
        }
        if(weight <0){
            throw new IllegalArgumentException("weight is negative");
        }
        int beforeWeight;
        //if(weight>0){
            for (Vertex<L> vertex:
                    vertices) {
                //寻找节点source
                //若找到，进行下一步处理
                if(vertex.getVertex().equals(source)){
                    for (L str:
                         vertex.getEdges().keySet()) {
                        //遍历节点source的每一条边
                        if(target.equals(str)){
                            //若找到对应边，获取beforeWeight
                            beforeWeight = vertex.getEdges().get(str);
                            if(weight>0){
                                //若weight>0，则修改边
                                vertex.addEdge(str,weight);
                            }else {
                                //若weight=0，则删除边
                                vertex.removeEdge(str);
                            }
                            checkRep();
                            return beforeWeight;
                        }
                    }
                    //到这里，未返回
                    //说明以source为起点的边中没有指向target的
                    for (Vertex<L> vertex1:
                            vertices) {
                        //寻找target
                        if(vertex1.getVertex().equals(target)){
                            //若找到target，且weight>0，则向source中添加边
                            //若weight=0，则不处理
                            if (weight > 0) {
                                vertex.addEdge(target,weight);
                            }
                            checkRep();
                            return 0;
                        }
                    }
                    //若未找到target，则在vertices中添加节点及对应的边
                    if (weight > 0) {
                        this.add(target);
                        vertex.addEdge(target,weight);
                    }
                    checkRep();
                    return 0;
                }
            }
            //若循环完成，未结束运行，说明不存在以source为起点的边
            //此时，若weight为0，则不处理，直接返回0
            //若weight>0，则在vertices中添加节点及对应的边
            if(weight>0){
                this.add(source);
                this.vertices.get(vertices.size()-1).addEdge(target,weight);
                //寻找target，若vertices中没有则添加节点target
                for (Vertex<L> vertex1:
                        vertices) {
                    if(vertex1.getVertex().equals(target)){
                        checkRep();
                        return 0;
                    }
                }
                this.add(target);
            }

        //}
        checkRep();
        return 0;
    }
    
    @Override public boolean remove(L vertex) {
        if(vertex == null){
            throw new RuntimeException();
        }
        //判断vertices中有无节点vertex
        boolean flag =false;
        for (Vertex<L> vertex1:
                vertices) {
            if(vertex1.getVertex().equals(vertex)){
                vertices.remove(vertex1);
                flag =true;
                break;
            }
        }

        if(!flag){
            checkRep();
            return false;
        }
        for (Vertex<L> vertex1:
             vertices) {
            if(vertex1.getEdges().containsKey(vertex)) {
                vertex1.removeEdge(vertex);
            }
        }
        checkRep();
        return true;
    }
    
    @Override public Set<L> vertices() {
        Set<L> result = new HashSet<>();
        for (Vertex<L> v:
             vertices) {
            result.add(v.getVertex());
        }
        checkRep();
        return result;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        if(target==null){
            throw new RuntimeException();
        }
        Map<L,Integer> result = new HashMap<>();
        for (Vertex<L> v:
             vertices) {
            for (L str:
                 v.getEdges().keySet()) {
                if(target.equals(str)){
                    result.put(v.getVertex(),v.getEdges().get(str));
                }
            }
        }
        checkRep();
        return result;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        if(source==null){
            throw new RuntimeException();
        }
        for (Vertex<L> v:
             vertices) {
            if(v.getVertex().equals(source)){
                checkRep();
                return new HashMap<>(v.getEdges());
            }
        }
        checkRep();
        return new HashMap<>();
    }
    
    //toString()

    @Override
    public String toString() {
        StringBuilder edges = new StringBuilder("edges:\n");
        //StringBuilder vertexes = new StringBuilder("vertex:[");
        Set<L> vertexes = new HashSet<>();
        for (Vertex<L> v:
             vertices) {
            vertexes.add(v.getVertex());
            edges.append(v);
        }

        checkRep();
        return "vertex:"+vertexes.toString()+"\n"+edges.toString();
    }
}

/**
 * specification
 * 表示一个节点和以它为source的所有边
 * Mutable.
 * vertex为节点
 * edges表示以vertex为source的边组成的集合
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    

    /**fields
     * vertex为节点
     * edges表示以vertex为source的边组成的集合
     */
    private final L vertex;
    private final Map<L,Integer> edges;
    // Abstraction function:
    //   vertex为节点
    //   edges表示以vertex为source的边集
    // Representation invariant:
    //   edges中无重复元素
    // Safety from rep exposure:
    //  防御式拷贝
    


    /**
     * constructor
     * @param vertex
     * @param edges
     */
    public Vertex(L vertex, Map<L, Integer> edges) {
        this.vertex = vertex;
        this.edges = edges;
    }

    /** checkRep
     * always true
     */
    public void checkRep(){}
    //  methods
    //setter and getter

    public L getVertex() {
        checkRep();
        return vertex;
    }


    public void addEdge(L target,int weight){
        checkRep();
        this.edges.put(target,weight);
    }
    public void removeEdge(L target){
        checkRep();
        this.edges.remove(target);
    }
    public Map<L, Integer> getEdges() {
        checkRep();
        return new HashMap<>(edges);
    }



    //  toString()

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (L str:
                edges.keySet()) {
            sb.append(vertex+" --"+edges.get(str)+"--> "+str+"\n");
        }
        checkRep();
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex<?> vertex1 = (Vertex<?>) o;
        return vertex.equals(vertex1.vertex) && edges.equals(vertex1.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex, edges);
    }
}
