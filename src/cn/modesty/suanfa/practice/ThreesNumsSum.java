package cn.modesty.suanfa.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * <p>
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 难度：中
 * 解题关键的：如何解决重复问题？
 */
public class ThreesNumsSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> data = new ArrayList();
        if (nums == null || nums.length <= 3) return data;
        int n = nums.length;
        //先进行排序
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            //去重第一步
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1;
            int k = n - 1;
            //从两边开始遍历，不使用三层遍历
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> data1 = new ArrayList();
                    data1.add(nums[i]);
                    data1.add(nums[j]);
                    data1.add(nums[k]);
                    data.add(data1);
                    while (j < k && nums[j] == nums[j + 1]) {
                        //去重第二步，防止连续两个数重复
                        j++;
                    }

                    while (j < k && nums[k] == nums[k - 1]) {
                        //去重第三步，防止连续两个数重复
                        k--;
                    }

                    j++;
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }


        }

        return data;
    }
}
