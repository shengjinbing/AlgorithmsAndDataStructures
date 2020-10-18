package cn.modesty.suanfa.offer;

/**
 * 表示数值得字符串
 */
public class main20 {
    private static int i = 0;

    public static void main(String[] args) {
        System.out.println(isNumber("-1E-16"));
    }


    public static boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        char[] c = s.toCharArray();
        //第一部分整数部分扫描
        boolean numeric = scanInteger(c);

        //第二部分小数部分
        if (c[i] == '.') {
            i++;
            //下面一行代码用||得原因
            //1.下数可以没有整数部分.123等与123
            //2.小数点后面可以没有数字，如233.等于233.0
            //3.当然小数点后面和前面都可以有数据233.666
            numeric = scanUnsignedInteger(c) || numeric;
        }

        //第三部分，判断指数部分E或者e
        if (c[i] == 'e' || c[i] == 'E') {
            i++;
            numeric = numeric && scanInteger(c);
        }

        return numeric && i == c.length;
    }

    public static boolean scanInteger(char[] c) {
        if (c[i] == '+' || c[i] == '-') {
            i++;
        }
        return scanUnsignedInteger(c);
    }

    public static boolean scanUnsignedInteger(char[] c) {
        //遍历整数0-9
        int before = i;
        while (i < c.length && c[i] >= '0' && c[i] <= '9') {
            i++;
        }
        //当str中存在若干0-9得数字时，返回true；
        return i > before;
    }
}
