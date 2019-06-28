package cn.modesty.suanfa.backtracking;

/**
 * 0-1背包问题，每个物品的重量不等，并且不可分割
 */
public class Knapsack {
    private static int maxW = Integer.MIN_VALUE; // 存储背包中物品总重量的最大值
    private static int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private static int n = 5; // 物品个数
    private static int w = 9; // 背包承受的最大重量
    public static void f(int i, int cw) {
        if (cw == w || i == n) { // cw==w 表示装满了 ;i==n 表示已经考察完所有的物品
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i+1, cw);
        if (cw + weight[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            f(i+1,cw + weight[i]);
        }
    }

    public static void main(String[] args) {
        f(0,0);
        System.out.println(maxW);
    }

/*    private int maxW = Integer.MIN_VALUE; // 结果放到 maxW 中
    private int[] weight = {2，2，4，6，3};  // 物品重量
private int n = 5; // 物品个数
private int w = 9; // 背包承受的最大重量
private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值 false
public void f(int i, int cw) { // 调用 f(0, 0)
        if (cw == w || i == n) { // cw==w 表示装满了，i==n 表示物品都考察完了
        if (cw > maxW) maxW = cw;
        return;
        }
        if (mem[i][cw]) return; // 重复状态
        mem[i][cw] = true; // 记录 (i, cw) 这个状态
        f(i+1, cw); // 选择不装第 i 个物品
        if (cw + weight[i] <= w) {
        f(i+1,cw + weight[i]); // 选择装第 i 个物品
        }
        }*/


        }
