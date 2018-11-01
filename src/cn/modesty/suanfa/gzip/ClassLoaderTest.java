package cn.modesty.suanfa.gzip;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader classLoader = new ClassLoader() {

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String      filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is       = getClass().getResourceAsStream(filename);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name,bytes,0,bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };

        try {
            Object obj = classLoader.loadClass("cn.modesty.suanfa.gzip.ClassLoaderTest").newInstance();

            System.out.println(obj.getClass());
            System.out.println(obj instanceof cn.modesty.suanfa.gzip.ClassLoaderTest);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
}
