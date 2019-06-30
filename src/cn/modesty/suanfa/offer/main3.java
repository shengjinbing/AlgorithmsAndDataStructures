package cn.modesty.suanfa.offer;


/**
 * 题目：找出数组中重复的数字(在一个长度为n的数组里的所有数字都在0-n-1的范围内)
 * 1.通过排序后查找，时间复杂度过高
 * 2.通过Hash表来做，但是需要额外n的空间开销
 * 3.用一个O（n）的时间复杂的来完成
 */
public class main3 {

    public static void main(String[] args) {
        int[] data = {2, 3, 1, 0, 2, 5, 3};
        int[] data1 = {0, 1, 2, 3, 4, 5, 6, 7};
        System.out.println(duplicate(data1));


    }

    /**
     * @param a
     * @return 是否存在重复数字
     */
    private static boolean duplicate(int[] a) {
        for (int i = 0; i < a.length; i++) {
            //判断当前位子的下表和值是否相等如果不相等一直交换位子直到相等
            while (a[i] != i) {
                if (a[i] == a[a[i]]) {
                    return true;
                }
                int temp = a[i];
                a[i] = a[temp];
                a[temp] = temp;
            }
        }

        return false;
    }

    /**
     * 变种-----不能改变原有数组中的数据
     * 1.不能使用额外空间
     * 2.时间复杂度还是为O（n）
     * @param a
     * @return
     */
    private static boolean duplicateChanage(int[] a) {
          return false;
    }

}
