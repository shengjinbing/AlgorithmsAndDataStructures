package cn.modesty.suanfa.mathematics;

/**
 * 数学相关的算法
 */
public class MathematicsMain {
    public static void main(String[] args) {
        System.out.println(countPrimes(2));
    }

    /**
     * 计数质数(厄拉多塞筛法)
     *
     * 统计所有小于非负整数 n 的质数的数量。
     * 示例 1：
     *
     * 输入：n = 10
     * 输出：4
     * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     * 示例 2：
     *
     * 输入：n = 0
     * 输出：0
     * 示例 3：
     *
     * 输入：n = 1
     * 输出：0
     * @param n
     * @return
     */
    public static int countPrimes(int n){
        int count = 0;
        Boolean[] b = new Boolean[n+1];
        for (int i = 0; i <= n; i++) {
            b[i] = false;
        }
        for (int i = 2; i <= n; i++) {
            if (!b[i]){
                count++;
                for (int j = i+i; j < n; j+=i) {
                    b[j] = true;
                }
            }
        }
        return count;
    }
}
