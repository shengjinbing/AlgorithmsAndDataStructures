package cn.modesty.suanfa.mathematics;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 数学相关的算法
 */
public class MathematicsMain {
    public static void main(String[] args) {
        //System.out.println(countPrimes(2));
        //System.out.println(hammingWeight(255));
        //异或不相同为1相同为0
        System.out.println(hammingDistance(1, 4));
        List<List<Integer>> generate = generate(5);

    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for(int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if (c == '(' || c == '{' ||c == '['){
                stack.push(c);
            }else {
                if (stack.size() == 0){
                    return  false;
                }
                char p = stack.peek();
                if(c == ')' && p== '('){
                    stack.pop();
                }else if( c == '}'&& p == '{'){
                    stack.pop();
                } else if( c == ']'&& p == '['){
                    stack.pop();
                }else {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    /**
     * 杨辉三角
     * 输入: 5
     * 输出:
     * [
     *      [1],
     *     [1,1],
     *    [1,2,1],
     *   [1,3,3,1],
     *  [1,4,6,4,1]
     * ]
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> data= new ArrayList();
        if (numRows == 0) return data;
        List<Integer> data1 = new ArrayList();
        data1.add(1);
        data.add(data1);
        for(int i = 1;i < numRows;i++){
            List<Integer> tdata = new ArrayList();
            List<Integer> prdate = data.get(i - 1);
            for (int j = 0; j < prdate.size(); j++) {
                if (j == 0){
                    tdata.add(1);
                }else {
                    tdata.add(prdate.get(j)+prdate.get(j-1));
                }
            }
            tdata.add(1);
            data.add(tdata);

        }
        return data;
    }
    /**
     * 汉明距离
     * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
     * 给出两个整数 x 和 y，计算它们之间的汉明距离。
     * 注意：
     * 0 ≤ x, y < 231.
     * 示例:
     * 输入: x = 1, y = 4
     * 输出: 2
     * 解释:
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     * ↑   ↑
     * <p>
     * 上面的箭头指出了对应二进制位不同的位置。
     */
    public static int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            if ((xor & 1) == 1) {
                distance += 1;
            }
            xor = xor >> 1;
        }
        return distance;
    }

    /**
     * 位1的个数
     *
     * @param n
     * @return
     */
    public static int hammingWeight(int n) {
        int i = 1;
        int count = 0;
        while (i <= n) {
            if ((i & n) != 0) {
                count++;
            }
            i <<= 1;
        }
        return count;
    }

    /**
     * 计数质数(厄拉多塞筛法)
     * <p>
     * 统计所有小于非负整数 n 的质数的数量。
     * 示例 1：
     * <p>
     * 输入：n = 10
     * 输出：4
     * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     * 示例 2：
     * <p>
     * 输入：n = 0
     * 输出：0
     * 示例 3：
     * <p>
     * 输入：n = 1
     * 输出：0
     *
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        int count = 0;
        Boolean[] b = new Boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            b[i] = false;
        }
        for (int i = 2; i <= n; i++) {
            if (!b[i]) {
                count++;
                for (int j = i + i; j < n; j += i) {
                    b[j] = true;
                }
            }
        }
        return count;
    }
}
