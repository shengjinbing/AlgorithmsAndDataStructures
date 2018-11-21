package cn.modesty.suanfa.graph;

public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G) {
        this.marked = new boolean[G.V()];
        this.color = new  boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]){
                dfs(G,i);
            }
        }
    }

    private void dfs(Graph G,int v){
        marked[v] = true;
        for (int w :G.adj(v)){
            if (!marked[w]){
                color[w] = !color[v];
                dfs(G,w);
            }else if (color[w] == color[v]){
                //循环一圈
                isTwoColorable = false;
            }

        }
    }

    public boolean isBipartite(){
        return isTwoColorable;
    }

}
