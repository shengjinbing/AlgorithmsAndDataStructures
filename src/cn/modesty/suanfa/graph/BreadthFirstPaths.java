package cn.modesty.suanfa.graph;

import edu.princeton.cs.algs4.Queue;

import java.util.ArrayDeque;

import java.util.concurrent.LinkedBlockingQueue;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;//从起点到一个顶点的已知路径上的最后一个定点
    private  int s;

    public BreadthFirstPaths(Graph G,int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }

    public void bfs(Graph G, int s){
        marked[s] = true;
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            for ( int w:G.adj(v)) {
                if (!marked[w]){
                    //没有被标记
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }
    public void printEdge(){
        for (int i = 0; i < edgeTo.length; i++) {
            System.out.println(edgeTo[i]);
        }
    }
}
