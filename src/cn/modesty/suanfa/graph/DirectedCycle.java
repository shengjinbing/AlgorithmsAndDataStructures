package cn.modesty.suanfa.graph;

import java.util.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;

    private Stack<Integer> cycle;//有向环中的所以定点
    private boolean[]      onStack;//递归调用的栈上的所以顶点

    public DirectedCycle(Digraph G){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]){
                dfs(G,i);
            }
        }

    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w :g.adj(v)){
            if (hasCycle()){
                return;
            }else if (!marked[w]){
                edgeTo[w] = v;
                dfs(g,w);
            }else if (onStack[w]){
               //判断是否有环
                cycle = new Stack<>();
                for (int x = v; x != w ; x = edgeTo[x]) {
                      cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
            onStack[v] = false;
        }
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Stack<Integer> getCycle() {
        if (cycle != null){
            System.out.println(cycle.size());
        }
        return cycle;
    }
}
