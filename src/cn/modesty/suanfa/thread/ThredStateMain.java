package cn.modesty.suanfa.thread;

/**
 * 线程的状态和转变
 */
public class ThredStateMain {
    static Thread1 thread1;

    public static void main(String[] args) {
        thread1 = new Thread1();
        thread1.start();//启动线程
        join();
    }


    /**
     * 1.join( )：等待该线程终止。
     * 2.join(long millis)：等待该线程终止的时间最长为 millis 毫秒。超时为 0 意味着要一直等下去。
     * 3.join(long millis, int nanos)：等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒。
     * 作用：
     * join方法的作用是父线程等待子线程执行完成后再执行，换句话说就是将异步执行的线程合并为同步的线程。
     */
    private static void join() {
        try {
            //main线程要等待thread1线程的结束，才可以往下执行
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "结束");
    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            int a = 1;
            for (int i = 1; i < 5; i++) {
                a += i;
            }
            System.out.println("线程" + getName() + "结束，Count a = " + a);
        }
    }


}
