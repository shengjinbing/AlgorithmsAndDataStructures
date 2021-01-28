package cn.modesty.suanfa.thread;

import java.util.concurrent.*;

/**
 * 1.线程的几种状态？
 * 2.任务提交给线程池之后的处理策略
 * 如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建执行这个任务；
 * 如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中
 * 若添加成功，则该任务会等待空闲线程将其取出去执行；
 * 若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
 * 如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
 * 如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，
 * 直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。
 *
 * 线程池
 * 1.线程池中的任务可以实现按照优先级执行么，如何实现？（优先级队列）
 * 2.线程池的设计用到了那种设计思想？（生产者消费者模型）
 * 3.你是如何配置线程池的？核心线程数你一般是怎么配置的？
 *   程序密集型任务尽量配置少的线程数量，入Ncpu+1的线程，Ncpu是cpu核心数。IO密集型，由于一条线程会堵塞不是一直在执行任务，
 * 所以尽量配置多的线程，提高cpu利用率，如2*Ncpu
 *
 * 锁。synchronized、volatile、Lock。锁的几种状态。CAS原理。
 * ①为什么会有线程安全？
 * ②Java中如何保证线程安全？
 * ③synchronized和Lock的使用、区别及底层实现；volatile的作用和使用方式；常见的原子类。
 * ④synchronized中的类锁和对象锁互斥么？
 */
public class CustomThreadPool {

    private TimeUnit                unit          = TimeUnit.SECONDS;
    /* ArrayBlockingQueue(有界队列)： FIFO 队列，规定大小的BlockingQueue，其构造函数必须带一个int参数来指明其大小

     LinkedBlockingQueue(无界队列)：FIFO 队列，大小不定的BlockingQueue，若其构造函数带一个规定大小的参数，生成的BlockingQueue有大小限制，
                                    若不带大小参数，所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定。

     PriorityBlockingQueue：优先级队列， 类似于LinkedBlockingQueue，但队列中元素非 FIFO, 依据对象的自然排序顺序或者是构造函数所带的Comparator决定的顺序

     SynchronousQueue(直接提交策略): 交替队列，队列中操作时必须是先放进去，接着取出来，交替着去处理元素的添加和移除*/
    private BlockingQueue<Runnable> workQueue     = new ArrayBlockingQueue(100);
    private ThreadFactory           threadFactory = Executors.defaultThreadFactory();


    /*当添加任务出错时的策略捕获器，如果出现错误，则直接抛出异常
    ThreadPoolExecutor.AbortPolicy

    当添加任务出错时的策略捕获器，如果出现错误，直接执行加入的任务
     ThreadPoolExecutor.CallerRunsPolicy

    当添加任务出错时的策略捕获器,如果出现错误,移除第一个任务,执行加入的任务
     ThreadPoolExecutor.DiscardOldestPolicy

    当添加任务出错时的策略捕获器，如果出现错误，不做处理
    ThreadPoolExecutor.DiscardPolicy*/
    private       RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
    private final ThreadPoolExecutor       mThreadPoolExecutor;

    public CustomThreadPool(int corePoolSize,
                            int maximumPoolSize,
                            long keepAliveTime) {
        mThreadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,//核心池的大小
                maximumPoolSize,//线程池最大线程数
                keepAliveTime,//保活时间
                unit,//保活时间单位
                workQueue,//任务队列
                threadFactory,//线程工厂
                handler);// 捕获异常
    }

    /**
     * 判断线程池是否关闭
     */
    public void isShutdown() {
        mThreadPoolExecutor.isShutdown();
    }

    /**
     * 判断线程池中任务是否执行完成
     */
    public void isTerminated() {
        mThreadPoolExecutor.isShutdown();
    }

    /**
     * 调用后不再接收新任务，如果里面有任务，就执行完
     */
    public void shutdown() {
        mThreadPoolExecutor.shutdown();
    }

    /**
     * 调用后不再接受新任务，如果有等待任务，移出队列；有正在执行的，尝试停止之
     */
    public void shutdownNow() {
        mThreadPoolExecutor.shutdownNow();
    }

    /**
     * 提交执行任务
     *
     * @param task
     */
    public void submit(Runnable task) {
        mThreadPoolExecutor.submit(task);
    }

    /**
     * 执行任务
     *
     * @param command
     */
    public void execute(Runnable command) {
        mThreadPoolExecutor.execute(command);
    }
}
/**
 * Java线程中的七种状态
 * 1.新建状态(New)
 * 当用new操作符创建一个线程后， 例如new Thread(r)，此时线程处在新建状态。 当一个线程处于新建状态时，线程中的任务代码还没开始运行。
 * 2.就绪状态(Runnable)
 * 也被称为“可执行状态”。一个新创建的线程并不自动开始运行，要执行线程，必须调用线程的start()方法。当调用了线程对象的start()方法即启动了线程，此时线程就处于就绪状态。
 * 处于就绪状态的线程并不一定立即运行run()方法，线程还必须同其他就绪线程竞争CPU，只有获得CPU使用权才可以运行线程。比如在单核心CPU的计算机系统中，不可能同时运行多个线程，一个时刻只能有一个线程处于运行状态。对与多个处于就绪状态的线程是由Java运行时系统的线程调度程序(thread scheduler)来调度执行。
 * 除了调用start()方法后让线程变成就绪状态，一个线程阻塞状态结束后也可以变成就绪状态，或者从运行状态变化到就绪状态。
 * 3.运行状态(Running)
 * 线程获取到CPU使用权进行执行。需要注意的是，线程只能从就绪状态进入到运行状态。真正开始执行run()方法的内容。
 * 4.阻塞状态(Blocked)
 * 线程在获取锁失败时(因为锁被其它线程抢占)，它会被加入锁的同步阻塞队列，然后线程进入阻塞状态(Blocked)。处于阻塞状态(Blocked)的线程放弃CPU使用权，暂时停止运行。待其它线程释放锁之后，阻塞状态(Blocked)的线程将在次参与锁的竞争，如果竞争锁成功，线程将进入就绪状态(Runnable) 。
 * 5.等待状态(WAITING)
 * 或者叫条件等待状态，当线程的运行条件不满足时，通过锁的条件等待机制(调用锁对象的wait()或显示锁条件对象的await()方法)让线程进入等待状态(WAITING)。处于等待状态的线程将不会被cpu执行，除非线程的运行条件得到满足后，其可被其他线程唤醒，进入阻塞状态(Blocked)。调用不带超时的Thread.join()方法也会进入等待状态。
 * 6.限时等待状态(TIMED_WAITING)
 * 限时等待是等待状态的一种特例，线程在等待时我们将设定等待超时时间，如超过了我们设定的等待时间，等待线程将自动唤醒进入阻塞状态(Blocked)或就绪状态(Runnable) 。在调用Thread.sleep()方法，带有超时设定的Object.wait()方法，带有超时设定的Thread.join()方法等，线程会进入限时等待状态(TIMED_WAITING)。
 * 7.死亡状态(TERMINATED)
 * 线程执行完了或者因异常退出了run()方法，该线程结束生命周期。
 */