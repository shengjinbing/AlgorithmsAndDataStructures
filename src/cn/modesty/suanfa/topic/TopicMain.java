package cn.modesty.suanfa.topic;

import cn.modesty.suanfa.collection.SetMain;

public class TopicMain {
    public static void main(String[] args) {
        A a = new A();
        a.test();
        a.test1();
    }

    public static class A {

        /**
         * 方法得到的是所有参数值的一个拷贝，即方法不能修改传递给它的任何参数变量的内容。基本类型参数传递的是参数副本，
         * 对象类型参数传递的是对象地址的副本；
         */
        public void test() {
            String str = "123";
            changeValue(str);
            System.out.println("str值为: " + str);  // str未被改变，str = "123"
        }

        public void changeValue(String str) {
            str = "abc";
        }

        /**
         *    Java 中方法参数的使用情况总结：
         *
         *      一个方法不能修改一个基本数据类型的参数（即数值型或布尔型）；
         *      一个方法可以改变一个对象参数的状态；
         *      一个方法不能让对象参数引用一个新的对象
         */
        public void test1() {
            Student student = new Student("Bobo", 15);
            changeValue1(student);   // student值未改变，不为null! 输出结果 student值为 name:Bobo、age:15
            changeValue2(student);  // student值被改变，输出结果 student值为 name:Lily、age:20
            System.out.println("student值为 name: " + student.name + "、age:" + student.age);
        }

        public void changeValue1(Student student) {
            student = null;
        }

        public static void changeValue2(Student student) {
            student.name = "Lily";
            student.age = 20;
        }

        public class Student{

            private String name;
            private int age;
            public Student(String name, int age) {
                this.name = name;
                this.age = age;
            }
        }
    }

}
