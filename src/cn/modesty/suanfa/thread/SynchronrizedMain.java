package cn.modesty.suanfa.thread;

/**
 *https://juejin.cn/post/6844903482114195463
 *锁机制有如下两种特性：
 * 1.互斥性：即在同一时间只允许一个线程持有某个对象锁，通过这种特性来实现多线程中的协调机制，这样在同一时间只有一个线程对
 * 需同步的代码块(复合操作)进行访问。互斥性我们也往往称为操作的原子性。
 * 2.可见性：必须确保在锁被释放之前，对共享变量所做的修改，对于随后获得该锁的另一个线程是可见的（即在获得锁时应获得最新共
 * 享变量的值），否则另一个线程可能是在本地缓存的某个副本上继续操作从而引起不一致。
 * 二、对象锁和类锁
 * 1. 对象锁
 * 在 Java 中，每个对象都会有一个 monitor 对象，这个对象其实就是 Java 对象的锁，通常会被称为“内置锁”或“对象锁”。类的对象可以有多个，所以每个对
 * 象有其独立的对象锁，互不干扰。
 * 2. 类锁
 * 在 Java 中，针对每个类也有一个锁，可以称为“类锁”，类锁实际上是通过对象锁实现的，即类的 Class 对象锁。每个类只有
 * 一个 Class 对象，所以每个类只有一个类锁
 *
 * 根据获取的锁分类
 * 1.获取对象锁
 * synchronized(this|object) {}
 * 修饰非静态方法
 * 2.获取类锁
 * synchronized(类.class) {}
 * 修饰静态方法
 *
 */
public class SynchronrizedMain {
    public  static void main(String[] args) {
        //A B方法实质是一样的

        //死锁的例子
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(new Runnable(){

            @Override
            public void run() {
                synchronized (o1){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o2){
                        System.out.println("222");
                    }
                }
            }
        }).start();
        new Thread(new Runnable(){

            @Override
            public void run() {
                synchronized (o2){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o1){
                        System.out.println("1111");
                    }
                }
            }
        }).start();
    }

    private synchronized void A(){

    }

    private void B(){
        synchronized (this){

        }
    }
}
