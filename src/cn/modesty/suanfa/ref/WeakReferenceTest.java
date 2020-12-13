package cn.modesty.suanfa.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    public static void main(String[] args) {

        A a = new A();

        ReferenceQueue<A> rq = new ReferenceQueue<A>();
        WeakReference<A> wrA = new WeakReference<A>(a, rq);

        System.out.println("引用对象:" + wrA);

        a = null;

        if (wrA.get() == null) {
            System.out.println("a对象进入垃圾回收流程");
        } else {
            System.out.println("a对象尚未进入垃圾回收流程" + wrA.get());
        }

        // 通知系统进行垃圾回收
        System.gc();

        try {
            Thread.currentThread().sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (wrA.get() == null) {
            System.out.println("a对象进入垃圾回收流程");
        } else {
            System.out.println("a对象尚未进入垃圾回收流程" + wrA.get());
        }

        System.out.println("引用对象:" + rq.poll());
    }

    static class A {

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("in A finalize");
        }

    }
}


