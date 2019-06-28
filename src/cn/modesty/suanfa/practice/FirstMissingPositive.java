package cn.modesty.suanfa.practice;

import java.util.Arrays;

/**
 * 缺失的第一个正数
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 说明:
 * <p>
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 * <p>
 * 难度：困难
 * 难点：
 */
public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] data1 = new int[]{1, 2, 0};
        int[] data2 = new int[]{3, 4, -1, 1};
        int[] data3 = new int[]{7, 8, 9, 11, 12};
        System.out.println(firstMissingPositive(data1));

    }

    /**
     * 符合要求的做法
     * @param nums
     * @return
     */
    public static int newfirstMissingPositive(int[] nums){
        if (nums.length == 0) return 1;
        //定义一个数组来存放存整数是否在这个范围
        boolean[] temp = new boolean[nums.length+1];
        //这里必须定义一个标志位，判断是否有符合的数据
        boolean found = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 1 && temp.length > nums[i]){
                //符合大于等于1，并且当前值在这个temp的范围内，进行标记
                temp[nums[i]] = true;
                //有符合的数据
                found = true;
            }
        }
        //得从1开始遍历
        for (int i = 1; i < temp.length; i++) {
            if (!temp[i]){
                return i;
            }
        }
        //这里需要进行说明，
        // found为true代表temp全部为true，代表数组从1-nums.length,输出结果为temp.length
        // found为false代表一个符合数据要求的都没有，那么必然为1
        return found?temp.length:1;

    }

    /**
     * 解法太繁琐，还需要增加一个排序（不推荐）
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        if (nums.length == 1){
            if (nums[0] == 1){
                return 2;
            }else {
                return 1;
            }
        }
        Arrays.sort(nums);
        if (nums[0] > 1) return 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] < 0 && nums[i] > 1) return 1;
            if (nums[i-1] >= 0 && nums[i] - nums[i-1] > 1) {
                return nums[i-1] + 1;
            }
        }
        if (nums[nums.length - 1] >= 1){
            return nums[nums.length - 1] + 1;
        }else {
            return 1;
        }

    }
}
