package cn.modesty.suanfa.practice;

/**
 * x得平方根（69）
 * <p>
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 难度：简单
 */
public class Sqrt {
    public static void main(String[] args) {
        System.out.println(mySqrt(8));
        System.out.println(mySqrtN(8));
    }


    /**
     * 二分搜索法
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        //注：在中间过程计算平方的时候可能出现溢出，所以用long long。
        long i = 0;
        long j = x / 2 + 1;//对于一个非负数n，它的平方根不会大于（n/2+1）
        while (i <= j) {
            long mid = (i + j) / 2;
            long res = mid * mid;
            if (res == x) return (int) mid;
            else if (res < x) i = mid + 1;
            else j = mid - 1;
        }
        return (int) j;
    }


    /**
     * 牛顿迭代法
     * @param x
     * @return
     */
    public static int mySqrtN(int x) {
        if (x == 0) return 0;
        double l=0;
        double r=1;
        while(r!=l)
        {
            l=r;
            r=(r+x/r)/2;
        }
        return (int) r;

    }

    /**
     * 翻转字符串
     * @param s
     */
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length-1;
        while (i < j) {
            char tem = s[i];
            s[i] = s[j];
            s[j] = tem;
            i++;
            j--;
        }
    }


}
