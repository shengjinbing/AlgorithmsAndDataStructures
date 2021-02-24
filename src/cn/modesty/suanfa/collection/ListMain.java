package cn.modesty.suanfa.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ListMain {
    public static void main(String[] args) {
        StringBuffer res = new StringBuffer();
        res.append("2");
        res.append("3");
        res.append("4");
        System.out.println(5/6);
        System.out.println(1 + '0');
        System.out.println(maxArea(new int[]{3, 1, 2}));
    }
    /*public int[][] threeOrders (TreeNode root) {
        // write code here
        List<List<Integer>> data = new ArrayList();
        for(int i = 0; i<3;i++){
            data.add(new ArrayList<Integer>());
        }
        _threeOrders (root,data);
        int[][] in =  data.toArray(new int[3][3]);
        return in;
    }

    public void _threeOrders (TreeNode node,List<List<Integer>> data) {
        // write code here
        if(node == null)return;
        data.get(0).add(node.val);
        _threeOrders(node.left,data);
        data.get(1).add(node.val);
        _threeOrders(node.right,data);
        data.get(2).add(node.val);

    }*/
    /**
     * 把数组排成最小的数 (剑指offer45)
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     *
     * 示例 1:
     *
     * 输入: [10,2]
     * 输出: "102"
     * 示例 2:
     *
     * 输入: [3,30,34,5,9]
     * 输出: "3033459"
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));//compareTo方法是String的比较方法
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();

    }
    public static int maxArea(int[] height){
        int maxarea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                maxarea = Math.max(maxarea,Math.min(height[i],height[j] * (j-i)));
            }
        }
        return maxarea;
    }

}
