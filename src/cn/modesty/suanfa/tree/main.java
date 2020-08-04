package cn.modesty.suanfa.tree;

/**
 * 1.Exception和Error有什么区别?
 * 2.NoClassDefFoundError和ClassNOtFoundException区别？
 *
 * Throw：
 * 作用在方法内，表示抛出具体异常，由方法体内的语句处理。
 * 具体向外抛出的动作，所以它抛出的是一个异常实体类。若执行了Throw一定是抛出了某种异常。
 * Throws：
 * 作用在方法的声明上，表示如果抛出异常，则由该方法的调用者来进行异常处理。
 * 主要的声明这个方法会抛出会抛出某种类型的异常，让它的使用者知道捕获异常的类型。
 * 出现异常是一种可能性，但不一定会发生异常。
 */
public class main {
    public static void main(String[] args) {

    }
}
