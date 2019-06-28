package cn.modesty.suanfa.practice;

import java.util.Stack;

/**
 * 最长有效括号32
 * <p>
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * <p>
 * 难度：困难
 * 难点：设计太巧妙，配对问题
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses("()(()"));
        System.out.println(longestValidParentheses("()(())"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses("()(()"));

        //"()(())"->6

    }

    public static int longestValidParentheses(String s) {
        if (s == null || s == "" || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }else {
                stack.pop();
                if (stack.isEmpty()){
                    stack.push(i);
                }else {
                    max = Math.max(max,i - stack.peek());
                }
            }
        }
        return max;
    }
}
