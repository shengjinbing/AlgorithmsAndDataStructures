package cn.modesty.suanfa.reflex;

import java.util.Stack;

public class TestClass {
    private String name;
    private static int age = 11;

    public String getName() {
        return name;
    }

    public TestClass() {
        name = "baobao";
    }

    public TestClass(int a) {

    }

    public TestClass(int a, String b) {
        name = b;
    }

    private TestClass(int a, double c) {

    }

    private static void work() {
        System.out.println("我是私有静态方法");
    }

    private void doSomething(String id) {
        System.out.println("我是私有方法" + id);
    }
}
