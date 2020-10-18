package cn.modesty.suanfa.offer;

/**
 *  数字序列中某一位的数字
 *
 *  数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：3
 *
 * 示例 2：
 *
 * 输入：n = 11
 * 输出：0
 *
 */
public class main44 {
    public static void main(String[] args) {
        System.out.println(findNthDigit(0));

    }
    public static int findNthDigit(int n) {
        int digit = 1;
        long start = 1,count = 9;
        while (n > count){
            n -= count;
            digit += 1;
            start *= 10;
            count = 9 * start * digit;
        }
        Long num = start + (n -1) / digit;
       return num.toString().charAt((n-1) % digit) - '0';
    }
}
