package cn.modesty.suanfa.thread;

/**
 * Created by lixiang
 * on 2018/12/10
 */
public class ThreadRunnable implements Runnable {
    private Integer index;
    public  ThreadRunnable(Integer index)
    {
        this.index=index;
    }
    @Override
    public void run() {
        /***
         * 业务......省略
         */
        System.out.println("我的线程标识是："+this.toString());
       /* try {
            System.out.println("开始处理线程！！！");
            Thread.sleep(index*100);
            System.out.println("我的线程标识是："+this.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
