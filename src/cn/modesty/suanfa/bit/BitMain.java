package cn.modesty.suanfa.bit;

import java.util.ArrayList;
import java.util.List;

/**
 * 位运算
 * 无符号右移 a>>> b
 *
 * 常见的使用：
 * 1.m*2^n
 * m>>n
 * 法则一：任何数左移（右移）32的倍数位等于该数本身。
 * 法则二：在位移运算m<<n的计算中，若n为正数，则实际移动的位数为n%32，若n为负数，则实际移动的位数为(32+n%32)，右移，同理。
 * 2.判断一个数n的奇偶性
 * n&1 == 1?”奇数”:”偶数”
 * 3.不用临时变量交换两个数
 * 4.取绝对值
 * (a^(a>>31))-(a>>31)
 */
public class BitMain {
    public static void main(String[] args) {
        //reverse();
        testAnd();
        testMove();
        testunsignedMove();
        System.out.println(maximum(1,2));

    }

    /**
     *
     * 进制的转换
     * 给定一个十进制数M，以及需要转换的进制数N。将十进制数M转化为N进制数
     *M是32位整数，2<=N<=16.
     */
    public String solve (int M, int N) {
        // write code here
        if(M == 0) return "0";
        boolean flag = true;
        if(M < 0){
            M = - M;
            flag = false;
        }
        StringBuffer res = new StringBuffer();
        String hex = "0123456789ABCDEF";
        while(M != 0){
            res.append(hex.charAt(M % N));
            M = M / N;
        }
        return flag == true ? res.reverse().toString() : "-"+res.reverse().toString();
    }

    /**
     * 幂集
     * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
     * 说明：解集不能包含重复的子集。
     * 示例:
     *  输入： nums = [1,2,3]
     *  输出：
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    public static List<List<Integer>> subsets(int[] nums) {
        //子集的长度是2的nums.length次方，这里通过移位计算
        int length = 1 << nums.length;
        List<List<Integer>> res = new ArrayList<>(length);
        //遍历从0到length中间的所有数字，根据数字中1的位置来找子集
        for (int i = 0; i < length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                //如果数字i的某一个位置是1，就把数组中对
                //应的数字添加到集合
                if (((i >> j) & 1) == 1)
                    list.add(nums[j]);
            }
            res.add(list);
        }
        return res;
    }

    //两数相加
    public int add(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = (a ^ b);
            b = carry;
        }

        return a;
    }
    /**
     * 消失的数字
     * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
     *
     * 注意：本题相对书上原题稍作改动
     *
     * 示例 1：
     *
     * 输入：[3,0,1]
     * 输出：2
     */
    public int missingNumber(int[] nums) {
        int res = 0;
        for(int i = 0;i<nums.length;i++){
            res ^= i;
            res ^= nums[i];
        }
        res ^= nums.length;
        return res;
    }

    /**
     * 主要元素
     * 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
     * 示例 1：
     * 输入：[1,2,5,9,5,9,5,5,5]
     * 输出：5
     * @param nums
     * @return 摩尔投票法
     */
    public static int majorityElement(int[] nums) {
        int temp = nums[0];
        int count = 1;
        for(int i = 1;i< nums.length;i++){
            if(temp == nums[i]){
                count++;
            }else{
                count--;
                if(count == 0){
                    temp = nums[i];
                    count = 1;
                }
            }
        }
        count = 0;
        for(int num:nums){
            if(temp == num){
                count++;
                if(count == nums.length/2 +1){
                    return temp;
                }
            }
        }
        return -1;
    }
    /*
     最大数值
     编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
     示例：
     输入： a = 1, b = 2
     输出： 2
     本质是平均值法： max(a, b) = ((a + b) + abs(a - b)) / 2。
     */
    public static int maximum(int a, int b) {
        long c = (long) a + (long) b;
        long d = (long) a - (long) b;
        long l = (d ^ (d >> 63)) - (d >> 63);
        return (int) ((c+l)/2);

    }

    //无符号右移（>>>）低位溢出，高位补0。注意，无符号右移（>>>）中的符号位（最高位）也跟着变，无符号的意思是将符号位当作数字位看待
    private static void testunsignedMove() {
        System.out.println(-1>>>1);
        System.out.println(~(1 << 31));
        System.out.println((1 << -1)-1);
        System.out.println(~(1 << -1));

        System.out.println(1 << -1);

        /**
         * -1
         * 源码：10000000 00000000 00000000 00000001
         * 补码：11111111 11111111 11111111 11111111
         * 无符号左移1位：01111111 11111111 11111111 11111111
         *
         */
    }

    /**
     * 1.负数的反码是在其原码的基础上, 符号位不变，其余各个位取反.
     * 2.负数的补码是在其原码的基础上, 符号位不变, 其余各位取反, 最后+1. (即在反码的基础上+1)
     */
    private static void testMove() {
        int a = -6;
        System.out.println(-6>>2);
        /**
         * -6
         * 源码：10000000 00000000 00000000 00000110
         * 补码：11111111 11111111 11111111 11111010
         * 低位溢出，符号位不变，并用符号位补溢出的高位。>>2:11111111 11111111 11111111 11111110
         * 负数求源码：符号位不变减1按位取反：10000000 00000000 00000000 00000010 结果为-2
         *
         */
    }

    //&
    public static void testAnd(){
        int a = -6;
        /**
         * -6
         * 源码10000000 00000000 00000000 00000110
         * 求结果反码+1：11111111 11111111 11111111 11111011
         */
        int b = -2;
        /**
         * -2
         * 10000000 00000000 00000000 00000010
         * 求结果反码+1：11111111 11111111 11111111 11111110
         *
         * 相&后：11111111 11111111 11111111 11111010
         * 减1：11111111 11111111 11111111 11111001
         * 取反：10000000 00000000 00000000 00000110 得到源码
         */
        System.out.println(a&b);
    }

    //交互两个数值
    public static void reverse(){
        int[] nums = {1, 2, 3, 4, 5};

        int i = 0;
        int j = nums.length-1;

        while(j>i){
            nums[i]= nums[i]^nums[j];
            nums[j] = nums[j]^nums[i];
            nums[i] = nums[i]^nums[j];
            j--;
            i++;

        }
        for (int n : nums){
            System.out.println(n);
        }

    }
}
