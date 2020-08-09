package cn.modesty.suanfa.offer;

/**
 * 旋转数组的最小数字
 * 题目：把一个数组最开始的若干个元素搬到数组的尾部，我称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组
 * 的最小元素。例如，数组{3，4，5，1，2}为{1，2，3，4，5}的一个旋转，该数组的最小值为1
 */
public class main11 {
    public static void main(String[] args) {

        int[] a = {3, 4, 5, 1, 2};
        int[] b = {1, 0, 1, 1, 1};
        System.out.println(findMin(a));
        System.out.println(1);
    }

    private static int findMin(int[] a){
        int s = 0;
        int e = a.length -1;
        //开始的元素小于最后元素，证明是有序排序直接返回首元素
        if (a[s] < a[e]) return a[s];
        int mid = 0;
        //结束条件是后一个元素小于前一个元素，也就是旋转数组的分界点
        while (a[s] >= a[e]){
            if (e - s  == 1){
                mid = e;
                break;
            }
             mid = (e+s)/2;

            //如果前中后相等采用遍历查找
            minInOrder(a,s,e);

            if (a[mid] >= a[s]){
                 s  = mid;
            }else if (a[mid] <= a[e]){
                 e = mid ;
            }
        }
        return a[mid];

    }


    private static int minInOrder(int[] a,int s,int e) {
        int result = a[s];
        for (int i = s + 1; i <= e; i++) {
            if (result > a[i]) {
                result = a[i];
            }
        }
        return result;
    }
}
