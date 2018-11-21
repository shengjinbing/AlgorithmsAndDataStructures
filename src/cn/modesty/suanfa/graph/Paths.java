package cn.modesty.suanfa.graph;

public class Paths {
    private int s;
    private boolean[] marked;
    private Graph G;

    public Paths(Graph G,int s) {
        this.s = s;
        this.G = G;
        marked = new boolean[G.V()];
    }

    /**
     * 是否存在从s到v的路径
     *
     * @param v
     * @return
     */
    public boolean hasPathTo(int v){
        for (Integer i : G.adj(v)) {
            if (s == i){
                return true;
            } else{

            }
        }
        return false;
    }

    /**
     * s到v的路径，如果不存在返回null
     *
     * @param v
     * @return
     */
    /*public Iterable<Integer> pathTo(int v){

    }*/
}
