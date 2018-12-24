package cn.modesty.suanfa.huffman;

/**
 * 单词查找树的结点
 */
public class Node implements Comparable<Node> {
    private char ch;//内部结点不会使用该变量
    private int  freq;//展开过程不会使用该变量
    private final Node left ,right;

    public Node(char ch, int freq, Node left, Node right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf(){
        return left == null && right == null;
    }

    @Override

    public int compareTo(Node o) {
        return this.freq - o.freq;
    }


}
