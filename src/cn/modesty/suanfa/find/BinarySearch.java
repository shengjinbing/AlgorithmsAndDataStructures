package cn.modesty.suanfa.find;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * 二分查找
 */
public class BinarySearch {
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
                if (mid == 0 ||a[mid-1] < search){
                    return mid;
                }else {
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
                if (mid == n ||a[mid+1] > search){
                    return mid;
                }else {
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
