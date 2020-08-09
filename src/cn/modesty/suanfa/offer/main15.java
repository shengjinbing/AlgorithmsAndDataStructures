package cn.modesty.suanfa.offer;


public class main15 {
    public static void main(String[] args) {

        System.out.println(fun(9));
        System.out.println(fun1(9));
    }

    private static int fun(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += n & 1;
            n = n >> 1;
        }
        return count;
    }

    private static int fun1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n - 1 & n;
        }
        return count;
    }
}
