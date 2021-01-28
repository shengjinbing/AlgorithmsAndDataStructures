package cn.modesty.suanfa.thread.finaltest;

/**
 * 方法内部的匿名内部类使用方法的局部变量时，为何要使用final修饰？
 * 1.在JDK8之前，如果我们在匿名内部类中需要访问局部变量，那么这个局部变量必须用final修饰符修饰
 * 2.回到正题，为什么需要用final保护数据的一致性呢？
 * 因为将数据拷贝完成后，如果不用final修饰，则原先的局部变量可以发生变化。这里到了问题的核心了，如果局部变量发生
 * 变化后，匿名内部类是不知道的（因为他只是拷贝了局不变量的值，并不是直接使用的局部变量）。这里举个栗子：原先局部
 * 变量指向的是对象A，在创建匿名内部类后，匿名内部类中的成员变量也指向A对象。但过了一段时间局部变量的值指向另外一
 * 个B对象，但此时匿名内部类中还是指向原先的A对象。那么程序再接着运行下去，可能就会导致程序运行的结果与预期不同。
 */
public class Main {
    public static void main(String[] args) {
        String str="haha";
        new Thread() {
            @Override
            public void run() {
                System.out.println(str);
            }
        }.start();

    }
}
