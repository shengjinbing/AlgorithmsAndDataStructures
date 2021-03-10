package cn.modesty.suanfa.kotlin

import java.util.*

/**
 * 作者：扔物线
 * https://juejin.cn/post/6869954460634841101
 * 1.一个变量如果以 const val 开头，它就会被编译器当做编译时常量来进行内联式编译（重点）
 * 2.因为 Java 并没有对函数类型的变量的原生支持，Kotlin 需要想办法来让这种自己新引入的概念在 JVM 中落地。
 * 而它想的办法是什么呢？就是用一个 JVM 对象来作为函数类型的变量的实际载体，让这个对象去执行实际的代码。
 * 也就是说，在我对代码做了刚才那种修改之后，程序在每次调用 hello() 的时候都会创建一个对象来执行 Lambda
 * 表达式里的代码，虽然这个对象是用一下之后马上就被抛弃，但它确实被创建了。
 * 3.高阶函数（Higher-order Functions）有它们天然的性能缺陷，我们通过 inline 关键字让函数用内联的方式进行编译，
 *   来减少参数对象的创建，从而避免出现性能问题。
 * 4.内联函数里被 crossinline 修饰的函数类型的参数，将不再享有「Lambda 表达式可以使用 return」的福利
 *
 * 
 */
fun main(args: Array<String>) {
    /*for (i in 1..100){
        hello {//每次调用都会创建一次对象，有性能隐患
            println("Bye")
            return@hello
        }
    }*/
    set(Herd<Cat>())
    val mutableListOf:MutableList<Any?> = mutableListOf('a', 1, "qwe")
    val mutableListOf1 = mutableListOf('a', 'b', "c")
    val  element :MutableList<*> = if (Random().nextBoolean()) mutableListOf else mutableListOf1

    var data : List<out Animal> = ArrayList()
}

fun set(animal: Herd<Animal>){

}

//需要返回的时候需要加上noinline
inline fun hello(noinline postAction:()-> Unit):()->Unit{
    println("Hello")
    postAction()
    return postAction
}

 interface Animal

 class Cat : Animal

 class Herd<out T : Animal?> //协变之后Herd<Cat>是Herd<Animal>的子类