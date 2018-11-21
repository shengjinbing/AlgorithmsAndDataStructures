package cn.modesty.suanfa.graph;

public class Search {
    private int s;
    private boolean[] marked;
    private Graph G;

    public Search(Graph G,int s) {
        this.G = G;
        this.s = s;
        this.marked = new boolean[G.V()];
    }

    /**
     * v和s是联通的吗
     *
     * @param v
     * @return
     */
    public boolean marked(int v){
        //之后学习union-find算法，再写
      return true;
    }

    /**
     * 与s联通的顶点总数
     */
    public void count(){

    }
}
