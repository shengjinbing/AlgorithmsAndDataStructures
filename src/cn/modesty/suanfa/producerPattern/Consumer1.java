package cn.modesty.suanfa.producerPattern;

import java.util.List;

public class Consumer1 implements Runnable {
    private List<PCData1> queue;

    public Consumer1(List<PCData1> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                PCData1 data = null;
                synchronized (queue) {
                    if (queue.size() == 0) {
                        queue.wait();
                        queue.notifyAll();
                    }
                    if(queue.size() != 0){
                        //需要进行判断。否者可能会出现被上一个消费机消费完了
                        data = queue.remove(0);
                    }

                }
                System.out.println(
                        Thread.currentThread().getId() + " 消费了:" + data.get() + " result:" + (data.get() * data.get()));
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
