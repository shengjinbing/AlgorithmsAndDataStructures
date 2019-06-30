package cn.modesty.suanfa.bagqueuestack;

/**
 * 有限
 *
 * @param <key>
 */
public class MaxPQ<key extends Comparable<key>> implements IPQ{

    private int capacity = 10;//默认数组大小
    private key[] pq;
    private int size = 0;
    public MaxPQ(int n){
        capacity = n;
        pq = (key[]) new Comparable[n+1];
    }

    /**
     *插入一个元素
     *
     * @param data
     */
    public void insert(key data){
       pq[++size] = data;
       swim(size);
    }

    public key delMax(){
        key temp = pq[1];
        pq[1] = pq[size];
        pq[size--] = null;//防止对象游离
        sink(1);
        return temp;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    @Override
    public void exch(int i, int j) {
        key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    @Override
    public void swim(int k) {
      while (k > 1 && less(k/2,k)){
          exch(k,k/2);
          k = k/2;
      }
    }

    @Override
    public void sink(int k) {
      while (2*k <= size){
          int j = 2*k;
          if (j < size && less(j,j+1)) j++;//下沉需要跟子节点大的比
          if(!less(k,j))break;//大于两个子节点不需要下沉
          exch(k,j);
          k = j;
      }
    }

    public int getCapacity() {
        return capacity;
    }

    public key[] getPq() {
        return pq;
    }

    public int getSize() {
        return size;
    }
}
