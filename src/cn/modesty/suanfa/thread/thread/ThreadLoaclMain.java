package cn.modesty.suanfa.thread.thread;

/**
 * class Thread {
 *   //内部持有ThreadLocalMap
 *   ThreadLocal.ThreadLocalMap threadLocals  = null;
 *   ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;//可继承的
 * }
 * class ThreadLocal<T>{
 *   public T get() {
 *     //首先获取线程持有的
 *     //ThreadLocalMap
 *     ThreadLocalMap map =
 *       Thread.currentThread()
 *         .threadLocals;
 *     //在ThreadLocalMap中
 *     //查找变量
 *     Entry e =
 *       map.getEntry(this);
 *     return e.value;
 *   }
 *   static class ThreadLocalMap{
 *     //内部是数组而不是Map
 *     Entry[] table;
 *     //根据ThreadLocal查找Entry
 *     Entry getEntry(ThreadLocal key){
 *       //省略查找逻辑
 *     }
 *     //Entry定义
 *     static class Entry extends
 *     WeakReference<ThreadLocal>{
 *       Object value;
 *     }
 *   }
 * }
 *
 * 1）根据哈希码和数组长度求元素放置的位置，即数组下标
 * 2）从第一步得出的下标开始往后遍历，如果key相等，覆盖value，如果key为null,用新key、value覆盖，
 *    同时清理历史key=null的陈旧数据
 * 3）如果超过阀值，就需要再哈希：
 *  private static int nextIndex(int i, int len) {
 *             return ((i + 1 < len) ? i + 1 : 0);
 * }
 * 清理一遍陈旧数据
 * >= 3/4阀值,就执行扩容，把table扩容2倍==》注意这里3/4阀值就执行扩容，避免迟滞
 * 把老数据重新哈希散列进新table
 */
public class ThreadLoaclMain {
    //一个线程中是可以有多个不同的ThreadLocal对象的
    public String string = new String();//全局变量
    public ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<String>(){
        /**
         * 子线程根据childValue函数获取到了父线程的变量值
         * @param parentValue
         * @return
         */
        @Override
        protected String childValue(String parentValue) {
            return super.childValue(parentValue+"父类修改了");
        }
    };
    public static void main(String[] args) {
        ThreadLoaclMain threadLocalTest = new ThreadLoaclMain();
        threadLocalTest.set();

        Thread t1 =new Thread(){
            @Override
            public void run() {
                super.run();
                threadLocalTest.get();
            }
        };
        threadLocalTest.get();
        t1.start();
    }
    public  void set(){
        string = "hello string ";
        //set方法中将该值保存在线程的threadLocals中
        threadLocal.set("hello threadLocal ");
        //set方法中将该值保存在线程的inheritableThreadLocals 中
        inheritableThreadLocal.set("hello inheritableThreadLocal ");
    }
    private void get(){
        System.out.println(string);
        System.out.println(threadLocal.get());
        System.out.println(inheritableThreadLocal.get());
    }
}
