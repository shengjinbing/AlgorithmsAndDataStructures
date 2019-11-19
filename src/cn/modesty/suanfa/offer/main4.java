package cn.modesty.suanfa.offer;

/**
 * 二维数组中的查找：
 * 在一个二位数组中，每一行都按照从左到右的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个
 * 二位数组和一个整数，判断数组是否含有该整数。
 */
public class main4 {
    public static void main(String[] args) {
        int[][] a = new int[][]{{ 1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        System.out.print(find(a,a.length,a[0].length,10));
    }


    private static boolean find(int [][] a,int rows,int columns,int find){
        if (a == null && a.length == 0 ) return false;
        //从第一行最后一列开始找
        int row = 0;
        int column = columns-1;
        while (row < rows && column >= 0){
            if (a[row][column] < find){
                //先查找最后一列
                row++;
            }else if (a[row][column] > find){
                //不在最后一列,，向前找
            columns--;
        }else {
            return true;
        }
        }
        return false;

    }
}
