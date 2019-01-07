package cn.modesty.suanfa.producerPattern;

import java.util.List;

public class Consumer2 implements Runnable{
    private List<PCData2> queue;
    public Consumer2(List<PCData2> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                PCData2 data = null;
                Main.lock.lock();
                if (queue.size() == 0){
                    Main.full.signalAll();
                    Main.empty.await();
                }
                Thread.sleep(1000);
                data = queue.remove(0);
                Main.lock.unlock();
                System.out.println("消费者ID:"+Thread.currentThread().getId()+" 消费了:"+data.getData()+" result:"+(data.getData()*data.getData()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
