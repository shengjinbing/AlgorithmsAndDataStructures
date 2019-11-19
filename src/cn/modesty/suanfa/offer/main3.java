package cn.modesty.suanfa.offer;


/**
 * 题目：找出数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0-n-1的范围内。数组中某些数字是重复，但不知道有几个数字重复了，也不知道每个数字重
 * 复了几次。请找出数组中任意一个重复的数字例如，输入长度为7的数组{2，3，1，0，2，5，3}，那么对应的输出是重复的数字2或者3.
 *
 * 1.通过排序后查找，时间复杂度过高
 * 2.通过Hash表来做，但是需要额外n的空间开销
 * 3.用一个O（n）的时间复杂的来完成
 */
public class main3 {

    public static void main(String[] args) {
        int[] data = {2, 3, 1, 0, 2, 5, 3};
        int[] data1 = {0, 1, 2, 3, 4, 5, 6, 7,7};
        System.out.println(duplicate(data1));
        System.out.println(duplicateChanage(data1));


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
     * 2.时间复杂度还是为O（nlgn）--------------countRange处理二分被调用lgn次
     *
     * @param a
     * @return
     */
    private static int duplicateChanage(int[] a) {
        if (a == null && a.length < 1) return -1;
        int start = 0;
        int end = a.length - 1;
        while (start <= end){
            int mid = start+(end-start)/2;
            int count = countRange(a, start, mid);
            if (start == end){
                //二分找到最后一个元素
                if (count > 1){
                    //重复了
                    return start;
                }else {
                    //没有重复元素
                    break;
                }
            }
            if (count > mid-start+1){
                end = mid;
            }else {
                start = mid +1;
            }
        }


          return -1;
    }

    /**
     * 查找start-end范围的数是否存在重复
     * @param a
     * @param start
     * @param end
     * @return
     */
    private static int countRange(int[] a,int start,int end){
        if (a == null) return 0;
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            //查找start-end在数组中存在的个数
            if (a[i] >= start && a[i] <= end){
                count++;
            }
        }
        return count;
    }
}
