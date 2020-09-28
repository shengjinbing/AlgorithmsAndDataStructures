package cn.modesty.suanfa.offer;

/**
 * 二叉搜索树与双向链表
 *
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，
 * 只能调整树中节点指针的指向
 */
public class main36 {
    Node head,pre;
    public static void main(String[] args) {

    }
    public Node treeToDoublyList(Node root) {
        //中序遍历有序
        if(root == null) return null;
        dfs(root);
        //现在前一个结点为遍历的最后一个结点
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void dfs(Node cur){
        if (cur == null) return;
        dfs(cur.left);
        if(pre != null) pre.right = cur;
        else head = cur;
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }


    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
