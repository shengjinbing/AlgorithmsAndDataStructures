package cn.modesty.suanfa.tree;

/**
 * 1.根节点是黑色的
 * 2.每个叶子节点都是黑色的空节点（NIL），也就是说叶子节点不存储数据
 * 3.任何相邻得节点不能同时为红色，也就是说红色节点是被黑色节点隔开得
 * 4.每个节点，该节点到达叶子节点的所有路径中黑色节点相同
 *
 * 在插入和删除节点的过程中会破坏2，3条，所有需要平衡调整，也就是：
 *   左旋：围绕某个节点的左转
 *   右旋：围绕某个节点的右转
 *
 * 红黑树规定，插入得节点必须是红色的
 *
 *
 * 平衡二叉树:就是为了防止二叉搜索树变为线性数据结构,而出现的数据结构
 *  * 而AVL树-绝对平衡树.左右子树的高度差不能超过1
 *  * 红黑树:特性:
 *  * 1.每个结点不是红色就是黑色
 *  * 2.根节点:一定是黑色的
 *  * 3.不可能有两个红色的节点连在一起,每个叶子节点都是黑色的空节点(NIl),并且不存储数据
 *  * 4.每个节点,从该结点到达其可到达的叶子节点的所有路径,都包含相同树目的黑色节点
 *  * 为什么要用红黑树,
 *  * 三个操作:
 *  * 1.变色:
 *  * 2.左旋: 指针的变化
 *  * 3.右旋:指针的变化
 *  * 什么时候左旋?什么时候右旋呢?
 *  * 所有新加的点一定是红色
 *  * 红黑树建立的基础就是在二叉查找树的基础之上的.解决了二叉查找树的线性问题;进行平衡性;
 *
 */
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
     * @param h
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
