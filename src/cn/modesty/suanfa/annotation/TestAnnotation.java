package cn.modesty.suanfa.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解是什么意思呢？
 * 元注解是可以注解到注解上的注解，或者说元注解是一种基本注解，但是它能够应用到其它的注解上面。
 * 元标签有 @Retention、@Documented、@Target、@Inherited、@Repeatable 5 种。
 *
 * @Retention
 * RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
 * 源码注解(RetentionPolicy.SOURCE)的生命周期只存在Java源文件这一阶段，是3种生命周期中最短的注解。
 * 当在Java源程序上加了一个注解，这个Java源程序要由javac去编译，javac把java源文件编译成.class文件，
 * 在编译成class时会把Java源程序上的源码注解给去掉。需要注意的是，在编译器处理期间源码注解还存在，即注解
 * 处理器Processor 也能处理源码注解，编译器处理完之后就没有该注解信息了。
 * RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
 * RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
 * 这3个生命周期分别对应于：Java源文件(.java文件) ---> .class文件 ---> 内存中的字节码。
 * 那怎么来选择合适的注解生命周期呢？
 * 首先要明确生命周期长度 SOURCE < CLASS < RUNTIME ，所以前者能作用的地方后者一定也能作用。
 * 一般如果需要在运行时去动态获取注解信息，那只能用 RUNTIME 注解，比如@Deprecated使用RUNTIME注解
 * 如果要在编译时进行一些预处理操作，比如生成一些辅助代码（如 ButterKnife），就用 CLASS注解；
 * 如果只是做一些检查性的操作，比如 @Override 和 @SuppressWarnings，使用SOURCE 注解。
 *
 * 注解@Override用在方法上，当我们想重写一个方法时，在方法上加@Override，当我们方法的名字出错时，编译器就会报错
 * 注解@Deprecated，用来表示某个类或属性或方法已经过时，不想别人再用时，在属性和方法上用@Deprecated修饰
 * 注解@SuppressWarnings用来压制程序中出来的警告，比如在没有用泛型或是方法已经过时的时候
 *
 *
 * @Documented
 * 顾名思义，这个元注解肯定是和文档有关。它的作用是能够将注解中的元素包含到 Javadoc 中去。
 *
 * @Target
 * Target 是目标的意思，@Target 指定了注解运用的地方。
 * 你可以这样理解，当一个注解被 @Target 注解时，这个注解就被限定了运用的场景。
 * 类比到标签，原本标签是你想张贴到哪个地方就到哪个地方，但是因为 @Target 的存在，它张贴的地方就非常具体了，比如只能张贴到方法上、类上、方法参数上等等。@Target 有下面的取值
 * ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 * ElementType.CONSTRUCTOR 可以给构造方法进行注解
 * ElementType.FIELD 可以给属性进行注解
 * ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 * ElementType.METHOD 可以给方法进行注解
 * ElementType.PACKAGE 可以给一个包进行注解
 * ElementType.PARAMETER 可以给一个方法内的参数进行注解
 * ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
 *
 * @Inherited
 * Inherited 是继承的意思，但是它并不是说注解本身可以继承，而是说如果一个超类被 @Inherited 注解过的注解进行注解的话，
 * 那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。
 *
 * @Repeatable
 * Repeatable 自然是可重复的意思。@Repeatable 是 Java 1.8 才加进来的，所以算是一个新的特性。
 * 什么样的注解会多次应用呢？通常是注解的值可以同时取多个。
 * 举个例子，一个人他既是程序员又是产品经理,同时他还是个画家。
 * @interface Persons {
 *     Person[]  value();
 * }
 * @Repeatable(Persons.class)
 * @interface Person{
 *     String role default "";
 * }
 * @Person(role="artist")
 * @Person(role="coder")
 * @Person(role="PM")
 * public class SuperMan{
 * }
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    public int id() default -1;
    public String name() default "你是谁";
    public String msg() default "Hi";
}
