package cn.modesty.suanfa.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class SoftReferenceTest {
    public static void main(String[] args) {

        A a = new A();

        ReferenceQueue<A> rq = new ReferenceQueue<A>();
        SoftReference<A> srA = new SoftReference<A>(a, rq);

        a = null;

        if (srA.get() == null) {
            System.out.println("a对象进入垃圾回收流程");
        } else {
            System.out.println("a对象尚未进入垃圾回收流程" + srA.get());
        }

        // 通知系统进行垃圾回收
        System.gc();

        try {
            Thread.currentThread().sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (srA.get() == null) {
            System.out.println("a对象进入垃圾回收流程");
        } else {
            System.out.println("a对象尚未进入垃圾回收流程" + srA.get());
        }

        System.out.println("引用对象:" + rq.poll());
    }
}

class A {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("in A finalize");
    }

}


