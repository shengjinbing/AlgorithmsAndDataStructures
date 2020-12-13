package cn.modesty.suanfa.reflex;

import cn.modesty.suanfa.producerPattern.Main;

import java.lang.reflect.*;

/**
 * 什么是反射：
 * 反射是Java的特征之一，是一种间接操作目标对象的机制，核心是JVM在运行的时候才动态加载类，并且对于任意一个类,
 * 都能够知道这个类的所有属性和方法，调用方法/访问属性，不需要提前在编译期知道运行的对象是谁，他允许运行中的
 * Java程序获取类的信息，并且可以操作类或对象内部属性。程序中对象的类型一般都是在编译期就确定下来的，而当我们
 * 的程序在运行时，可能需要动态的加载一些类，这些类因为之前用不到，所以没有加载到jvm，这时，使用Java反射机制
 * 可以在运行期动态的创建对象并调用其属性，它是在运行时根据需要才加载。
 *
 *
 * 1、优点：使用反射，我们就可以在运行时获得类的各种内容，进行反编译，对于Java这种先编译再运行的语言，能够让我们
 *        很方便的创建灵活的代码，这些代码可以在运行时装配，无需在组件之间进行源代码的链接，更加容易实现面向对象。
 * 2、缺点：（1）反射会消耗一定的系统资源，因此，如果不需要动态地创建一个对象，那么就不需要用反射；
 *        （2）反射调用方法时可以忽略权限检查，因此可能会破坏封装性而导致安全问题。
 */
public class ReflexMain {
    public static void main1(String[] args) {
       //第一种方式获取Class对象
        Student stu1 = new Student();//这一new 产生一个Student对象，一个Class对象。
        Class stuClass = stu1.getClass();//获取Class对象,在运行时加载
        System.out.println(stuClass.getName());

        //第二种方式获取Class对象,在编译器加载
        Class stuClass2 = Student.class;
        System.out.println(stuClass == stuClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象，最常用的就是第三种,在运行时加载
        try {
            //Class.forName的作用是要求JVM查找并加载指定的类，也就是说JVM会执行该类的静态代码段
            Class stuClass3 = Class.forName("cn.modesty.suanfa.reflex.Student");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Class aClass = null;
        Class amn = null;
        try {
             aClass = Class.forName("cn.modesty.suanfa.reflex.TestClass");
             amn = Class.forName("cn.modesty.suanfa.reflex.AMN");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ReflexMain main = new ReflexMain();
        //main.testConstructors(aClass);
        //main.testMethod(aClass);
        //main.testFiled(aClass);
        main.testGenericity(amn);
    }

    //泛型
    private void testGenericity(Class aClass){
        try {
            Class<?> singleton = Class.forName("cn.modesty.suanfa.reflex.Singleton");
            Field mInstanceFile = singleton.getDeclaredField("mInstance");
            mInstanceFile.setAccessible(true);

            Field gDefaultFile = aClass.getDeclaredField("gDefault");
            gDefaultFile.setAccessible(true);
            Object gDefault = gDefaultFile.get(null);

            Object rawB2Object = mInstanceFile.get(gDefault);

            Class<?> classB2Interface = Class.forName("cn.modesty.suanfa.reflex.AMN$ClassB2Interface");
            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{classB2Interface},
                    new AMN.classB2Mock((AMN.ClassB2Interface) rawB2Object));
            mInstanceFile.set(gDefault,proxy);

            AMN.ClassB2Interface aDefault = AMN.getDefault();
            aDefault.testDosomething();

        } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //变量
    private void testFiled(Class aClass) {
        try {
            //普通方法
            Field field = aClass.getDeclaredField("name");
            field.setAccessible(true);
            Object o = field.get(aClass.newInstance());//静态字段这里传null就行
            System.out.println(o);

            //静态方法,静态变量一次修改终身受用
            Field staticField = aClass.getDeclaredField("age");
            staticField.setAccessible(true);
            System.out.println(staticField.get(null));
            staticField.set(null,13);
            System.out.println(staticField.get(null));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    //方法
    private void testMethod(Class aClass){
        try {
            //普通方法
            Method method = aClass.getDeclaredMethod("doSomething",
                    new Class[]{String.class});
            method.setAccessible(true);
            method.invoke(aClass.newInstance(),new Object[]{"177"});

            //静态方法
            Method staticMethod = aClass.getDeclaredMethod("work");
            staticMethod.setAccessible(true);
            staticMethod.invoke(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    //构造函数
    public void testConstructors(Class aClass ){
        Constructor[] declaredConstructors = aClass.getDeclaredConstructors();//所以构造参数
        for (Constructor declaredConstructor : declaredConstructors) {
            int modifiers = declaredConstructor.getModifiers();//修饰符
            System.out.println(Modifier.toString(modifiers));
            Class[] parameterTypes = declaredConstructor.getParameterTypes();//参数类型
            for (Class parameterType : parameterTypes) {
                System.out.println(parameterType.getName());
            }
        }

        //获取对应参数的构造函数
        Class[] p2 = new Class[]{int.class,String.class};
        try {
            Constructor constructor = aClass.getDeclaredConstructor(p2);
            Class[] parameterTypes = constructor.getParameterTypes();
            TestClass testClass = (TestClass) constructor.newInstance(1,"hahah");
            System.out.println(testClass.getName());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
