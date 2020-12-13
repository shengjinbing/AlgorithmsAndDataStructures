package cn.modesty.suanfa.thread;

/**
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
