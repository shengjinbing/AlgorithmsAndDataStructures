package cn.modesty.suanfa.classlaoders;

import java.io.*;

/**
 * 类加载的过程：
 * （1）加载 - 将字节码数据从不同的数据源读取到 JVM 中，并映射为 JVM 认可的数据结构 (Class 对象)
 * 由于类加载器的代理机制，启动类加载过程的类加载器和真正完成类加载工作的类加载器，有可能不同；
 * 启动类的加载过程通过调用loadClass()来实现，称为初始加载器 (initiating loader)；而完成类的加载工作通过调
 * 用defineClass()来实现，称为类的定义加载器 (defining loader)。在 Java 虚拟机判断两个类是否相同的时候，
 * 使用的是类的定义加载器；loadClass() 抛出的是 java.lang.ClassNotFoundException 异常，而 defineClass()
 * 抛出的是 java.lang.NoClassDefFoundError 异常；类加载器在成功加载某个类之后，会把得到的 java.lang.Class
 * 类的实例缓存起来。下次再请求加载该类的时候，类加载器会直接使用缓存的类的实例，而不会尝试再次加载 (即 loadClass()
 * 不会被重复调用)。
 * (2）链接 - 将原始的类定义信息平滑地转化入 JVM 运行的过程中
 * 验证：核验字节信息是符合 Java 虚拟机规范；
 * 准备：创建类或接口中的静态变量并初始化，侧重分配所需要的内存空间（与初始化阶段区分开）；
 * 解析：替换常量池中的符号引用为直接引用，类、接口、方法和字段等各个方面的解析等
 *（3）初始化 - 真正执行类初始化的代码逻辑，包括静态字段赋值的动作，以及类中静态初始化块内的逻辑。编译器在编译阶段就会把
 * 这部分逻辑整理好，父类型的初始化逻辑优先于当前类型的逻辑
 *
 * Java GC 是如何判断对象是可以被回收的？
 * Java 垃圾收集的原理：
 * 自动垃圾收集的前提是清楚哪些内存可以被释放，主要有两个方面，最主要部分就是对象实例，存储在堆上的；另一个是方法区中的
 * 元数据等信息，例如类型不再使用，卸载该 Java 类比较合理；
 * 对象实例收集主要是两种基本算法，引用计数和可达性分析，Java 选择的可达性分析。JVM 会把虚拟机栈和本地方法栈中正在引用的
 * 对象、静态属性引用的对象和常量**，作为 GC Roots。
 *
 */
public class FileSystemClassLoader extends ClassLoader {

    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }
}
