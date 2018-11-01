package cn.modesty.suanfa.bagqueuestack;

import java.util.Iterator;

/**
 * 下压（LIFO）栈（能够动态调整数组大小的实现）
 * @param <T>
 */
public class ResizeingArrayStack<T> implements Iterable<T> {
    private T[] a = (T[]) new Object[1];

    //栈元素
    private int N = 0;
    public void push(T t){
       if (N >= a.length)reSize(a.length * 2);
           a[N++] = t;
    }

    public T pop(){
        T datum = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) reSize(a.length / 2);
        return datum;
    }

    /**
     * 扩容
     */
    public void reSize(int max){
        T[] reziData = (T[]) new Object[max];
        for (int i = 0; i < N; i++) {
            reziData[i] = a[i];
        }
        a = reziData;
    }


    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T>{

        private int i = N;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return a[--i];
        }
    }
}
