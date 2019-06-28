package cn.modesty.suanfa.practice;


import cn.modesty.suanfa.bagqueuestack.LinkQueue;
import cn.modesty.suanfa.bagqueuestack.MaxPQ;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 滑动窗口最大值(239)
 *
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 注意：
 *
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 * 难度：困难
 * 难点：
 */
public class MaxSlidingWindow {
    public static void main(String[] args) {
        //int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] nums = {7,2,4};
        int[] maxSlidingWindow = maxSlidingWindow1(nums, 2);
        for (int i = 0; i < maxSlidingWindow.length; i++) {
            System.out.println(maxSlidingWindow[i]);

        }

       /* PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        queue.add(2);
        queue.add(1);
        queue.add(3);
        System.out.println(queue);*/

        Queue<Integer> queue1 = new ArrayDeque<>();
        queue1.add(1);
        queue1.add(2);
        queue1.add(3);
        queue1.add(3);
        queue1.add(3);
        queue1.add(3);
        System.out.println(queue1);
    }


    /**
     * 官方解法
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null)return new int[]{};
        if (nums.length == 0) return new int[0];
        if (k == 1) return nums;
        int max = Integer.MIN_VALUE;
        int length = nums.length;
        int[] result = new int[length - k + 1];
        for (int i = 0; i < k; i++) {
            if (max<nums[i]) max = nums[i];
        }
        result[0] = max;
        for (int i = 0; i < length -k ; i++) {
            if (max == nums[i]){
                //第一个值就是最大值
                max = nums[i+1];
                for (int j = i+1; j <i+k+1 ; j++) {
                    //赋值最大值
                    if (max < nums[j]) max = nums[j];

                }
            }else if (max < nums[i+k]){
                //最大值在后面两个位子
                max = nums[i+k];
            }
            result[i+1] = max;
        }
        return result;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0)return new int[0];
        int[] data = new int[nums.length+1 - k];
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        Queue<Integer> queue1 = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            queue1.add(nums[i]);
            queue.add(nums[i]);
            if (i >= k -1 ){
                data[i-k +1] = queue.peek();
                queue.remove(queue1.poll());
            }
        }

        return data;
    }

}
