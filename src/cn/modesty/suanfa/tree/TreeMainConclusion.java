package cn.modesty.suanfa.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *树的金典题型
 */
public class TreeMainConclusion {
    public static void main(String[] args) {
        //letterCombinations("23");
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(8);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(9);
        root.left = node1;
        node1.left = node2;
        node1.right = node3;
        root.right = node4;
        node4.left = node5;
        node4.right = node6;

        TreeMainConclusion main = new TreeMainConclusion();
        System.out.println(main.maxDepth(root));
    }




    /**
     * 二叉树的直径（重点）543
     * 直径就等于当前节点的左边
     * @param root
     * @return
     */
    int dans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        _diameterOfBinaryTree(root);
        return dans;
    }
    public int _diameterOfBinaryTree(TreeNode root){
        if(root == null){
            return 0;
        }
        int l = _diameterOfBinaryTree(root.left);
        int r = _diameterOfBinaryTree(root.right);
        dans = Math.max(dans,l+r);
        return Math.max(l,r)+1;
    }

    public int maxDepth(TreeNode root) {
        maxDepth1(root,1);
        return res;
    }
    /**
     * 树的最大深度(重点)
     * 1.“自顶向下” 的解决方案
     *  “自顶向下” 意味着在每个递归层级我们将首先访问节点来计算一些值，并在递归调用函数时将这些值传递到子节点。
     *   所以 “自顶向下” 的解决方案可以被认为是一种前序遍历
     */
    int res = 0;
    public void maxDepth1(TreeNode node,int depth) {
        if (node == null)return;
        if (node.left == null && node.right == null){
            //叶子节点
            res = Math.max(res,depth);
        }
        maxDepth1(node.left,depth+1);
        maxDepth1(node.right,depth+1);
    }

     /* 2.“自底向上” 的解决方案
     * “自底向上” 是另一种递归方法。 在每个递归层次上，我们首先对所有子节点递归地调用函数，然后根据返回值和根节
     * 点本身的值得到答案。 这个过程可以看作是后序遍历的一种
     */
    public int maxDepth2(TreeNode node) {
        if (node == null)return 0;
        int left_depth = maxDepth2(node.left);
        int right_depth = maxDepth2(node.right);
        return Math.max(left_depth,right_depth)+1;
    }

    /**
     * 电话号码的字母组合
     */
    public static List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }


    static int n = 0;
    static int ans = 0;
    /**
     * 二叉搜索树中第K小的元素
     */
    public static int kthSmallest(TreeNode root, int k) {
        search(root, k);
        return ans;
    }

    private static void search(TreeNode node, int k) {
        if (node.left != null) {
            search(node.left, k);
        }
        n++;
        if (n == k) {
            ans = node.val;
        } else if (n > k) {
            return;//剪枝
        }
        if (node.right != null) {
            search(node.right, k);
        }
    }
}
