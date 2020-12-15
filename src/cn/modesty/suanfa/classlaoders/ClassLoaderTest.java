package cn.modesty.suanfa.classlaoders;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * https://developer.ibm.com/zh/articles/j-lo-classloader/
 *  * 一.类加载器的树状组织结构
 *  * Java 中的类加载器大致可以分成两类，一类是系统提供的，另外一类则是由 Java 应用开发人员编写的。
 *  * 1.系统提供的类加载器主要有下面三个：
 *  * 引导类加载器（bootstrap class loader）：它用来加载 Java 的核心库，是用原生代码来实现的，并不继承自 java.lang.ClassLoader 。
 *  * 扩展类加载器（extensions class loader）：它用来加载 Java 的扩展库。Java 虚拟机的实现会提供一个扩展库目录。该类加载器在此目录里面查找并加载 Java 类。
 *  * 系统类加载器（system class loader）：它根据 Java 应用的类路径（CLASSPATH）来加载 Java 类。一般来说，Java 应用的类都是由它来完成加载的。可以通过 ClassLoader.getSystemClassLoader() 来获取它。
 *
 *  除了引导类加载器之外，所有的类加载器都有一个父类加载器。通过 java.lang.ClassLoader 类介绍 中给出的
 *  getParent() 方法可以得到。对于系统提供的类加载器来说，系统类加载器的父类加载器是扩展类加载器，而扩展类
 *  加载器的父类加载器是引导类加载器；对于开发人员编写的类加载器来说，其父类加载器是加载此类加载器 Java 类的
 *  类加载器。因为类加载器 Java 类如同其它的 Java 类一样，也是要由类加载器来加载的。一般来说，开发人员编写
 *  的类加载器的父类加载器是系统类加载器。类加载器通过这种方式组织起来，形成树状结构。树的根节点就是引导类加载
 *  器。 图 1. 类加载器树状组织结构示意图 中给出了一个典型的类加载器树状组织结构示意图，其中的箭头指向的是父
 *  类加载器。
 *
 *  二.类加载器的代理模式
 *  1.类加载器在尝试自己去查找某个类的字节代码并定义它时，会先代理给其父类加载器，由父类加载器先去尝试加载这个类，
 *  依次类推。在介绍代理模式背后的动机之前，首先需要说明一下 Java 虚拟机是如何判定两个 Java 类是相同的。Java
 *  虚拟机不仅要看类的全名是否相同，还要看加载此类的类加载器是否一样。只有两者都相同的情况，才认为两个类是相同的。
 *  即便是同样的字节代码，被不同的类加载器加载之后所得到的类，也是不同的。比如一个 Java 类 com.example.Sample
 *  ，编译之后生成了字节代码文件 Sample.class 。两个不同的类加载器 ClassLoaderA 和 ClassLoaderB 分别读
 *  取了这个 Sample.class 文件，并定义出两个 java.lang.Class 类的实例来表示这个类。这两个实例是不相同的。
 *  对于 Java 虚拟机来说，它们是不同的类。试图对这两个类的对象进行相互赋值，会抛出运行时异常 ClassCastException 。
 *  下面通过示例来具体说明。 清单 3 中给出了 Java 类 com.example.Sample 。
 *  2.通过代理模式，对于 Java 核心库的类的加载工作由引导类加载器来统一完成，保证了 Java 应用所使用的都是同一个
 *  版本的 Java 核心库的类，是互相兼容的。
 *
 *  三.加载类的过程
 *  1.在前面介绍类加载器的代理模式的时候，提到过类加载器会首先代理给其它类加载器来尝试加载某个类。这就意味着真正完成类
 *  的加载工作的类加载器和启动这个加载过程的类加载器，有可能不是同一个。真正完成类的加载工作是通过调用 defineClass
 *  来实现的；而启动类的加载过程是通过调用 loadClass 来实现的。前者称为一个类的定义加载器（defining loader），
 *  后者称为初始加载器（initiating loader）。在 Java 虚拟机判断两个类是否相同的时候，使用的是类的定义加载器。
 *  也就是说，哪个类加载器启动类的加载过程并不重要，重要的是最终定义这个类的加载器。两种类加载器的关联之处在于：
 *  一个类的定义加载器是它引用的其它类的初始加载器。如类 com.example.Outer 引用了类 com.example.Inner ，
 *  则由类 com.example.Outer 的定义加载器负责启动类 com.example.Inner 的加载过程。
 *  2.方法 loadClass() 抛出的是 java.lang.ClassNotFoundException 异常；
 *    方法 defineClass() 抛出的是 java.lang.NoClassDefFoundError 异常。
 *  3.类加载器在成功加载某个类之后，会把得到的 java.lang.Class 类的实例缓存起来。下次再请求加载该类的时候，
 *   类加载器会直接使用缓存的类的实例，而不会尝试再次加载。也就是说，对于一个类加载器实例来说，相同全名的类只加载
 *   一次，即loadClass 方法不会被重复调用
 *
 *   四.线程上下文类加载器
 *   1.线程上下文类加载器（context class loader）是从 JDK 1.2 开始引入的。类 java.lang.Thread 中的方法
 *   getContextClassLoader() 和 setContextClassLoader(ClassLoader cl) 用来获取和设置线程的上下文类
 *   加载器。如果没有通过 setContextClassLoader(ClassLoader cl) 方法进行设置的话，线程将继承其父线程的上
 *   下文类加载器。Java 应用运行的初始线程的上下文类加载器是系统类加载器。在线程中运行的代码可以通过此类加载器来加
 *   载类和资源。
 *
 *   五。Class.forName
 *   1.Class.forName 是一个静态方法，同样可以用来加载类。该方法有两种形式：
 *   Class.forName(String name, boolean initialize, ClassLoader loader) 和
 *   Class.forName(String className) 。第一种形式的参数 name 表示的是类的全名；
 *   initialize 表示是否初始化类；loader 表示加载时使用的类加载器。第二种形式则相当于设置了参数
 *   initialize 的值为 true，loader 的值为当前类的类加载器。Class.forName 的一个很常见的用法是在加载数据库
 *   驱动的时候。如 Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance() 用来加
 *   载 Apache Derby 数据库的驱动。
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        //loadClss();
        //testGetClassLoader();
        testGetClassLoader();

    }

    private static void testGetClassLoader() {
        ClassLoader loader = classLoader;
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }

    public void testClassIdentity() {
        String classDataRootPath = "C:\\workspace\\Classloader\\classData";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "com.example.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadClss(){
        try {
            Object obj = classLoader.loadClass("cn.modesty.suanfa.classlaoders.ClassLoaderTest").newInstance();
            System.out.println(obj.getClass());
            System.out.println(obj instanceof ClassLoaderTest);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    static ClassLoader classLoader = new ClassLoader() {

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream is = getClass().getResourceAsStream(filename);
            if (is == null) {
                return super.loadClass(name);
            }
            try {
                byte[] bytes = new byte[is.available()];
                is.read(bytes);
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                throw new ClassNotFoundException();
            }
        }
    };
}
