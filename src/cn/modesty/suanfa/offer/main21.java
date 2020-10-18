package cn.modesty.suanfa.offer;

/**
 * 调整数组顺序使奇数在前偶数在后
 */
public class main21 {
    public static void main(String[] args) {
        int[] a = {2,4,6,8};
        int[] exchange = exchange(a);
        for (int i = 0; i < exchange.length; i++) {
            System.out.println(exchange[i]);
        }
    }

    public static int[] exchange(int[] nums) {
        int i = 0;
        int j = nums.length -1;
        while (i < j) {
            while (i < j && nums[i] % 2 != 0){
                //找奇数
                i++;
            }
            while (i < j && nums[j] % 2 == 0){
                //找偶数
                j--;
            }
            if (i < j){
                //前后交换
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                //交换完了后
                i++;
                j--;
            }
        }
        return nums;
    }
}
