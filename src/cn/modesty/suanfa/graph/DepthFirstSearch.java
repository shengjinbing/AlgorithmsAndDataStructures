package cn.modesty.suanfa.graph;

import java.util.Stack;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    private int[] edgeTo;//从起点到一个顶点的已知路径上的最后一个定点
    private  int s;
    public DepthFirstSearch(Graph G,int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G,s);
    }

    /**
     * 深度优先搜索，给定任意顶点开始
     *
     * @param g
     * @param v
     */
    private void dfs(Graph g, int v) {
       marked[v] = true;
       count++;
       for (int w:g.adj(v)){
           if (!marked[w]){
               edgeTo[w] = v;
               dfs(g,w);
           }
       }
    }

    public boolean marked(int w){
        return marked[w];
    }

    public int count(){
        return count;
    }

    public void printEdge(){
        for (int i = 0; i < edgeTo.length; i++) {
            System.out.println(edgeTo[i]);
        }
    }
    public Iterable<Integer> pathTo(int v){
      if (!marked(v))return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
