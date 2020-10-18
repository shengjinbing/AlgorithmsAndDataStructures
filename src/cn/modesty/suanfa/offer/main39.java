package cn.modesty.suanfa.offer;

/**
 * 数组中出现次数超过一半的数字
 * <p>
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * <p>
 *  
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 */
public class main39 {

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(majorityElement(data));
    }

    public static int majorityElement(int[] nums) {
        int j = 1;
        int k = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == k){
                j++;
            }else if (--j == 0){
                j++;
                k = nums[i];
            }
        }
        return k;
    }
}
