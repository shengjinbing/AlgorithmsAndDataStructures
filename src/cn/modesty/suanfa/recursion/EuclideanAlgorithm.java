package cn.modesty.suanfa.recursion;

public class EuclideanAlgorithm {

    public static int sum(int a,int b){
        if (a < b){
            int temp;
            temp = a;
             a = b;
             b = temp;

        }
        return getGCD(a,b);
    }

    public static int getGCD(int a,int b){
        int r = a % b;
        if (r == 0){
            return b;
        }else {
            return getGCD(b,r);
        }
    }
}
