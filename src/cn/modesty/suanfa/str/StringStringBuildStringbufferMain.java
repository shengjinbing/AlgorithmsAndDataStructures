package cn.modesty.suanfa.str;
/*学习了String类和StringBuffer类，现在从三分面来总结一下String、StringBuffer、StringBudder三者的区别：
        是否可变：
        String：底层利用字符数组保存字符串常量，是不可变的，因为String类的原码中有：private final char value[];因为有
        final修饰，所以String类的对象是不可改变的。所以每次修String对象的值时，实际上是生成了一个新的对象，而指针指向了
        新的String对象；
        StringBuffer和StringBudder底层是利用字符数组保存字符串变量的，在jdk1.7中它们都继承了AbstractStringBuilder类，
        而在AbstractStringBuilder类中有char[] value;，所以这两者对象是可变的；

        执行速度：一般情况StringBuilder > StringBuffer > String

        String：因为String对象是不可变的，所以每次修String对象的时候，实际上是生成了一个新的对象，而指针指向了新的String
        对象；所以经常改变内容的字符串最好不要用 String ，因为每次生成对象都会对系统性能产生影响，特别当内存中无引用对象
        多了以后， JVM 的 GC 就会开始工作，那速度是一定会相当慢的。
        StringBuffer：而如果是使用 StringBuffer 类则结果就不一样了，每次结果都会对 StringBuffer 对象本身进行操作，而不是
        生成新的对象，再改变对象引用。

        但是有特殊情况： String str = “abc” + “def”;StringBuffer Sb = new StringBuilder(“abc”).append(“ def”);
        这时这种情况下：String的速度就要比StringBuffer的速度要快，因为这时jvm认为String str = “abc” + “def”;其实是：
        String str = “abcdef”;所以速度当然很快，这里说的是当String保存的字符串中有其他String的字符串时，速度就会变慢。

        是否线程安全：
        String：　String中的对象是不可变的，也就可以理解为常量，显然线程安全。
        StringBuffer：是线程安全的，因为对方法加了同步锁或者对调用的方法加了同步锁*/
public class StringStringBuildStringbufferMain {

    public static void main(String[] args) {

        testString();
        //初始化容量为str+16字符串数组大小
        new StringBuilder("!1232").append("dsadasdas");
        //synchronized实现线程安全
        new StringBuffer("dasdda").append("dasdsadasdas");
    }

    public static void testString() {
        /*1.直接定义字符串变量的时候赋值，如果表达式右边只有字符串常量，那么就是把变量存放在常量池里面。
　　　　2.new出来的字符串是存放在堆里面。
　　　　3.对字符串进行拼接操作，也就是做"+"运算的时候，分2中情况：
　　　　　　i.表达式右边是纯字符串常量，那么存放在常量池里面。
　　　　　　ii.表达式右边如果存在字符串引用，也就是字符串对象的句柄，那么就存放在堆里面。*/
        String s1 = "Programming";
        //堆区
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        //堆区
        String s6 = s3 + s4;
        System.out.println(s1 == s2);//false
        System.out.println(s1 == s5);//true
        System.out.println(s1 == s6);//false
        //intern()优化性能从常量池里面取出来
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());


        String str1 = "aaa";
        String str2 = "bbb";
        String str3 = "aaabbb";
        String str4 = str1 + str2;
        String str5 = "aaa" + "bbb";
        System.out.println(str3 == str4); // false
        System.out.println(str3 == str4.intern()); // true
        System.out.println(str3 == str5);// true

        /*结果：str1、str2、str3、str5都是存在于常量池，str4由于表达式右半边有引用类型，所以str4存在于堆内存，
        而str5表达式右边没有引用类型，是纯字符串常量，就存放在了常量池里面。其实Integer这种包装类型的-128 ~ +127
        也是存放在常量池里面，比如Integer i1 = 10;Integer i2 = 10; i1 == i2结果是true，估计也是为了性能优化。*/

    }
}
