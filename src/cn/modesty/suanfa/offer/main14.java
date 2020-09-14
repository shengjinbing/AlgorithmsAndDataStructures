package cn.modesty.suanfa.offer;

/**
 * 剪绳子
 */
public class main14 {
    public static void main(String[] args) {
        System.out.println(maxProductAfterCutting_solution1(14));
    }

    private static int maxProductAfterCutting_solution1(int n){
        if (n < 2) return 0;
        if (n == 2) return  1;
        if (n == 3) return 2;

        int[] products = new int[n+1];
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;

        int max;

        for (int i = 4; i <= n; i++) {
             max = 0;
            for (int j = 1; j <= i/2; j++) {
                int prodcut = products[j]*products[i-j];
                if (prodcut > max){
                    max = prodcut;
                }
            }
            products[i] = max;
        }
        return products[n];
    }
}
