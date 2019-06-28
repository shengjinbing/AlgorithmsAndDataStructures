package cn.modesty.suanfa.practice;

/**
 * 爬楼梯（70）
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 难度：简单
 * 难点：递归
 */
public class ClimbStairs {
    public static void main(String[] args) {

        System.out.println(climbStairs1(8));
        System.out.println(climbStairs(8));
    }

    /**
     * 使用非递归得方法
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n == 1) return n;
        //一阶，有1种走法
        int one = 1;
        //二阶，有2种走法
        int two = 2;
        int sum = 2;
        for (int i = 3; i <= n; i++) {
             sum  = one + two;
             one = two;
             two = sum;
        }
        return sum;
    }
    /**
     * 递归得方法，n过大会出现栈溢出
     * @param n
     * @return
     */
    public static int climbStairs1(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs1(n-1) +climbStairs1(n-2);
    }
}
