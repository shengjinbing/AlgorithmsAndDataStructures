package cn.modesty.suanfa.sort;

public class SortLeetcodeMain {
    public static void main(String[] args) {
        SortLeetcodeMain main = new SortLeetcodeMain();
        System.out.println(main.findKthLargest(new int[]{1}, 1));
        System.out.println(main.findPeakElement(new int[]{1,2,1,3,5,6,4}));
    }


    /**
     * 寻找峰值
     * @param nums
     * @return
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
     * 数组中的第K个最大元素
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums,0,nums.length-1,nums.length - k);
    }

    public int quickSort(int[] nums,int s,int e,int k){
        if(s > e)return 0;
        int q = find(nums,s,e);
        if (q == k){
            return nums[q];
        }
        if (q < k){
            return quickSort(nums,q+1,e,k);
        }
        return quickSort(nums,s,q-1,k);
    }

    public int find(int[] nums,int s,int e){
        int q = nums[s];
        int i = s;
        int j = e + 1 ;
        while(i < j){
            while(++i < j && nums[i] < q);
            while(nums[--j] > q);
            if(i < j){
                swap(nums,i,j);
            }else{
                break;
            }
        }
        swap(nums,s,j);
        return j;
    }

    public void swap(int[] nums,int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
