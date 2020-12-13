package cn.modesty.suanfa.bit;

/**
 * 位运算
 * 无符号右移 a>>> b
 *
 * 常见的使用：
 * 1.m*2^n
 * m>>n
 * 2.判断一个数n的奇偶性
 * n&1 == 1?”奇数”:”偶数”
 * 3.不用临时变量交换两个数
 * 4.取绝对值
 * (a^(a>>31))-(a>>31)
 */
public class BitMain {
    public static void main(String[] args) {
        System.out.println(1<<30);
        int[] ints = {1, 2, 3, 4, 5};
        reverse(ints);
        for (int j : ints){
            System.out.println(j);
        }
    }

    public static int[] reverse(int[] nums){
        int i = 0;
        int j = nums.length-1;

        while(j>i){
            nums[i]= nums[i]^nums[j];
            nums[j] = nums[j]^nums[i];
            nums[i] = nums[i]^nums[j];
            j--;
            i++;

        }
        return nums;

    }
}
