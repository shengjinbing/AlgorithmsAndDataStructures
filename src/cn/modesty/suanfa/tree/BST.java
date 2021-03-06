package cn.modesty.suanfa.tree;


import cn.modesty.suanfa.bagqueuestack.LinkQueue;

import java.util.*;

/**
 * 二叉搜索树
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        private int N;//以该节点为根的子树中的节点总数

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 查找一个元素
     *
     * @param x
     * @param key
     */
    public Value get(Node x, Key key) {
        //如果找不到就返回null
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            //往左找
            return get(x.right, key);
        } else if (cmp < 0) {
            //往右找
            return get(x.left, key);
        } else {
            return x.value;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    /**
     * put一个元素
     *
     * @param x
     * @param key
     * @param value
     */
    public Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);
        int cmp = x.key.compareTo(key);
        if (cmp > 0) {
            //放在树的左边
            x.left = put(x.left, key, value);
        } else if (cmp < 0) {
            //放在树的右边
            x.right = put(x.right, key, value);
        } else {
            //相等的话覆盖的
            x.value = value;
        }
        //左加右加根
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 删除一个元素
     * 1. 若删除的节点没有孩子的时候，直接删除此节点，然后将父节点NULL；
     * 2.若删除的节点有一个孩子的时候，直接将父节点连接到其孩子节点，然后删除此节点。
     * 3.若有两个节点的时候，这时候就要找个节点代替它了，因为二叉搜索树具有左子树比此节点的值都小，
     * 右子树比此节点的值大，所以
     * （1）可以找左子树中节点最大的元素，也就是左子树中最右端的元素，
     * （2）也可以找右子树中节点最小的元素，也就是右子树中最左端的节点。让此节点的值等于子树中选出的值，
     *     然后删除子树中的节点，因为被删除的节点不是在最右端就是在最左端，所以可知此节点只有一个孩子。。。。
     *     然后转到第二种情况。。
     *
     * @param key
     */
    public void delete(Key key) {
        if (root == null) return;
        root = delete(root, key);

    }

    public Node delete(Node x, Key key) {
        if (x == null) return null;
        int i = key.compareTo(x.key);
        if (i > 0) {
            x.right = delete(x.right, key);
        } else if (i < 0) {
            x.left = delete(x.left, key);
        } else {
            //找到结点，开始删除
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            //找到最小的结点
            x = min(x.right);
            //返回删除最小结点之后的根节点
            x.right = deleteMin(t.right);
            x.left = t.left;
            x.N = size(x.left) + size(x.right) + 1;
        }

        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    /**
     * 删除最小元素
     *
     * @param x
     * @return
     */
    public Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 最小键
     *
     * @return
     */
    public Key min() {
        if (root == null) return null;
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    /**
     * 最大键
     *
     * @return
     */
    public Key max() {
        if (root == null) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    /*
     * 小于等于key的最大键
     *
     * @param key
     * @return
     */
    public Key floor(Key key) {
        if (root == null) return null;
        return floor(root, key).key;
    }

    public Node floor(Node x, Key key) {
        if (x == null) return null;
        int i = key.compareTo(x.key);
        if (i > 0) {
            //找右结点
            Node t = floor(x.right, key);
            if (t != null) return t;
            else return x;
        } else if (i < 0) {
            //找左结点
            return floor(x.left, key);
        } else {
            return x;
        }
    }

    /**
     * 大于等于key的最小键
     *
     * @param key
     * @return
     */
   /* public Key celling(Key key){

    }*/

    /**
     * 小于key的键的数量
     *
     * @param key
     * @return
     */
   /* public int rank(Key key){

    }*/

    /**
     * 排名为k的键
     *
     * @param
     * @return
     */
  /*  public Key select(int k){

    }*/
    public void print(int state) {
        if (root == null) return;
        switch (state) {
            case 1:
                printPre(root);
                break;
            case 2:
                printMid(root);
                break;
            case 3:
                printLast(root);
                break;
            case 4:
                levelOrder(root);
                System.out.println(list.toString());
                break;
            case 5:
                System.out.println(levelOrderIteration(root));
                break;
        }

    }

    /**
     * 前序遍历
     *
     * @param x
     */
    public void printPre(Node x) {
        if (x == null) {
            return;
        }
        //前序遍历
        System.out.print(x.value + ",");
        printPre(x.left);
        printPre(x.right);

    }

    /**
     * 中序遍历
     *
     * @param x
     */
    public void printMid(Node x) {
        if (x == null) return;
        //中序遍历
        printMid(x.left);
        System.out.print(x.value + ",");
        printMid(x.right);

       /* //后序遍历
        print(x.left);
        print(x.right);
        System.out.print(x.value + ",");*/

    }

    /**
     * 后序遍历
     *
     * @param x
     */
    public void printLast(Node x) {
        if (x == null) return;
        //后序遍历
        printLast(x.left);
        printLast(x.right);
        System.out.print(x.value + ",");

    }


    /**
     * 二叉树的层次遍历（102，中等）
     *
     * @param
     */
    public List<List<Integer>> levelOrder(Node root) {
        levelOrderRecursion(root,0);
        return list;
    }
    List<List<Integer>> list = new LinkedList<List<Integer>>();

    /**
     * 递归法
     * @param root
     * @param level
     * @return
     */
    public void levelOrderRecursion(Node root,int level) {
        if (root == null) return;
        if (level >= list.size()){
            list.add(new ArrayList<>());
        }
        list.get(level).add((Integer) root.value);
        levelOrderRecursion(root.left,level+1);
        levelOrderRecursion(root.right,level+1);
    }

    /**
     * 迭代法
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderIteration(Node root) {
        List<List<Integer>> lists = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        //将根节点添加进来
        queue.add(root);
        while (queue.size() > 0){
            List<Integer> data = new ArrayList();
            //队列得大小
            int size = queue.size();
            while (size > 0){
                Node poll = queue.poll();
                data.add((Integer) poll.value);
                if (poll.left != null){
                    queue.add(poll.left);
                }

                if (poll.right != null){
                    queue.add(poll.right);
                }
                //每次循环需要
                size--;

            }
            lists.add(data);
        }
        return lists;
    }

    /**
     * 二叉树的最大深度(104)
     * 难度（简单）
     *
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int maxDepth(){
        return maxDepth(root);
    }

    public int maxDepth(Node x){
        //利用层级遍历来求最大深度，时间和空间复杂度过高
        /*if (x == null) return 0;
        int maxDepth = 0;
        Queue<Node> queue = new LinkedList<>();
        //将根节点添加进来
        queue.add(root);
        while (queue.size() > 0){
            //队列得大小
            int size = queue.size();
            while (size > 0){
                Node poll = queue.poll();
                if (poll.left != null){
                    queue.add(poll.left);
                }

                if (poll.right != null){
                    queue.add(poll.right);
                }
                //每次循环需要
                size--;
            }
            maxDepth++;
        }
        return maxDepth;*/

        //使用递归得方式
        if (x == null) return 0;
        int depth = 1;
        depth += Math.max(maxDepth(x.right),maxDepth(x.left));
        return depth;
    }

    /**
     * 翻转二叉树（226，简单）
     * 翻转一棵二叉树。
     * <p>
     * 示例：
     * <p>
     * 输入：
     * <p>
     * 4
     * /   \
     * 2     7
     * / \   / \
     * 1   3 6   9
     * 输出：
     * <p>
     * 4
     * /   \
     * 7     2
     * / \   / \
     * 9   6 3   1
     */
    public void invertTree() {
        invertTree(root);
    }


    public void invertTree(Node x) {
        if (x == null) return;
        Node temp = x.left;
        x.left = x.right;
        x.right = temp;
        invertTree(x.left);
        invertTree(x.right);
    }


    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new ArrayDeque<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
     * 二叉查找树的范围查找条件
     *
     * @param x
     * @param queue
     * @param lo
     * @param hi
     */
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);

    }
}
