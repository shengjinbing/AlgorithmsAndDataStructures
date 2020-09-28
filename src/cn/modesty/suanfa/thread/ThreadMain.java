package cn.modesty.suanfa.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 五种线程池的适应场景
 *
 * newCachedThreadPool：用来创建一个可以无限扩大的线程池，适用于服务器负载较轻，执行很多短期异步任务。
 * newFixedThreadPool：创建一个固定大小的线程池，因为采用无界的阻塞队列，所以实际线程数量永远不会变化，
 *                     适用于可以预测线程数量的业务中，或者服务器负载较重，对当前线程数量进行限制。
 * newSingleThreadExecutor：创建一个单线程的线程池，适用于需要保证顺序执行各个任务，并且在任意时间点，
 *                          不会有多个线程是活动的场景。
 * newScheduledThreadPool：可以延时启动，定时启动的线程池，适用于需要多个后台线程执行周期任务的场景。
 * newWorkStealingPool：创建一个拥有多个任务队列的线程池，可以减少连接数，创建当前可用cpu数量的线程来并行执行，
 *                      适用于大耗时的操作，可以并行来执行
 *
 */
public class ThreadMain {
    public static void main(String[] args) {
        startNewCachedThreadPool();
        //newFixedThreadPool();
        //newSingleThreadExecutor();
        //newScheduledThreadPool();
        //newWorkStealingPool();
    }

    /**
     * 重用之前的线程
     * 适合执行许多短期异步任务的程序。
     * 调用 execute() 将重用以前构造的线程
     * 如果没有可用的线程，则创建一个新线程并添加到池中
     * 默认为60s未使用就被终止和移除
     * 长期闲置的池将会不消耗任何资源
     */
    public static void startNewCachedThreadPool() {
        //使用同步队列 new SynchronousQueue<Runnable>()
        //可以有无限大的线程数进来（线程地址不一样）
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 12; i++) {
            service.execute(new ThreadRunnable(i));
        }
    }

    /**
     * 创建重用固定数量线程的线程池，不能随时新建线程
     * 当所有线程都处于活动状态时，如果提交了其他任务，
     * 他们将在队列中等待一个线程可用
     * 线程会一直存在，直到调用shutdown
     */
    public static void newFixedThreadPool() {
        //使用同步队列 new LinkedBlockingQueue<Runnable>()
        //每次只有两个线程在处理，当第一个线程执行完毕后，新的线程进来开始处理（线程地址不一样）
        //参数为最大线程数和核心线程数
        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 8; i++) {
            service.execute(new ThreadRunnable(i));
        }
    }

    /**
     * 在任何情况下都不会有超过一个任务处于活动状态
     * 与newFixedThreadPool(1)不同是不能重新配置加入线程，使用FinalizableDelegatedExecutorService进行包装
     * 能保证执行顺序，先提交的先执行。
     * 当线程执行中出现异常，去创建一个新的线程替换之
     */
    public static void newSingleThreadExecutor() {
        //使用同步队列 new LinkedBlockingQueue<Runnable>()
        //只存一个线程
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 8; i++) {
            service.execute(new ThreadRunnable(i));
        }
    }

    /**
     * 设定延迟时间，定期执行
     * 空闲线程会进行保留
     */
    public static void newScheduledThreadPool() {
        //延迟3秒后执行，跟newFixedThreadPool基本相同，可以用来执行定时任务
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 8; i++) {
            service.schedule(new ThreadRunnable(i), 3, TimeUnit.SECONDS);
        }
    }

    public static void newWorkStealingPool() {
        //java1.8增加并行执行任务
        ExecutorService service = Executors.newWorkStealingPool(2);
        for (int i = 0; i < 8; i++) {
            service.execute(new ThreadRunnable(i));
        }
    }

}
