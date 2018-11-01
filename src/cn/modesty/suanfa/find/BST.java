package cn.modesty.suanfa.find;


import java.util.ArrayDeque;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        Key   key;
        Value value;
        Node  left, right;
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
     * @param k
     * @return
     */
  /*  public Key select(int k){

    }*/
    public void print() {
        if (root == null) return;
        print(root);

    }

    public void print(Node x) {
        if (x == null) return;
        //前序遍历
        System.out.print(x.value + ",");
        print(x.left);
        print(x.right);

     /*   //中序遍历
        print(x.left);
        System.out.print(x.value + ",");
        print(x.right);*/

       /* //后序遍历
        print(x.left);
        print(x.right);
        System.out.print(x.value + ",");*/

    }

    /**
     * 删除一个元素
     *
     * @param key
     */
    public void delete(Key key) {
        if (root == null) return;
        root = delete(root,key);

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
            x.N = size(x.left)+size(x.right)+1;
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
        if (x.left == null)return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }

    public Iterable<Key> keys(){
        return keys(min(),max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new ArrayDeque<>();
        keys(root,queue,lo,hi);
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
