package cn.modesty.suanfa.graph;

import edu.princeton.cs.algs4.Bag;

public class Graph {
    private final int V;//顶点数目
    private int E;//边的数目
    private Bag<Integer>[] adj;//邻接表

    public Graph(int v) {
        V = v;
        this.E = 0;
        adj = (Bag<Integer>[])new Bag[V];//初始化邻接表
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>(); //将所有链表初始化为空
        }

    }

    public int V(){return V;};
    public int E(){return E;};

    /**
     * 给定俩顶点
     *
     * @param v
     * @param w
     */
    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}
