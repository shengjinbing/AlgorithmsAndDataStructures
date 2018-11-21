package cn.modesty.suanfa.graph;

/**
 * 检查图中是否存在环，假设不存在自环和平行边
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        this.marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]){
                dfs(G,i,i);
            }
        }
    }

    private void dfs(Graph G,int v,int u){
        marked[v] = true;
        for (int w :G.adj(v)){
            if (!marked[w]){
                dfs(G,w,v);
            }else if (w != u){
                //循环一圈，看看收尾是否相等
                hasCycle = true;
            }

        }
    }

    public boolean isHasCycle(){
        return hasCycle;
    }
}
