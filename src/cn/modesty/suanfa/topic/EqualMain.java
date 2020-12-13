package cn.modesty.suanfa.topic;

/**
 * hashcode 与 equal 区别?
 * （1）关系操作符 ==
 * 若操作数的类型是基本数据类型，则该关系操作符判断的是左右两边操作数的值是否相等
 * 若操作数的类型是引用数据类型，则该关系操作符判断的是左右两边操作数的内存地址是否相同。也就是说，若此时返回 true, 则该操作符作用的一定是同一个对象
 * （2）equals（内部实现三个步骤）
 * 1.先 比较引用是否相同 (是否为同一对象)
 * 2.再 判断类型是否一致（是否为同一类型）
 * 3.最后 比较内容是否一致
 * 注：equal 的默认行为是比较引用(Object对象)，所以除非在自己的新类中覆盖了 equal() 方法，否则不可能表现出我们希望的行为
 * （3）hashCode
 * hashcode 是系统用来快速检索对象而使用（一般在需要用哈希算法的数据结构中才有用，比如 HashSet, HashMap 和 Hashtable）
 * 重写 equals 方法和 hashcode 方法时，equals 方法中用到的成员变量也必定会在 hashcode 方法中用到，只不过前者作为比较项，
 * 后者作为生成摘要的信息项，本质上所用到的数据是一样的，从而保证二者的一致性
 * （4）equals 与 hashCode 关系
 * 1.如果两个对象 equals，那么它们的 hashCode 必然相等
 * 2.但是 hashCode 相等，equals 不一定相等
 *
 *
 * 作者：叛逆的青春不回头
 * 链接：https://www.jianshu.com/p/9bfb74c50f6c
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class EqualMain {
    public static void main(String[] args) {
        String a = new String("a");
        String b = new String("b");
        a.equals(b);
    }
}
