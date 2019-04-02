package cn.modesty.suanfa.dynamicprogramming;

/**
 * 动态规划一个模型三个特征
 * 模型：多阶段决策最优解模型
 * 三个特征：
 * 1.最优子结构
 *   最优子结构指的是，问题的最优解包含子问题的最优解。反过来说就是我们可以通过子问题的最优解，推导出问题的最优解。
 *   如果我们把最优子结构，定义到前面我们动态规划问题模型上，那我们也可以理解为，后面阶段的状态可以通过前面阶段的状态推导出来
 * 2.无后效行
 * 3.重复子问题
 *
 *
 */
public class main {
    public static void main(String[] args) {
        int[] weight = {2, 2, 4, 6, 3};  // 物品重量
        int[] value = {3,4,8,9,6};//物品价值
        int n = 5; // 物品个数
        int w = 9; // 背包承受的最大重量
        System.out.println(dyn(weight, n, w));
        System.out.println(dyn1(weight, n, w));
        System.out.println(knapsack3(weight, value, n, w));
        int[][] matrix = {{5},{7,8},{2,3,4},{4,9,6,1},{2,7,9,4,5}};
        System.out.println(triangle(matrix));
        System.out.println(yanghuiTriangle(matrix));
        int b = 10;
        int a = b;
        b = 0;
        System.out.println(a);

    }

    /**
     * @param matrix
     * @return 从顶部导底部路径上的数字最小之和
     */
    private static int triangle(int[][] matrix) {
        int[][] status = new int[matrix.length][matrix.length];
        status[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0){
                    //第一个数据
                    status[i][j] = status[i-1][j] + matrix[i][j];
                }else if (j == matrix[i].length -1){
                    //最后一个数据
                    status[i][j] = status[i-1][j-1] + matrix[i][j];
                }else {
                    //其他情况
                    int min = 0;
                    if (status[i-1][j-1] > status[i-1][j]){
                        min = status[i-1][j];
                    }else {
                        min = status[i-1][j-1];
                    }
                    status[i][j] = min + matrix[i][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (min > status[matrix.length-1][i]) min = status[matrix.length-1][i];
        }
        return min;
    }

    /**
     * 杨辉三角模板代码
     * @param matrix
     * @return
     */
    public static int yanghuiTriangle(int[][] matrix) {
        int[][] state = new int[matrix.length][matrix.length];
        state[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) state[i][j] = state[i - 1][j] + matrix[i][j];
                else if (j == matrix[i].length - 1) state[i][j] = state[i - 1][j - 1] + matrix[i][j];
                else {
                    int top1 = state[i - 1][j - 1];
                    int top2 = state[i - 1][j];
                    state[i][j] = Math.min(top1, top2) + matrix[i][j];
                }
            }
        }
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[matrix.length - 1].length; i++) {
            int distance = state[matrix.length - 1][i];
            if (distance < minDis) minDis = distance;
        }
        return minDis;
    }

    /*
     *满足最大质量的前提下，装入物品的总价值最大
     * 二维数组实现
     * 返回最大价值
     */
    private static int dyn2(int[] weight, int[] value, int n, int w) {
        int[][] status = new int[n][w + 1];
        status[0][0] = 0;
        status[0][weight[0]] = value[0];
        for (int i = 1; i < n; i++) {

            for (int j = 0; j < w; j++) {
                if (status[i-1][j] > 0) status[i][j] = status[i-1][j];
            }

            for (int j = 0; j <= w - weight[i]; j++) {
                if (status[i-1][j] > 0){
                    int v = status[i][j+weight[i]] +value[i];
                    if (v > status[i][j+weight[i]]){
                        status[i][j+weight[i]] = v;
                    }
                }
            }

        }
        int maxValue = -1;
        for (int i = 0; i <= w; i++) {
          if (maxValue < status[n-1][i]){
              maxValue = status[n-1][i];
          }
        }
        return maxValue;
    }

    /**
     * 模板代码
     * @param weight
     * @param value
     * @param n
     * @param w
     * @return
     */
    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w+1];
        for (int i = 0; i < n; ++i) { // 初始化 states
            for (int j = 0; j < w+1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        states[0][weight[0]] = value[0];
        for (int i = 1; i < n; ++i) { // 动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第 i 个物品
                if (states[i-1][j] >= 0) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; ++j) { // 选择第 i 个物品
                if (states[i-1][j] >= 0) {
                    int v = states[i-1][j] + value[i];
                    if (v > states[i][j+weight[i]]) {
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n-1][j] > maxvalue) maxvalue = states[n-1][j];
        }
        return maxvalue;
    }


    /**
     * 0-1背包问题
     * 对于一组不同重量、不可分割的物品，我们需要选择一些装入背包，在满足背包最大重量限制的前提下，
     * 背包中物品总重量的最大值是多少
     *
     * 缺点：需要创建一个二维数组比较消耗空间
     *
     * */
    private static int dyn(int[] weight, int n, int w) {
        boolean[][] status = new boolean[n][w+1];//默认值都为false
        status[0][0] = true;
        status[0][weight[0]] = true;
        //从第二行开始
        for (int i = 1; i < n; i++) {//动态规划状态转移
            for (int j = 0; j <= w; j++) {
                //首先把当前行和上一行相同位子全部为true
                if (status[i-1][j] == true) status[i][j] = status[i -1][j];
            }

            for (int j = 0; j <= w ; j++) {
                //将当前物品重量加上上一行每一个符合状态的值小于w的设置为true
                if (status[i][j] == true && j+weight[i] <= w) status[i][j+weight[i]] = true;
            }
        }

        for (int i = w; i >= 0; i--) {
            if (status[n-1][i] == true) return i;
        }

        return 0;
    }

    /**
     * 只创建一个一纬数组解决问题
     */
    private static int dyn1(int[] weight, int n, int w) {
        boolean[] status = new boolean[w+1];
        status[0] = true;
        status[weight[0]] = true;
        for (int i = 1; i < n; i++) {
            //j从大到小处理的话会出现重复for循环
            /*for (int j = 0; j <= w ; j++) {
                //将当前物品重量加上上一行每一个符合状态的值小于w的设置为true
                if (status[j] == true && j+weight[i] <= w) status[j+weight[i]] = true;
            }*/

            for (int j = w - weight[i]; j >=0 ; j--) {
                if (status[j]) status[j+weight[i]] = true;
            }
        }
        for (int i = w; i >= 0 ; i--) {
            if (status[i]) return i;
        }
        return 0;
    }

}

