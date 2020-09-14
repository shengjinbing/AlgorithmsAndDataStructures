package cn.modesty.suanfa.offer;

/**
 * 斐波那契
 *
 * f
 *
 */
public class main10 {

    public static void main(String[] args) {
        System.out.println(fun(4));
    }

    /**
     * 递归
     * @param n
     * @return
     */
/*    private static int Fibonacci(int n){

    }*/

    private static int fun(int n){
        int a  = 0;
        int b = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
          sum = b + a;
          a = b;
          b = sum;
        }

        return sum;
    }
}
