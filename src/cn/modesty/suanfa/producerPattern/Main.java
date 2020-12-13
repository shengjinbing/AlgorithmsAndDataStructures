package cn.modesty.suanfa.producerPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition empty = lock.newCondition();
    public static Condition full = lock.newCondition();
    public static void main(String[] args) throws InterruptedException {
        /***********************Lock实现************************************/
       /* List<PCData2> queue = new ArrayList<PCData2>();
        int length = 10;
        Producter2 p1 = new Producter2(queue, length);
        Producter2 p2 = new Producter2(queue, length);
        Producter2 p3 = new Producter2(queue, length);
        Consumer2 c1 = new Consumer2(queue);
        Consumer2 c2 = new Consumer2(queue);
        Consumer2 c3 = new Consumer2(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
*/
        /***********************阻塞队列实现************************************/
        BlockingQueue<PCData> queue = new LinkedBlockingQueue<>(10);
        Producer p1 = new Producer(queue);
        //Producer p2 = new Producer(queue);
        //Producer p3 = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        //Consumer c2 = new Consumer(queue);
        //Consumer c3 = new Consumer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        //service.execute(p2);
        //service.execute(p3);
        service.execute(c1);
        //service.execute(c2);
        //service.execute(c3);
        Thread.sleep(10 * 1000);

        p1.stop();
        //p2.stop();
        //p3.stop();

        Thread.sleep(3000);

        service.shutdown();

        /***********************synchronized实现************************************/
       /* List<PCData1> queue = new ArrayList();
        int length = 10;
        Producer1 p1 = new Producer1(queue,length);
        Producer1 p2 = new Producer1(queue,length);
        Producer1 p3 = new Producer1(queue,length);
        Consumer1 c1 = new Consumer1(queue);
        Consumer1 c2 = new Consumer1(queue);
        Consumer1 c3 = new Consumer1(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);*/


    }
}
