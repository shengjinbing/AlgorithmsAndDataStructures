package cn.modesty.suanfa.interviewquestion;

import cn.modesty.suanfa.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收集面试题中出现的算法
 * 1.二叉树输出第 k 层节点元素
 * 2.两个有序的链表的合并
 * 3.输入一个字符串(不含和.)、正则(字母、和.任意组合)，判断字符串是否合法
 * 4.求一个整数的二进制中 1 的个数
 * 5.不知道头结点的链表，删除指定结点
 * 6.字符串找首位最大重复子串
 * 7.二叉树的右视图(199)
 */
public class Main {
    public static void main(String[] args) {
        List<String> list = generateParenthesis(3);
        Main main = new Main();
        List<List<Integer>> subsets = main.subsets(new int[]{1, 2, 3});

        char[][] board =
                {
                       {'A', 'B', 'C', 'E'},
                       {'S', 'F', 'C', 'S'},
                       {'A', 'D', 'E', 'E'}
               };

       String word = "ABCCED";
       System.out.println(main.exist(board, word));


    }
    boolean isExist = false;
    public boolean exist(char[][] board, String word) {

        for(int i = 0;i < board.length;i++){
            for(int j = 0; j < board[0][j];j++){
                dfs(board,word,new StringBuffer(),i,j);
            }
        }
        return isExist;
    }
    public void dfs(char[][] board, String word,StringBuffer sb,int i,int j){
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length){
            return;
        }
        if(sb.length() == word.length() && word.equals(sb.toString())){
            isExist = true;
            return;
        }
        sb.append(board[i][j]);
        dfs(board,word,sb,i-1,j);
        sb.deleteCharAt(sb.length() -1);
        dfs(board,word,sb,i+1,j);
        sb.deleteCharAt(sb.length() -1);
        dfs(board,word,sb,i,j-1);
        sb.deleteCharAt(sb.length() -1);
        dfs(board,word,sb,i,j+1);
        sb.deleteCharAt(sb.length() -1);
    }


    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    /**
     * 子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }


    /**
     * 括号生成（22）
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 示例：
     * 输入：n = 3
     * 输出：[
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     */
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        _generateParenthesis(list, "", n, n);
        return list;
    }

    public static void _generateParenthesis(List<String> list, String cur, int left, int right) {
        if (left == 0 && right == 0) {
            list.add(cur);
            return;
        }
        //先放左边
        if (left > 0) {
            _generateParenthesis(list, cur + "(", left - 1, right);
        }
        if (left < right) {
            _generateParenthesis(list, cur + ")", left, right - 1);
        }
    }

    /**
     * 树的最小深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (right == 0 || left == 0) ? left + right + 1 : Math.min(right, left) + 1;

    }
}
