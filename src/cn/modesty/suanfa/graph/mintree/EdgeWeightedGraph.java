package cn.modesty.suanfa.graph.mintree;

import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedGraph {
    private final int         V;//顶点总数
    private       int         E;//边的总数
    private       Bag<Edge>[] adj;

    public EdgeWeightedGraph(int v) {
        this.V = v;
        this.E = 0;
        adj  = new Bag[v];
        for (int i = 0; i < V; i++) {
            adj[i]  = new Bag<>();
        }
    }

    /**
     * @return 顶点数
     */
    public int getV() {
        return V;
    }

    /**
     * @return 边数
     */
    public int getE() {
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }
}
