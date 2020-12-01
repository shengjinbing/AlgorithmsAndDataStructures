package cn.modesty.suanfa.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * https://juejin.im/post/6854573219412885518#heading-22
 * 为什么使用泛型？
 * 1、消除类型转换
 * 2、再编译时进行更强的类型检测
 * 3、增加代码的复用性
 *
 *
 *
 * 泛型类：泛型类是通过类型进行参数化的类
 *
 * 1、在泛型类中定义泛型方法时，需要注意，泛型类里的泛型参数 <T> 和泛型方法里的泛型参数 <T> 不是同一个。
 * 2、我们经常看到类似 List<? extends Number> 的代码，? 就是通配符，表示未知类型
 * 3、上限通配符将未知类型限定为该类型或其子类型，使用 extends 关键字，而下限通配符将未知类型限定为该类型或其父类型，
 * 使用 super 关键字。
 * <? extends Number> 上限通配符
 * <? super Number> 下限通配符
 *
 *
 * 泛型的类型擦除问题？--->运行时都会转换成object
 */
public class GenericityMain {
    public static void main(String[] args) {
        //向上转型
        Apple appletest = new Apple();
        Fruit fruittest = appletest;


        List<Apple> apples = new ArrayList();
        //List<Fruit> fruits = apple;

        List<? extends Fruit> fruits1 = apples;
        List<? super Apple> fruits2 = apples;


        Apple apple = new Apple();
        Orange orange = new Orange();
        Fruit fruit = new Fruit();

        //上限通配符无法 set 数据，但是，可以 get 数据且只能 get 到其上限 Fruit，所以，上限通配符可以安全的访问
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


        /*下限通配符可以且只能 set 其下限 Apple，也可以 get 数据，但只能用 Object 接收(因为Object是所有类型的
        父类，这是一个特例)，所以，下限通配符可以安全的写入数据。

        所以，在使用上下限通配符时，可以遵循以下准则：
        如果你只需要从集合中获得类型T , 使用<? extends T>通配符
        如果你只需要将类型T放到集合中, 使用<? super T>通配符
        如果你既要获取又要放置元素，则不使用任何通配符*/

        AiFruitPlate<? super Apple> fruitPlateGen1 = new AiFruitPlate<Fruit>();
        fruitPlateGen1.set(apple); // OK
//        fruitPlateGen1.set(orange); // error
//        fruitPlateGen1.set(fruit); // error
        //  Apple object = fruitPlateGen1.get();// OK
//        Fruit fruit2 = fruitPlateGen1.get(); // error
//        Apple apple2 = fruitPlateGen1.get(); // error
//        Orange orange2 = fruitPlateGen1.get(); // error

        //AiFruitPlate<? extends Fruit> fruitPlateGen = new AiFruitPlate<Apple>();//不能用add
        //AiFruitPlate<? super Apple> fruitPlateGen1 = new AiFruitPlate<Fruit>();//不能用get

    }

}
