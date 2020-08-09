package cn.modesty.suanfa.offer;

import edu.princeton.cs.algs4.Stack;

/**
 * 用两个栈实现队列
 * 题目：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail和deleteHead,分别完成再队列尾部插入节点和在
 *       队列头部删除节点的功能。
 */
public class main9 {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue();
        queue.appendTail(1);
        queue.appendTail(2);
        queue.appendTail(3);
        queue.appendTail(4);
    }

    public static class  Queue<T>{
        Stack<T> stack1 = new Stack<>();
        Stack<T> stack2 = new Stack<>();

        /**
         * 头节点
         * @param t
         */
        public  void appendTail(T t){
            stack1.push(t);
        }

        public  void deleteHead(T t){
            if (!stack2.isEmpty()){
                stack2.pop();
            }else {
                while (!stack1.isEmpty()){
                    T pop1 = stack1.pop();
                    stack2.push(pop1);
                }
                if (!stack2.isEmpty())stack2.pop();
            }
        }
    }

}
