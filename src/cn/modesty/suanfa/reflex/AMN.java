package cn.modesty.suanfa.reflex;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AMN {
    private static final Singleton<ClassB2Interface> gDefault =
            new Singleton<ClassB2Interface>() {
                @Override
                protected ClassB2Interface create() {
                    ClassB2 classB2 = new ClassB2();
                    classB2.id = "cccc";
                    return classB2;
                }
            };

    static public ClassB2Interface getDefault() {
        return gDefault.get();
    }

    public interface ClassB2Interface {
        void testDosomething();

    }

    public static class ClassB2 implements ClassB2Interface {
        private String id = "sasa";
        public String getId(){
            return id;
        }

        @Override
        public void testDosomething() {
            System.out.println("doSomething");
        }
    }

    public static class classB2Mock implements InvocationHandler {
        ClassB2Interface obj;
        classB2Mock(ClassB2Interface classB2Interface) {
            obj = classB2Interface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("invoke");
            return null;
        }
    }
}
