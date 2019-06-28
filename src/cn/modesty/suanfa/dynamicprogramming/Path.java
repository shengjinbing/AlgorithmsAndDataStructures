package cn.modesty.suanfa.dynamicprogramming;

/**
 * 路径问题
 */
public class Path {

    private int[][] status = {{1,3,5,9},{2,1,3,4},{5,2,6,7},{6,8,4,3}};

    public static void main(String[] args) {

    }


    /**
     * 问题描述：棋子起步在左上角，结束在右下角，我们将棋子从左上角移到右下角每次只能向下或向右移动一位，
     *         求最短路径。
     */
    private int getMatrixMinPath(){
        int minPath = status[0][0];
        //遍历每一行
        for (int i = 0; i < status.length; i++) {
            //遍历每一列
            for (int j = 0; j < status[i].length; j++) {

            }

        }

        return 0;
    }
}

