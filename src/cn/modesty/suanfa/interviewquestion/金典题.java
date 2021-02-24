package cn.modesty.suanfa.interviewquestion;

public class 金典题 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个无序数组arr，找到数组中未出现的最小正整数
     * 例如arr = [-1, 2, 3, 4]。返回1
     * arr = [1, 2, 3, 4]。返回5
     * [要求]
     * 时间复杂度为O(n)O(n)，空间复杂度为O(1)O(1)
     */
    public static int minNumberdisappered(int[] arr) {
        // write code here
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] >= 1 && arr[i] < n && arr[arr[i] - 1] != arr[i]) {
                swap(arr, arr[i] - 1, i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
