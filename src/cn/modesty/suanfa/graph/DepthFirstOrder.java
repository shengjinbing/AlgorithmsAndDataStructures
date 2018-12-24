package cn.modesty.suanfa.graph;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {
    private boolean[]      marked;
    private Queue<Integer> pre;//所有顶点的前序排列
    private Queue<Integer> post;//所有顶点的后续排列
    private Stack<Integer> reversepost;//所有顶点的逆后序排列

    public DepthFirstOrder(Digraph g){
        pre = new Queue<>();
        post = new Queue<>();
        reversepost = new Stack<>();
        marked = new boolean[g.V()];

        for (int i = 0; i < g.V(); i++) {
            dfs(g,i);
        }
    }


    private void dfs(Digraph g, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (Integer w : g.adj(v)) {
            if (!marked[w]){
                dfs(g,w);
            }
        }
        post.enqueue(v);
        reversepost.push(v);
    }

    public Queue<Integer> Pre() {
        System.out.println("\n");
        while (!pre.isEmpty()){
            System.out.print(pre.dequeue()+",");
        }
        return pre;
    }

    public Queue<Integer> Post() {
        System.out.println("\n");
        while (!post.isEmpty()){
            System.out.print(post.dequeue()+",");
        }
        return post;
    }

    public Stack<Integer> Reversepost() {
        System.out.println("\n");
        while (!reversepost.isEmpty()){
            System.out.print(reversepost.pop()+",");
        }
        return reversepost;
    }
}