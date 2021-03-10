package cn.modesty.suanfa.genericity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * https://juejin.im/post/6854573219412885518#heading-22
 * 为什么使用泛型 Java 5？
 * 1、消除类型转换
 * 2、在编译时进行更强的类型检测
 * 3、增加代码的复用性
 * ①泛型擦除的原因和效果，擦除的时机。
 *  1.因为“真泛型”的引入，势必会为原本不支持泛型的 API 平行添加一套泛型 API。而新增了API，对于 Java 开发者来说
 *  ，又必须要做迁移。
 *  2.编译期间将泛型类型中的类型参数全部替换 Object
 *  3.不再新增一套泛型 API，直接把已有的类型原地泛型化。
 *  4.处理泛化前后类型的兼容。
 * ②为何会有协变和逆变
 * ③通配符。
 *   ? extends 上限通配符
 *   ? super 下线通配符
 * ④存取原则和PECS法则
 *   下限通配符可以且只能 set 其下限 Apple，也可以 get 数据，但只能用 Object 接收(因为Object是所有类型的
 *    父类，这是一个特例)，所以，下限通配符可以安全的写入数据。
 *    所以，在使用上下限通配符时，可以遵循以下准则：
 *    1.如果你只需要从集合中获得类型T , 使用<? extends T>通配符
 *    2.如果你只需要将类型T放到集合中, 使用<? super T>通配符
 *    3.如果你既要获取又要放置元素，则不使用任何通配符
 *
 * 泛型类：泛型类是通过类型进行参数化的类
 *
 * 1、在泛型类中定义泛型方法时，需要注意，泛型类里的泛型参数 <T> 和泛型方法里的泛型参数 <T> 不是同一个。
 * 2、我们经常看到类似 List<? extends Number> 的代码，? 就是通配符，表示未知类型
 * 3、上限通配符将未知类型限定为该类型或其子类型，使用 extends 关键字，而下限通配符将未知类型限定为该类型或其父类型，
 * 使用 super 关键字。
 * <? extends Number> 上限通配符
 * <? super Number> 下限通配符
 * 4.where关键字，解决边界
 *   T extends Animal & Food
 *   class Monster<T> where T : Animal, T : Food
 * 5.reified关键字
 *   通过额外传递一个 Class<T> 类型的参数来检查
 *   <T> void check(Object item, Class<T> type) {
 *     if (type.isInstance(item)) {
 *     }
 *   }
 *   Kotlin使用关键字 reified 配合 inline 来解决：
 *   inline fun <reified T> printIfTypeMatch(item: Any) {
 *     if (item is T) { // 👈 这里就不会在提示错误了
 *         println(item)
 *     }
 *   }
 *
 * 泛型的类型擦除问题？--->运行时都会转换成object
 *
 * 小结下，Java 的泛型本身是不支持协变和逆变的。（重点）
 * 1.可以使用泛型通配符 ? extends 来使泛型支持协变，但是「只能读取不能修改」，这里的修改仅指对泛型集合添加元素，如果是 remove(int index) 以及 clear 当然是可以的。
 * 2.可以使用泛型通配符 ? super 来使泛型支持逆变，但是「只能修改不能读取」，这里说的不能读取是指不能按照泛型类型读取，你如果按照 Object 读出来再强转当然也是可以的。
 * 3.使用关键字 out 来支持协变，等同于 Java 中的上界通配符 ? extends。
 * 4.使用关键字 in 来支持逆变，等同于 Java 中的下界通配符 ? super。
 * 5.* 号
 * 前面讲到了 Java 中单个 ? 号也能作为泛型通配符使用，相当于 ? extends Object。 它在 Kotlin 中有等效的写法：* 号，相当于 out Any。
 * 作者：扔物线
 * 链接：https://juejin.cn/post/6844903929734496263
 */
public class GenericityMain {
    public static void main(String[] args) {
        //ArrayList<String>里面添加Int类型的值
        ArrayList<String> list=new ArrayList<String>();
        Method method = null;
        try {
            method = list.getClass().getMethod("add", Object.class);
            method.invoke(list, 1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(list);

        //向上转型
        Apple appletest = new Apple();
        Fruit fruittest = appletest;


        List<Apple> apples = new ArrayList();
        List<Fruit> fruits = new ArrayList();

        List<? extends Fruit> fruits1 = apples;
        List<? super Apple> fruits2 = apples;

        Apple apple = new Apple();
        Orange orange = new Orange();
        Fruit fruit = new Fruit();

        List<? extends Fruit> fruits3 = new ArrayList();
       /* fruits3.add(apple);
        fruits3.add(fruit);
        fruits3.add(orange)*/
        List<? super Apple> fruits4 = new ArrayList();
        fruits4.add(apple);
        /*fruits4.add(fruit);
        fruits4.add(orange)*/
        //上限通配符不能set 数据，但是，可以 get 数据且只能 get 到其上限 Fruit，所以，上限通配符可以安全的访问
        // 数据。
        AiFruitPlate<? extends Fruit> fruitPlateGen = new AiFruitPlate<Apple>();
//        fruitPlateGen.set(apple); // error
//        fruitPlateGen.set(orange); // error
//        fruitPlateGen.set(fruit); // error
        Fruit fruit1 = fruitPlateGen.get(); // OK
//        Orange orange1 = fruitPlateGen.get(); // error
//        Apple apple1 = fruitPlateGen.get(); // error

        //这里编译不报错，运行报错
       /* Fruit[] fruits = new Apple[10];
        fruits[0] = new Apple();
        fruits[1] = new Orange();*/

        //List<Fruit> fruitList = new ArrayList<Apple>();//报错
        List<Fruit> fruitList1 = (ArrayList)new ArrayList<Apple>();//不建议的的写法
        fruitList1.add(apple);
        //Orange orange1 = (Orange)fruitList1.get(0);//错误




        AiFruitPlate<? super Apple> fruitPlateGen1 = new AiFruitPlate<Fruit>();
        fruitPlateGen1.set(apple); // OK
       fruitPlateGen1.set(orange); // error
//        fruitPlateGen1.set(fruit); // error
        //  Apple object = fruitPlateGen1.get();// OK
//        Fruit fruit2 = fruitPlateGen1.get(); // error
//        Apple apple2 = fruitPlateGen1.get(); // error
//        Orange orange2 = fruitPlateGen1.get(); // error

        //AiFruitPlate<? extends Fruit> fruitPlateGen = new AiFruitPlate<Apple>();//不能用add
        //AiFruitPlate<? super Apple> fruitPlateGen1 = new AiFruitPlate<Fruit>();//不能用get


        //测试上限通配符
        Herd<Cat> catHerd = new Herd<Cat>();
        //seta(catHerd);
    }

    private static void setv( List<Fruit> fruits){

    }

    private static void seta( Herd<Animal> herd){

    }

    interface Animal{

    }

    static class Cat implements Animal{

    }

    static class Herd<T extends Animal>{

    }

}
