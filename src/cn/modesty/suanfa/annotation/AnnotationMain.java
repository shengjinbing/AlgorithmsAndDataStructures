package cn.modesty.suanfa.annotation;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationMain {
    // 状态值
    public static final int STATUS_OPEN = 1;
    public static final int STATUS_CLOSE = 2;

    public static void main(String[] args) {
        AnnotationMain main = new AnnotationMain();
        main.setStatus(3);
    }
    /**
     * 定义方法并使用@Status限定参数的取值
     * @param status
     */
    public void setStatus(@SourceAnnotation int status) {
    }

    private void annotation(){
        //获取该类的class对象
        try {
            Class<?> clazz = Class.forName("cn.modesty.suanfa.annotation.Test");
            //获取该类的所有方法
            Method[] methods = clazz.getDeclaredMethods();
            Test o =(Test) clazz.newInstance();//创建该类的对象
            //遍历方法,判断方法是否带指定的注解,
            for (Method method : methods) {
                method.setAccessible(true);//设置方法为可执行的
                if (method.isAnnotationPresent(TestAnnotation.class)) {
                    //带注解的调用运行,并解析注解的属性值输出打印
                    method.invoke(o);
                    TestAnnotation mt = method.getAnnotation(TestAnnotation.class);
                    String name = mt.name();
                    System.out.println("注解的属性值为"+name);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}


