package cn.modesty.suanfa.offer;

/**
 * 回溯法
 * 矩阵中找到一条路径
 */
public class main13 {
    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'a', 'b', 't', 'g'},
                {'c', 'f', 'c', 's'},
                {'j', 'd', 'e', 'h'}
        };
        char[] chars = {'b','f','c','e'};
    }

    private static boolean hasPath(char[][] matrix,int rows,int cols,char[] str ){
        if (matrix == null || rows<1 || cols <1 || str == null){
            return false;
        }
        
        return true;
    }
}
