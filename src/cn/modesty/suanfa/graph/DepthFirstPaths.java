package cn.modesty.suanfa.graph;

import java.util.Stack;

public class DepthFirstPaths {
    private       boolean[] marked;//这个顶点上调过dfs()了吗？
    private       int[]     edgeTo;//从起点到一个顶点的已知路径上的最后一个顶点
    private final int       s;//起点

    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph g, int s) {
        marked[s] = true;
        for (int w :g.adj(s)){
            if (!marked[w]){
                edgeTo[w] = s;
                dfs(g,w);
            }
        }
    }

    /**
     * 是否有路径到达v
     *
     * @param v
     * @return
     */
    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v))return null;
        Stack<Integer> stack = new Stack<>();
        for ( int x = v; x != s ; x = edgeTo[x]) {
             stack.push(x);
        }
        stack.push(s);
        return stack;
    }
}
