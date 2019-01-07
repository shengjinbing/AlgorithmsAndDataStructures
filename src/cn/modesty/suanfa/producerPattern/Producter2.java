package cn.modesty.suanfa.producerPattern;

import java.util.List;
import java.util.Random;

public class Producter2 implements Runnable {
    private List<PCData2> queue;
    private int len;

    public Producter2(List<PCData2> queue, int len) {
        this.queue = queue;
        this.len = len;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                Random r = new Random();
                PCData2 data = new PCData2();
                data.setData(r.nextInt(500));
                Main.lock.lock();
                if (queue.size() >= len) {
                    Main.empty.signalAll();
                    Main.full.await();
                }
                Thread.sleep(1000);
                queue.add(data);
                Main.lock.unlock();
                System.out.println("生产者ID:" + Thread.currentThread().getId() + " 生产了:" + data.getData());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
