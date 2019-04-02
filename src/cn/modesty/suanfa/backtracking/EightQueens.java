package cn.modesty.suanfa.backtracking;

/**
 * 八皇后
 * 我们有一个8*8的棋盘，希望往里面放8个棋子（皇后），8个棋子所在的行，列，对角线都不能有另一个棋子。
 * 八皇后问题就是期望找出所有满足这种要求的放棋子方式
 */
public class EightQueens {
    private static int[] status = new int[8];//下标代表行，值代表列
    public static void put8queens(int row){
        if (row == 8){
            //第八行结束
            printQueens(status);
            return;
        }
        for (int column = 0; column < 8; column++) {
            if (isOkQueens(row,column)) {
                status[row] = column;
                //考察下一行，警记不能写成row++
                put8queens(row+1);
            }
        }

    }

    private static boolean isOkQueens(int row, int column) {
        int leftup = column - 1;
        int right = column + 1;
        //向上遍历每一行
        for (int i = row -1; i >= 0; i--) {
            //列上存在棋子，结束
            if (status[i] == column) return false;
            //左边对角线边界判断
            if (leftup >= 0){
                //左边对角线存在棋子
                if (status[i]== leftup) return false;
            }
            //右边对角线边界判断
            if (right < 8) {
                //右边对角线存在棋子
                if (status[i] == right) return false;
            }
            leftup--;
            right++;
        }
        return true;
    }


    /********************模板***********************/
    static int[] result = new int[8];// 全局或成员变量, 下标表示行, 值表示 queen 存储在哪一列
    public static void cal8queens(int row) { // 调用方式：cal8queens(0);
        if (row == 8) { // 8 个棋子都放置好了，打印结果
            printQueens(result);
            return; // 8 行棋子都放好了，已经没法再往下递归了，所以就 return
        }
        for (int column = 0; column < 8; ++column) { // 每一行都有 8 中放法
            if (isOk(row, column)) { // 有些放法不满足要求
                result[row] = column; // 第 row 行的棋子放到了 column 列
                cal8queens(row+1); // 考察下一行
            }
        }
    }

    private static boolean isOk(int row, int column) {// 判断 row 行 column 列放置是否合适
        int leftup = column - 1, rightup = column + 1;
        for (int i = row-1; i >= 0; --i) { // 逐行往上考察每一行
            if (result[i] == column) return false; // 第 i 行的 column 列有棋子吗？
            if (leftup >= 0) { // 考察左上对角线：第 i 行 leftup 列有棋子吗？
                if (result[i] == leftup) return false;
            }
            if (rightup < 8) { // 考察右上对角线：第 i 行 rightup 列有棋子吗？
                if (result[i] == rightup) return false;
            }
            --leftup; ++rightup;
        }
        return true;
    }

    private static void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
