package cn.modesty.suanfa.thread;

/**
 *
 */
public class SynchronrizedMain {
    public  static void main(String[] args) {
        //A B方法实质是一样的
    }

    private synchronized void A(){

    }

    private void B(){
        synchronized (this){

        }
    }
}
