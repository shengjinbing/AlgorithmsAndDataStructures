package cn.modesty.suanfa.practice;

import edu.princeton.cs.algs4.Stack;

/**
 * 有效的括号20
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * <p>
 * 难度：简单
 * 难点：栈
 */
public class IsValid {
    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
    }

    /**
     * Character是char的包装类,就像Integer和int
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s == null || s == "") return true;
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Character peek;
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            } else {
                peek = stack.peek();
            }

            if ((c == ')' && peek == '(') ||
                    (c == ']' && peek == '[') ||
                    (c == '}' && peek == '{')) {
                stack.pop();
                continue;
            }

            stack.push(c);
        }
        return stack.isEmpty() ? true : false;
    }
}
