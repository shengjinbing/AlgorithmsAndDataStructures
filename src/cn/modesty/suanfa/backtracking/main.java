package cn.modesty.suanfa.backtracking;

import java.util.*;

/**
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247485097&idx=1&sn=a5e82da8646cd8985de6b2b0950de4e2&chksm=9bd7f8a1aca071b7b72e23013bc2a7c528ee913fded9278e2058bc98d7c746e439737d7abb5b&scene=178&cur_album_id=1318883740306948097#rd
 * 元素不同
 * result = []
 * def backtrack(路径, 选择列表):
 *     if 满足结束条件:
 *         result.add(路径)
 *         return
 *
 *     for 选择 in 选择列表:
 *         做选择
 *         backtrack(路径, 选择列表)
 *         撤销选择
 */
public class main {
    public static void main(String[] args) {
        //EightQueens.cal8queens(0);
        // EightQueens.put8queens(0);
        main main = new main();
        String[] abcs = main.permutation("abc");
        for (int i = 0; i < abcs.length; i++) {
            System.out.println(abcs[i]);
        }
    }



    /**
     * 和为K的子数组（前缀合）
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     * 示例 1 :
     * 输入:nums = [1,1,1], k = 2
     * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
     *
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        int ran = 0;
        int sum = 0;
        map.put(0,1);
        for(int i = 0;i<nums.length;i++){
            sum+=nums[i];
            int v = sum - k;
            if(map.containsKey(v)){
                ran+= map.get(v);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return ran;
    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。（重点）
     * 示例：
     * 输入：n = 3
     * 输出：[
     *        "((()))",
     *        "(()())",
     *        "(())()",
     *        "()(())",
     *        "()()()"
     *      ]
     */
    public List<String> generateParenthesis(int n) {
        List<String> data = new ArrayList();
        _gen("",data,n,n);
        return data;
    }

    public void _gen(String result,List<String> data ,int left,int right) {
        if(left == 0  && right == 0){
            data.add(result);
            return;
        }
        if(left > 0){
            _gen(result+"(",data,left - 1,right);
        }
        if(right > left){
            _gen(result+")",data,left,right -1);
        }

    }
    /*public List<String> generateParenthesis(int n) {
        List<String> data = new ArrayList();
        _gen(new StringBuilder(""),data,n,n);
        return data;
    }

    public void _gen(StringBuilder result,List<String> data ,int left,int right) {
        // 若左括号剩下的多，说明不合法
        if (right < left) return;
        // 数量小于 0 肯定是不合法的
        if (left < 0 || right < 0) return;
        if(left == 0  && right == 0){
            data.add(result.toString());
            return;
        }

        result.append("(");
        _gen(result,data,left - 1,right);
        result.deleteCharAt(result.toString().length()-1);

        result.append(")");
        _gen(result,data,left,right -1);
        result.deleteCharAt(result.toString().length()-1);
    }*/
    /**
     * 子集(重点)
     * @param nums
     * @return
     */
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> t = new ArrayList<Integer>();
        dfs(0, nums,t);
        return ans;
    }

    public void dfs(int cur, int[] nums,List<Integer> t) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums,t);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums,t);
    }

    /**
     * 字符串全排列（重点）
     */
    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation(String s) {
        c = s.toCharArray();
        _permutation(0);
        return res.toArray(new String[res.size()]);
    }

    public void _permutation(int x) {
        if (x == c.length - 1) {
            res.add(String.valueOf(c)); // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x); // 交换，将 c[i] 固定在第 x 位
            _permutation(x + 1); // 开启固定第 x + 1 位字符
            swap(i, x); // 恢复交换
        }
    }

    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }


}
