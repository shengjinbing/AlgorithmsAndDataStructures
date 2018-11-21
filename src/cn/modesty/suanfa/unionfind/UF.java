package cn.modesty.suanfa.unionfind;

public class UF {
    private int[] id;
    private int[] sz;
    private int   count;

    public UF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            //初始化每个触点的值
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }
    /*****************快速find*************************/
    /**
     * p（0到N-1）所在的分量的标识符
     *
     * @param p
     * @return
     */
//    public int find(int p){
//        return id[p];
//    }
    /**
     * 在P和q之间添加一条连接
     *
     * @param p
     * @param q
     */
//    public void union(int p,int q){
//        int pId = find(p);
//        int qId = find(q);
//
//        if (pId == qId)return;
//        for (int i = 0; i < id.length; i++) {
//            if (id[i] == pId)id[i] = qId;
//        }
//        count--;
//    }

    /*****************快速union*************************/
   /* public int find(int p) {
        while (p != id[p])  p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) return;
        id[pId] = id[qId];
        count--;
    }*/

    /*****************快速union*************************/
    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if (i == j) return;
        //将小树的根节点连接到大树上的根节点
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i]+=sz[j];
        }
        count--;
    }


    /**
     * 如果p和q存在于同一个分量重则返回true
     *
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int getCount() {
        return count;
    }
}
