package cn.modesty.suanfa.huffman;

/**
 * 霍夫曼编码（Huffman Coding）https://blog.csdn.net/xgf415/article/details/52628073
 * OkHttp Gzip压缩
 * 对传输的数据进行了压缩，那么传输效率肯定就增加了。
 * 其原理是对一个文件先进行ZL77算法压缩，再进行Huffman编码压缩。
 * ZL77：是在一段数据里依次找到多处相同的数据块，保留第一块的内容，用索引和大小来替换后面相同数据块的内容。
 * 霍夫曼编码(Huffman Coding): 使用变长编码表对源符号（如文件中的一个字母）进行编码，其中变长编码表是通过一种评估来源符号出现机率的方法得到的，
 * 出现机率高的字母使用较短的编码，出现机率低的则使用较长的编码，这便使编码之后的字符串的平均长度、期望值降低，从而达到无损压缩数据的目的。
 * 单词查找树的结点
 */
public class Node implements Comparable<Node> {
    private char ch;//内部结点不会使用该变量
    private int freq;//展开过程不会使用该变量
    private final Node left, right;

    public Node(char ch, int freq, Node left, Node right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override

    public int compareTo(Node o) {
        return this.freq - o.freq;
    }


}
