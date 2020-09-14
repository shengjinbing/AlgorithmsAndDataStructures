package cn.modesty.suanfa.offer;

/**
 * 重构二叉树
 * 前序遍历{1，2，4，7，3，5，6，8}
 * 中序遍历{4，7，2，1，5，3，8，6}
 */
public class main7 {
    public static void main(String[] args) {
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] mid = {4,7,2,1,5,3,8,6};
        BinaryTreeNode root=reConstructBinaryTree(pre,0,pre.length-1,mid,0,mid.length-1);
    }

    private static BinaryTreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,
                                                          int [] mid,int startMid,int endMid) {

        if (startPre > endPre || startMid > endMid)return null;
        BinaryTreeNode root = new BinaryTreeNode(pre[startPre]);
        for (int i = startMid; i <= endMid; i++) {
            if (mid[i] == pre[startPre] ){
                //找到跟节点的位置将数组分成左子树和右子树
                root.left = reConstructBinaryTree(pre,startPre+1,startPre+i-startMid,mid,startMid,i-1);
                root.right = reConstructBinaryTree(pre,startPre+i-startMid+1,endPre,mid,i+1,endMid);
            }
        }
        return root;
    }

    public static class BinaryTreeNode{
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
        BinaryTreeNode(int value){
            this.value = value;
        }
    }
}
