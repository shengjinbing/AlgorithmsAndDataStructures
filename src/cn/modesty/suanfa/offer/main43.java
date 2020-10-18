package cn.modesty.suanfa.offer;

/**
 * 1～n整数中1出现的次数
 *
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：5
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：6
 *
 */
public class main43 {
    public static void main(String[] args) {

    }

    public int countDigitOne(int n) {
        int hight = n / 10;
        int cur = n % 10;
        int digit = 1;
        int low = 0;
        int res = 0;
        while (cur != 0 || hight != 0){
            if (cur == 0){
                res += hight * digit;
            }else if (cur == 1){
                res += hight*digit + low + 1;
            }else {
                res += (hight+1) * digit;
            }
            low +=  cur * digit;
            cur = hight % 10;
            hight /= 10;
            digit *= 10;
        }
        return res;
    }
}
