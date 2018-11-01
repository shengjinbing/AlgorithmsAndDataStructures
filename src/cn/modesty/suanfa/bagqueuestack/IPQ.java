package cn.modesty.suanfa.bagqueuestack;

public interface IPQ {
    /**
     * 比较
     *
     * @param i
     * @param j
     */
    boolean less(int i,int j);

    /**
     * 交换
     *
     * @param i
     * @param j
     */
    void exch(int i,int j);

    /**
     * 由下至上的堆有序化（上浮）
     *
     * @param k
     */
    void swim(int k);

    /**
     * 由上至下的堆有序化（下沉）
     *
     * @param k
     */
    void sink(int k);
}
