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
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   vertices中元素表示图的顶点
    //   edges中每个元素表示图中一条有向边
    // Representation invariant:
    //   vertices中应该是无重复的
    //   edges中应该无重复的
    // Safety from rep exposure:
    //   采用防御式拷贝
    //  除变值器外，其他方法不会改变值
    //  在变值器中使用checkRep检查循环不变量

    /**
     * constructor
     */
    public ConcreteEdgesGraph() {
    }

    /** checkRep
     * if the edges has repeated element, then assert
     */
    void checkRep(){
        Edge<L> edge1;
        Edge<L> edge2;
        for (int i =0; i < this.edges.size(); i++) {
            edge1 = this.edges.get(i);
            for (int j = i+1; j < this.edges.size(); j++) {
                edge2 = this.edges.get(j);
                if(edge1.equals(edge2)||!vertices.contains(edge1.getSource())||!vertices.contains(edge1.getTarget())){
                    assert false;
                }
            }
        }
    }
    @Override public boolean add(L vertex) {
        if (vertices.contains(vertex)){
            return false;
        }
        this.vertices.add(vertex);
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
        //遍历边集
        for (Edge<L> edge:
             edges) {
            //若找到边
            if(source.equals(edge.getSource())&&target.equals(edge.getTarget())){
                beforeWeight = edge.getWeight();
                Edge<L> temp = edge.setWeight(weight);
                edges.remove(edge);
                if (temp != null) {
                    edges.add(temp);
                    if(!vertices.contains(source)){
                        this.add(source);
                    }
                    if(!vertices.contains(target)){
                        this.add(target);
                    }
                }
                checkRep();
                return beforeWeight;
            }
        }
        //若运行到此，说明未找到
        if(weight>0){
            edges.add(new Edge<L>(source,target,weight));
            if(!vertices.contains(source)){
                this.add(source);
            }
            if(!vertices.contains(target)){
                this.add(target);
            }
        }
        checkRep();
        return 0;
    }
    
    @Override public boolean remove(L vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("vertex is null");
        }
        if(!vertices.contains(vertex)){
            checkRep();
            return false;
        }
        vertices.remove(vertex);
        Iterator<Edge<L>> iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if(vertex.equals(edge.getSource())||vertex.equals(edge.getTarget())){
                edges.remove(edge);
                iterator = edges.iterator();
            }
        }
        checkRep();
        return true;
    }
    
    @Override public Set<L> vertices() {
        checkRep();
        return new HashSet<L>(this.vertices);
    }
    
    @Override public Map<L, Integer> sources(L target) {
        if (target == null) {
            throw new IllegalArgumentException("target is null");
        }
        Map<L,Integer> result = new HashMap<>();
        for (Edge<L> edge:
             edges) {
            if(target.equals(edge.getTarget())){
                result.put(edge.getSource(), edge.getWeight());
            }
        }
        checkRep();
        return result;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        if (source == null) {
            throw new IllegalArgumentException("source is null");
        }
        Map<L,Integer> result = new HashMap<>();
        for (Edge<L> edge:
                edges) {
            if(source.equals(edge.getSource())){
                result.put(edge.getTarget(), edge.getWeight());
            }
        }
        checkRep();
        return result;
    }
    
    //  toString()

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("vertex:"+vertices+"\n"+"edges:\n");
        for (int i = 0; i < edges.size(); i++) {
            sb.append(edges.get(i).toString()+"\n");
        }
        checkRep();
        return sb.toString();
    }


}

/**
 *  specification
 *  一个不可变的类型，表示图中的一条边
 *  表示由source指向target的一条边
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {

    /**
     * source 边的起点
     * target 边的终点
     */
    private final L source;
    private final L target;

    private final Integer weight;
    // Abstraction function:
    //   表示由source指向target的一条边
    // Representation invariant:
    //   source and target are not null
    // Safety from rep exposure:
    //   使用不可变的字符串类和int来作为rep
    //  set方法返回新副本

    /**
     * 构造方法
     * 创造一条由source指向target的一条边
     * @param source 边的起点
     * @param target 边的终点
     * @param weight 边的权值
     */
    public Edge(L source, L target,int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }


    /**
     * checkRep
     */
    public void checkRep(){
        if(this.source==null||this.target==null){
            assert false;
        }
    }
    //  methods

    /**
     * getter
     * @return
     */
    public L getSource() {
        checkRep();
        return source;
    }

    /**
     * getter
     * @return
     */
    public L getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    public Edge<L> setWeight(int weight) {
        if(weight==0){
            return null;
        }
        return new Edge<>(this.source,this.target,weight);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Edge)){
            throw new IllegalArgumentException("obj is not an edge");
        }
        Edge edge = (Edge) obj;
        if(edge.getSource().equals(this.getSource())&&edge.getTarget().equals(this.getTarget())&&edge.getWeight()==this.getWeight()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, weight);
    }
    //  toString()

    @Override
    public String toString() {
        return source+" --"+weight+"--> "+target;
    }
}
