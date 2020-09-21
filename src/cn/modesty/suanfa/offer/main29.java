package cn.modesty.suanfa.offer;

/**
 * 顺时针打印矩阵
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 */
public class main29 {
    public static void main(String[] args) {
      /*  int [][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };*/
        int [][] matrix = {
                {1}
        };
        int[] ints = spiralOrder(matrix);

    }

    public static int[] spiralOrder(int[][] matrix) {
        if(matrix == null||matrix.length == 0) return new int[0];
        int[] data = new int[matrix.length* matrix[0].length];

        int up = 0;
        int down = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;

        int index = 0;

        while (true){
            //从左往右
            for (int i = left; i <= right; i++) {
                data[index] = matrix[up][i];
                index++;
            }
            if (++up > down) break;

            //从上往下
            for (int i = up; i <= down; i++) {
                data[index] =  matrix[i][right];
                index++;
            }
            if (--right < left ) break;

            //从右往左
            for (int i = right; i >= left ; i--) {
                data[index] = matrix[down][i];
                index++;
            }
            if (--down < up) break;

            //从下往上
            for (int i = down; i >= up ; i--) {
                data[index] = matrix[i][left];
                index++;
            }
            if (++left > right)break;
        }

        return  data;
    }
}
