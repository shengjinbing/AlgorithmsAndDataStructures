package cn.modesty.suanfa.tree;


import java.util.logging.Level;

public class RedBlackBST<Key extends Comparable<Key>,Value>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public Node getRoot() {
        return root;
    }

    private Node root;
    private class Node{
        Node left,right;
        boolean color;
        Key key;
        Value value;
        int N;

        public Node(Key key, Value value,int N,boolean color) {
            this.color = color;
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }
    // return number of key-value pairs in BST rooted at x
    public int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }
    /**
     * 判断
     *
     * @param x
     * @return
     */
    private boolean isRed(Node x){
        if (x == null)return false;
        return x.color == RED;
    }

    /**
     * 将右边的红线旋转到左边
     *
     * @param h
     * @return
     */
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        return x;
    }

    /**
     * 将左边的红线旋转到右边
     *
     * @param h
     * @return
     */
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        return x;
    }

    /**
     * 两个子节点都是红色，变成黑色，根节点变成红尘
     *
     * @param x
     */
    private void flipColors(Node h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void put(Key key,Value value){
        root = put(root,key,value);
        //根节点一直为黑色
        root.color = BLACK;
    }

    /**
     * 添加元素
     *
     * @param x
     * @param key
     * @param value
     * @return
     */
    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key,value,1,RED);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right=put(x.right,key,value);
        else if (cmp < 0)x.left = put(x.left,key,value);
        else x.value = value;

        if (isRed(x.right) && !isRed(x.left))x=rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x=rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);

        x.N = 1 + size(x.left)+size(x.right);
        return x;
    }
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
}
