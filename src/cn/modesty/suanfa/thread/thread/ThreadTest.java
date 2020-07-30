package cn.modesty.suanfa.thread.thread;

import java.util.concurrent.locks.LockSupport;

public class ThreadTest {

    public static void main(String[] args)  {
        ThreadManager threadManager = new ThreadManager();
        Thread1 thread1 = new Thread1(threadManager);
        Thread2 thread2 = new Thread2(threadManager,thread1);
        Thread3 thread3 = new Thread3(threadManager);

        thread1.start();
        thread2.start();
        //thread3.start();
    }

}
class ThreadManager{
}

//如果A线程调用了LockSupport.park()，没有别的线程调用LockSupport.unpark(A)，那么A没有办法恢复运行。
class Thread1 extends Thread{
    ThreadManager threadManager;
    public Thread1(ThreadManager threadManager) {
        this.threadManager = threadManager;
    }

    /**
     * 线程执行完毕，执行完run方法正常返回，或者抛出了运行时异常而结束，线程都会停留在这个状态。
     * 这个时候线程只剩下Thread对象了，没有什么用了。
     */
    @Override
    public void run() {
        try {
            synchronized (threadManager){
                //在线线程中调用wait方法的时候 要用synchronized锁住对象，确保代码段不会被多个线程调用
                //wait会释放锁
                threadManager.wait(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //LockSupport.park();
        System.out.println("Thread2我在等待wait，现在我被唤醒");
        super.run();
    }
}

class Thread2 extends Thread{
    ThreadManager threadManager;
    Thread1 thread1;
    public Thread2(ThreadManager threadManager, Thread1 thread1) {
        this.threadManager = threadManager;
        this.thread1 = thread1;
    }

    @Override
    public void run() {
        //休息3s唤醒
        try {
            sleep(3000);
            System.out.println("3s完毕，开始唤醒");
            //LockSupport.unpark(thread1);
            synchronized (threadManager){
                //调用唤醒方法后需要,当前方法执行完毕释放锁之后，对应的wait才会继续获取锁
                threadManager.notifyAll();
                System.out.println("开始唤醒完毕");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.run();
    }
}

class Thread3 extends Thread{
    ThreadManager threadManager;
    public Thread3(ThreadManager threadManager) {
        this.threadManager = threadManager;
    }

    @Override
    public void run() {
        try {
            synchronized (threadManager) {
                //在线线程中调用wait方法的时候 要用synchronized锁住对象，确保代码段不会被多个线程调用
                threadManager.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread3我在等待wait，现在我被唤醒");
        super.run();
    }
}