package cn.modesty.suanfa.bagqueuestack;

import edu.princeton.cs.algs4.Stack;
import jdk.nashorn.internal.ir.IfNode;

public class StackAlg {

    /**
     * 判断括号字符串是否正确
     *
     * @param s
     * @return
     */
    public boolean isBracketCorrect(String s) {
        if (s.length() == 0 || s.length() == 1){
            return false;
        }
        Stack<Character> stack  = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() &&(c == '}' || c == ']' || c == ')')){
                return false;
            }
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else {
                char pop = stack.pop();
                switch (c) {
                    case '}':
                        if (pop != '{'){
                            return false;
                        }
                        break;
                    case ')':
                        if (pop != '('){
                            return false;
                        }
                        break;
                    case ']':
                        if (pop != '['){
                            return false;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return true;
    }

    /**
     * 计算字符串运算
     *
     * @param str
     * @return
     */
    public int getResult(String str) {
        return 0;
    }
}
