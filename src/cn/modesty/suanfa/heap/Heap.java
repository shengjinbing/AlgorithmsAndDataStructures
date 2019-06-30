package cn.modesty.suanfa.heap;

/**
 * 数据结构二叉堆能够很好的实现优先队列的基本操作
 */
public class Heap {
    private int N = 0;//将元素存储在a[1..N]中，a[0]没有使用
    private int[] a;

    public Heap(int maxN) {
        this.a = new int[maxN];
    }


    /**
     * 插入元素
     * @param value
     */
    public void insert(int value){
        a[++N] = value;
        swim(N);
    }

    /**
     * 删除最大元素，也就是堆顶元素
     * @return
     */
    public int delMax(){
        int maxValue = a[1];
        //将最后一个元素放在第一个位子上
        exch(1,N--);
        //将最后一个元素为空
        a[N++] = Integer.parseInt(null);
        //下沉第一个元素
        sink(1);
        return maxValue;
    }


    /**
     * 上浮操作，从底部到顶部
     * @param k
     */
    private void swim(int k){
        //大于1并且这个节点大于它的父节点
        while (k >1 && less(k/2,k)){
            //交换它和父节点
            exch(k/2,k);
            //继续向上找
            k = k/2;
        }
    }

    /**
     * 下沉
     * @param i
     */
    private  void sink( int i) {
        while (2*i <= N){
            //下沉节点需要每次需要和它的左右节点都进行比较，小于节点需要交换
            int j = 2*i;
            //j < n这样判断的意义：最后一个元素是最大的,j+1会事最大元素的下标，这样就会交换与j的值
            if (j < N &&less(j,j+1))j++;
            if (!less(i,j))break;
            exch(i,j);
            i = j;
        }
    }

    /**
     * 第一个元素是否小于第二个元素
     * @param i
     * @param j
     * @return
     */
    private  boolean less( int i, int j){
        // System.out.println(i+","+j);
        return a[i] < a[j];
    }

    /**
     * 交换数据
     * @param i
     * @param j
     */
    private  void exch(int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
