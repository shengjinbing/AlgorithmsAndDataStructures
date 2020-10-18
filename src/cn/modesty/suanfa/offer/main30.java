package cn.modesty.suanfa.offer;

/**
 * 包含min函数的栈
 * <p>
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *  
 */
public class main30 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.min();
        minStack.pop();
        minStack.top();
        minStack.min();

    }

    static class MinStack {
        //链表头，相当于栈顶
        private ListNode head;

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            if (empty())
                throw new IllegalStateException("栈为空……");
            head = head.next;
        }

        public void pop() {
            if (empty())
                throw new IllegalStateException("栈为空……");
            head = head.next;
        }

        public int top() {
            if (empty())
                throw new IllegalStateException("栈为空……");
            return head.val;

        }

        public int min() {
            if (empty())
                throw new IllegalStateException("栈为空……");
            return head.min;

        }

        //判断栈是否为空
        private boolean empty() {
            return head == null;
        }
    }

    class ListNode {
        public int val;
        public int min;//最小值
        public ListNode next;

        public ListNode(int val, int min, ListNode next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
