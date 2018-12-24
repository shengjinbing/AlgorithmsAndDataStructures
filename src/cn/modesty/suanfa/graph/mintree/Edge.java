package cn.modesty.suanfa.graph.mintree;

import java.text.Format;

public class Edge implements Comparable<Edge>{
    private final int v;//顶点之一
    private final int w;//另一个顶点
    private final double weight;//边的权重

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (weight - o.weight < 0)return -1;
        else if (weight - o.weight == 0)return 0;
        else return 1;
    }

    /**
     * 权重
     *
     * @return
     */
    public double weight(){
        return weight;
    }

    /**
     * 边两端的顶点之一
     *
     * @return
     */
    public int either(){
        return v;
    }

    /**
     * 边两边顶点之一
     *
     * @param vertex
     * @return
     */
    public int other(int vertex){
      if (v == vertex)return w;
      else if (w == vertex)return v;
      else throw new RuntimeException("Inconsistent edge");
    }

    public String toString(){
        return String.format("%d-%d %.2f",v,w,weight);
    }
}
