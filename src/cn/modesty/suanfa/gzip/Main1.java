package cn.modesty.suanfa.gzip;

import sun.print.PrintJob2D;

public class Main1 {

    static  boolean shutdown = false;
    public static void main(String[] args) {
        doWork();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shutDown();

    }

    public static void shutDown(){
        System.out.println("77777");
        shutdown = true;
    }

    public static void doWork(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!shutdown){
                    System.out.println("66666");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
