package cn.modesty.suanfa.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态规划题目
 *
 * 1.递归+记忆化 -> 递推
 * 2.状态的定义：opt[n],dp[n],fin[n]
 * 3.状态转移方程：opt[n] = best_of(opt[n-1],opt[n-2],...)
 * 4.最优子结构
 */
public class DynamicMain {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 2, 5, 1, 3, 3}));
        System.out.println(maxSubArray(new int[]{-1,0,-2}));
        System.out.println(rob(new int[]{2,7,9,3,1}));
        System.out.println(1%3);
    }

    /**
     * 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房
     * 屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * 示例 1：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2：
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     * 提示：
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length ==0)return 0;
        if (nums.length == 1)return nums[0];
        int max;
        int[] states = new int[nums.length];
        //states[0] = nums[0];
        //states[1] = Math.max(nums[0],nums[1]);

        int first = nums[0];
        int second = Math.max(nums[0],nums[1]);

        for (int i = 2; i < nums.length; i++) {
            //states[2] = Math.max(states[i-1],states[i-2]+nums[i]);

            int temp = second;
            second = Math.max(second,first+nums[i]);
            first = temp;
        }
        max = second;
        //max = states[nums.length-1];
        return max;
    }

    /**
     * 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:
     *
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);

            pre += nums[i];
            pre = pre>nums[i] ? pre : nums[i];
            max = max > pre? max : pre;
        }
        return max;
    }

    /**
     * 买卖股票的最佳时机
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * 注意：你不能在买入股票前卖出股票。
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * 示例 2:
     *
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int buy = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int profit = prices[i] - buy;
            if (profit > maxProfit) {
                maxProfit = profit;
            }else if (buy > prices[i]){
                buy = prices[i];
            }
        }
        return maxProfit;
    }


}
