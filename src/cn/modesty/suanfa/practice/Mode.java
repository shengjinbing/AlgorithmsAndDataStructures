package cn.modesty.suanfa.practice;

/**
 * 求众数
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * 难度:简单
 * 难点：不借助HashMap或多重循环求解
 * 摩尔投票算法
 */
public class Mode {

    public static void main(String[] args) {

    }

    public static int majorityElement(int[] nums) {
        int count = 1;
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0){
                temp = nums[i];
            }
            if (temp==nums[i]){
                count++;
            }else {
                count--;
            }
        }

        return temp;
    }
}
