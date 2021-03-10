package cn.modesty.suanfa;

public class TestMain {
    public static void main(String[] args) {
        int[] a = new int[]{1,3,5,7,9,20};
        System.out.println(findMid(a, 5));
    }

    public static int  findMid(int arr[],int k){
        int s = 0;
        int e = arr.length -1;
        while (s < e){
            int mid = s + (e-s)/2;
            if (arr[mid] > k){
                e = mid - 1;
            }else if (arr[mid] < k){
                s = mid +1;
            }else{
                return mid;
            }
        }
        return  -1;
    }
}
