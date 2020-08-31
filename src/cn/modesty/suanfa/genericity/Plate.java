package cn.modesty.suanfa.genericity;

public interface Plate<T> {
    public void set(T t);
    public T get();
}
