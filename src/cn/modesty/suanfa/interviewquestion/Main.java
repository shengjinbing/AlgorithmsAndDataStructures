package cn.modesty.suanfa.interviewquestion;

import cn.modesty.suanfa.tree.TreeNode;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

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
        System.out.println(main.kthSmallest(new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        }, 8));
        Stack<Integer> stack = new Stack();
        System.out.println(main.calculate(" 3+5 / 2 "));
        System.out.println((int)'0');
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack();
        for(int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if (c == ' ')continue;
            int pre = 0;
            if (!stack.isEmpty()) {
                pre = stack.peek();
            }
            if (pre == '*' - '0'){
                stack.pop();
                int temp = stack.pop() * (c - '0');
                stack.push(temp);
            }else if (pre== '/' - '0'){
                stack.pop();
                int temp = stack.pop() / (c - '0');
                stack.push(temp);
            }else {
                stack.push(c - '0');
            }
        }
        while(stack.size() != 1){
            int e = stack.pop();
            int m = stack.pop();
            int k = stack.pop();
            if(m == '+' - '0'){
                stack.push(e+k);
            }else if(m == '-' - '0'){
                stack.push(k-e);
            }
        }
        return stack.pop();
    }
    /**
     * 有序矩阵中第K小的元素
     * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
     * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
     * 示例：
     * matrix = [
     *    [ 1,  5,  9],
     *    [10, 11, 13],
     *    [12, 13, 15]
     * ],
     * k = 8,
     *
     * 返回 13。
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int s = matrix[0][0];
        int e = matrix[n-1][n-1];
        while (s < e){
            int mid = s+(e -s)/2;
            if (check(matrix,mid,k,n)){
                e = mid;
            }else {
                s = mid+1;
            }
        }
        return s;
    }

    public boolean check(int[][] matrix,int mid ,int k,int n){
        int i = n - 1;//从底部开始遍历
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;//重点：每次加上当前列的总数
                j++;
            } else {
                i--;
            }
        }
        return num >= k;

    }

    /**
     * 快乐数
     * 编写一个算法来判断一个数 n 是不是快乐数。
     *
     * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
     *
     * 如果 n 是快乐数就返回 True ；不是，则返回 False
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet();
        int sum = 0;
        while(sum != 1){
            while(n !=  0){
                int m  = n % 10;
                n = n / 10;
                sum+=m*m;
            }
            n = sum;
            if (set.contains(sum)) {
                return false;
            }
            set.add(sum);
            if(sum == 1)return true;
            sum = 0;
        }
        return false;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(k,new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            queue.offer(new int[]{num, count});
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

    /**
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0)return false;
        if (word == null || word.length() == 0) return true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ( dfs(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int i, int j, int index) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length ||
                board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        char temp = board[i][j];
        board[i][j] = '.'; //访问过了
        boolean result = dfs(board, word, i - 1, j, index + 1) ||
                dfs(board, word, i + 1, j, index + 1) ||
                dfs(board, word, i, j - 1, index + 1) ||
                dfs(board, word, i, j + 1, index + 1);
        board[i][j] = temp;//恢复数据
        return result;
    }


    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    /**
     * 子集
     *
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
