package cn.modesty.suanfa.thread;

import java.util.concurrent.*;

/**
 * 任务提交给线程池之后的处理策略
 *
 * 如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建执行这个任务；
 * 如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中
 * 若添加成功，则该任务会等待空闲线程将其取出去执行；
 * 若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
 * 如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
 * 如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，
 * 直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。
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
        mThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,//核心池的大小
                keepAliveTime,//线程池最大线程数
                unit,//保持时间
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
