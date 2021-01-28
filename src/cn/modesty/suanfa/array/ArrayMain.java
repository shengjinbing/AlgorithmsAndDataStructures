package cn.modesty.suanfa.array;


import java.util.*;

/**
 * 数组相关题目
 */
public class ArrayMain {
    public static void main(String[] args) {
        int[] data = {7,1,5,3,6,4};
        int[] data1 = {1,2,3,4,5};
        int[] data2 = {7,6,4,3,1};
        //System.out.println(maxProfit(data));
        //System.out.println(maxProfit(data1));
        //System.out.println(maxProfit(data2));

        int[] data3 = {1,2,3,4,5,6,7};
        //rotate(data3,3);

        //intersect(new int[]{1,2,2,1},new int[]{2,2});

        //merge(new int[]{2,0},1,new int[]{1},1);
        System.out.println(firstBadVersion(4));
        int[] ints = new int[3];
        ints[0] = 1;
    }

    /**
     * 第一个错误的版本
     * @param n
     * @return
     */
    public static int firstBadVersion(int n) {
        int s = 1;
        int e = n;
        while (s < e){

            
            int mid = s + (e - s)/2;
            if (isBadVersion(mid)){
                e = mid;
            }else{
                s = mid + 1;
            }
        }
        return s;

        /*if (isBadVersion(1))return 1;
        return recursion(1,n);*/
    }

    public static int recursion(int s,int e){
        if(s >= e)return 0;
        int mid = s + (e - s)/2;
        if(!isBadVersion(mid) && isBadVersion(mid+1)){
            return mid+1;
        }else if(isBadVersion(mid) && !isBadVersion(mid-1)){
            return mid;
        }else if (isBadVersion(mid)){
            return recursion(s,mid-1);
        }else {
            return recursion(mid+1,e);
        }
    }

    private static boolean isBadVersion(int i) {
        return i >= 4;
    }

    /**
     * 合并两个有序数组
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * 说明：
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * 示例：
     *
     * 输入：
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     *
     * 输出：[1,2,2,3,5,6]
     *
     *
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (j >= 0){
            if (i >= 0 && nums1[i] > nums2[j]){
                nums1[k--] = nums1[i--];
            }else {
                nums2[k--] = nums2[j--];
            }
        }
    }

    /**
     * 数组中的K-diff数对
     * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.
     *
     * 示例 1:
     *
     * 输入: [3, 1, 4, 1, 5], k = 2
     * 输出: 2
     * 解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
     * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findPairs(int[] nums, int k) {
        if(k < 0) return 0;
        Arrays.sort(nums);
        int start = 0, count = 0, prev = 0x7fffffff;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] - nums[start] > k || prev == nums[start]) {
                //相等的话，下一次循环i++是正常
                if (++start != i) i--;
            }else if (nums[i] - nums[start] == k) {
                prev = nums[start++];
                count++;
            }
        }
        return count;

    }


    /**
     * 旋转矩阵
     *
     * 给定一个 n × n 的二维矩阵表示一个图像。
     * 将图像顺时针旋转 90 度。
     * 说明：
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     *
     * 示例 1:
     *
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        // transpose matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        // reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }


    /**
     * 有效的数独
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int r = board.length;
        int c = board[0].length;
        HashMap<Integer, Integer> [] rows = new HashMap[9];
        HashMap<Integer, Integer> [] columns = new HashMap[9];
        HashMap<Integer, Integer> [] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                char num = board[i][j];
                if (num != '.'){
                    int n = num;
                    int boxIndex = (i / 3) * 3 + j / 3;
                    if (rows[i].containsKey(n)
                            || columns[j].containsKey(n)
                            ||boxes[boxIndex].containsKey(n)) {
                        return false;
                    }
                    rows[i].put(n,i);
                    columns[i].put(n,i);
                    boxes[i].put(n,i);
                }

            }
        }
        return true;

    }


    /**
     * 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int place = 0;//最后一个位子
        for(int i = 0;i < nums.length;i++){
            if(nums[i] != 0){
                if(place != i){
                    nums[place] = nums[i];
                    nums[i] = 0;
                }
                place++;
            }
        }

    }

    /**
     * 俩数组交集
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(i))continue;
            for (int j = 0; j < nums2.length; j++) {
                if (map.containsValue(j))continue;
                if (nums1[i] == nums2[j]){
                    list.add(nums1[i]);
                    map.put(i,j);
                    break;
                }
            }
        }
        int[] data = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i);
        }
        return data;
    }

    /**
     * 只出现一次的数据查找
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 使用异或来实现，同为假，异为真
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res^=nums[i];
        }
        return res;
    }


    /**
     * 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     *
     * 示例 2:
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        //时间复杂度为O(n)的解法，使用环状替换
        int length = nums.length;
        k = k % length; //防止K值大于数组
        int count = 0;//每个元素移动一次整个数组就旋转完毕了，停止循环
        for (int i = 0; count <length; i++) {
            int curIndex = i;
            //当前值
            int prev = nums[i];
            do {
                //下一个值的下标
                int nextIndex = (curIndex + k) % length;
                //下一个值
                int next = nums[nextIndex];
                //当前值替换掉下一个值
                nums[nextIndex] = prev;
                //更换前一个值索引
                curIndex = nextIndex;
                //更换前一个索引
                prev = next;
                count++;

            }while (curIndex != i);
        }

        //反转的解法,反转三次
        /*k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);*/


        //时间复杂度最高的解法，O(n∗k)
        /*int length = nums.length ;
        k = k % nums.length;
        for(int i = 0; i < k; i++){
            int end = nums[length -1];
            for(int j = length -1; j >= 0; j--){
                if(j == 0){
                    nums[j] = end;
                    break;
                }
                nums[j] = nums[j-1];
            }
        }*/
    }
    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 买卖股票的最佳时机 II
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     *
     * 示例 2:
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     *
     * 示例 3:
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices 总利润
     * @return
     */
    public static int maxProfit(int[] prices) {
        int sumPrice = 0;
        int buyIndex = -1;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i -1]){
                //之前没有买点，现在出现买点
                if (buyIndex == -1){
                    buyIndex = i - 1 ;
                }
            }else if ( buyIndex != -1){
                //已经买过股票现在出现卖点
                sumPrice += prices[i - 1] - prices[buyIndex];
                //卖完股票重置买点索引
                buyIndex = -1;
            }
            //数据尾部边界处理，已经买过股票到边界卖掉
            if (buyIndex != -1 && i == prices.length -1){
                sumPrice += prices[i] - prices[buyIndex];
            }
        }
        return sumPrice;

    }

    /**
     * 删除排序数组中的重复项
     *
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 示例 1:
     * 给定数组 nums = [1,1,2],
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * 示例 2:
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums 数组长度
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int end = 0; //最好一个元素的索引
        int count = 0;//重复元素的数量
        for(int i = 1; i < nums.length; i++){
            if(nums[end] != nums[i] ){
                if(end != i -1){
                    nums[++end] = nums[i];
                }else{
                    end++;
                }
            }else{
                count++;
            }
        }
        return nums.length - count;
    }

}
