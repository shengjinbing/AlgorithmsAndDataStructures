package cn.modesty.suanfa.find;


import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 */
public class BinarySearch {
    /**
     *
     * (重点)
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     * 示例 1:
     * 输入: [1,3,4,2,2]
     * 输出: 2
     * 示例 2:
     * 输入: [3,1,3,4,2]
     * 输出: 3
     * 说明：
     * 不能更改原数组（假设数组是只读的）。
     * 只能使用额外的 O(1) 的空间。
     * 时间复杂度小于 O(n2) 。
     * 数组中只有一个重复的数字，但它可能不止重复出现一次。
     *
     */
    public int findDuplicate(int[] nums) {
        int l = 1,r = nums.length -1;
        int res = 1;
        while(l < r){
            int mid = (l + r) >> 1;
            int count = 0;
            for(int i = 0; i < nums.length ;i++){
                if(nums[i] <= mid){
                    count++;
                }
            }
            if(count <= mid){
                l = l + 1;
                res = l;
            }else{
                r = mid;
            }
        }
        return res;
    }

    /**
     * 有效的完全平方数
     * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
     * 说明：不要使用任何内置的库函数，如  sqrt。
     * 示例 1：
     * 输入：16
     * 输出：True
     */
    public boolean isPerfectSquare(int num) {
        if(num < 2)return true;
        long l = 2,r = num/2;//这里是重点，不会超时，节省一半查找时间
        while(l <= r){
            long mid = l + (r - l)/2;
            long v = mid * mid;
            if(v > num){
                r = mid -1;
            }else if(v < num){
                l = mid +1;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     *
     * 找到 K 个最接近的元素(重点题658)
     * 给定一个排序好的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
     * 整数 a 比整数 b 更接近 x 需要满足：
     * |a - x| < |b - x| 或者
     * |a - x| == |b - x| 且 a < b
     *
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0,r = arr.length - k;
        while (l < r){
            int mid = l + (r -l)/2;
            if (x - arr[mid] > arr[mid+k] -x){
                l = mid + 1;
            }else {
                r = mid;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = l; i < l + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }
    /**
     * 寻找旋转排序数组中的最小值II(重点)
     * 示例 1：
     * 输入：nums = [2,2,2,0,1]
     * 输出：0
     *
     * @param nums
     * @return
     */
    public int findMin2(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else if (nums[pivot] > nums[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return nums[low];
    }

    /**
     * 寻找旋转排序数组中的最小值
     * 示例 1：
     * 输入：nums = [3,4,5,1,2]
     * 输出：1
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        int min = nums[0];
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid - 1 >= 0 && nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            if (mid + 1 < nums.length && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[l] < nums[mid]) {
                //在右边
                l = mid + 1;
                min = Math.min(nums[l], min);
            } else {
                //在左边
                r = mid - 1;
            }
        }
        return min;
    }

    /**
     * 搜索旋转排序数组
     * 示例 1：
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {//mid左侧为有序数组
                if (nums[left] <= target && nums[mid] >= target) {//如果target在mid左侧的话
                    right = mid - 1;
                } else {//在mid右侧
                    left = mid + 1;
                }
            } else {//mid右侧为有序数组
                if (nums[mid] <= target && nums[right] >= target) {//如果target在mid右侧的话
                    left = mid + 1;
                } else {//在mid左侧
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 寻找峰值(重点题)
     * 示例 1:
     * 输入: nums = [1,2,3,1]
     * 输出: 2
     * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
     */
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] p = new int[]{-1, -1};
        _searchRangeand(p, 0, nums.length - 1, nums, target);
        return p;
    }

    public void _searchRangeand(int[] p, int l, int r, int nums[], int target) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                if (mid == 0 || nums[mid] > nums[mid - 1]) {
                    p[0] = mid;
                    p[1] = mid;
                    for (int i = mid; i < nums.length; i++) {
                        if (nums[i] > target) {
                            p[1] = i - 1;
                            break;
                        }
                    }
                    break;
                } else {
                    r = mid - 1;
                }

            }
        }
    }


    /**
     * @param findData
     * @param a
     * @return 位子
     */
    public static int find(int findData, int[] a, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > findData) {
                hi = mid - 1;
            } else if (a[mid] < findData) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return 0;
    }


    /**
     * 递归二分查找
     *
     * @param findData
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    public static int recursionFind(int findData, int[] a, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int mid = lo + (hi - lo) / 2;//不使用(lo+hi)/2,是防止两个数相加过大
        if (a[mid] > findData) {
            return recursionFind(findData, a, lo, mid - 1);
        } else if (a[mid] < findData) {
            return recursionFind(findData, a, mid + 1, hi);
        } else {
            return mid;
        }
    }

    /**
     * 查找第一个值等于给定值的元素
     *
     * @param search
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    public static int searchFirst(int search, int a[], int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > search) {
                hi = mid - 1;
            } else if (a[mid] < search) {
                lo = mid + 1;
            } else {
                if (mid == 0 || a[mid] > a[mid - 1]) {
                    //mid == 0 要放前面防止啊a[mid -1]出现数组越界
                    return mid;
                } else {
                    //继续查找,找第一个当然找左半部分
                    hi = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     *
     * @param search
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    public static int searchEnd(int search, int a[], int lo, int hi) {
        int n = a.length;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > search) {
                hi = mid - 1;
            } else if (a[mid] < search) {
                lo = mid + 1;
            } else {
                if (mid == n || a[mid] < a[mid + 1]) {
                    return mid;
                } else {
                    lo = mid + 1;
                }
            }
        }

        return -1;
    }

    /**
     * 查找第一个大于等于给定值的
     *
     * @param search
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    public static int searchFirstBig(int search, int a[], int lo, int hi) {
        int n = a.length;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > search) {
                if (mid == 0 || a[mid - 1] < search) {
                    return mid;
                } else {
                    hi = mid - 1;
                }
            } else if (a[mid] < search) {
                lo = mid + 1;
            } else {
                if (mid == 0 || a[mid] > a[mid - 1]) {
                    return mid;
                } else {
                    hi = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的
     *
     * @param search
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    public static int searchFirstLess(int search, int a[], int lo, int hi) {
        int n = a.length;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > search) {
                hi = mid - 1;
            } else if (a[mid] < search) {
                if (mid == n || a[mid + 1] > search) {
                    return mid;
                } else {
                    lo = mid + 1;
                }

            } else {
                if (mid == n || a[mid] < a[mid + 1]) {
                    return mid;
                } else {
                    lo = mid + 1;
                }
            }
        }

        return -1;
    }
}
