package cn.modesty.suanfa.annotation;


public class Test {
    @TestAnnotation()
    public void test01(){
        System.out.println("自定义注解标记的公共方法");
    }

}
