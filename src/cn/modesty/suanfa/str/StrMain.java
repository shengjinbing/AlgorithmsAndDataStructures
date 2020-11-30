package cn.modesty.suanfa.str;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 字符串相关算法
 */
public class StrMain {
    public static void main(String[] args) {
        //System.out.println(reverse(-12300));
        //System.out.println(isAnagram("aacc", "ccaa"));

        //System.out.println(isPalindrome("0P"));
        System.out.println(strStr("mississippi","issi"));
    }
    public static int strStr(String haystack, String needle) {
        if(haystack == null || needle == null) return -1;
        if (haystack.length() < needle.length()) return -1;
        if(needle.length() == 0) return 0;
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()){
            if(haystack.charAt(i) == needle.charAt(j)){
                if (j == needle.length() -1)return i-j;
                i++;
                j++;
            }else {
                i = i - j + 1;
                j=0;
            }
        }
        return -1;
    }
    /**
     * 验证回文数
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        /*if (s == null || s.length() == 0) return true;
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char st = s.charAt(i);
            char e = s.charAt(j);
            if (!((0 <= st && st <= 9) ||
                    ('a' <= st && st <= 'z') ||
                    ('A' <= st && st <= 'Z'))) {
                i++;
                continue;
            }
            if (!((0 <= e && e <= 9)
                    || ('a' <= e && e <= 'z')
                    || ('A' <= e && e <= 'Z'))) {
                j--;
                continue;
            }

            //这里两个字母也可能差32
            if (st == e || Character.toLowerCase(st) == Character.toLowerCase(e)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;*/

        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

    public static boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        return false;

        //速度很慢的解法
        /*Map<Integer,Integer> map = new HashMap();
        for(int i = 0;i < s.length();i++){
            int v = s.charAt(i) - 'a';
            map.put(v,map.getOrDefault(v,0)+1);
        }

        for(int i = 0;i < t.length();i++){
            int v = t.charAt(i) - 'a';
            if (map.containsKey(v)) {
                int count = map.get(v);
                if (count- 1 == 0){
                    map.remove(v);
                }else {
                    map.put(v,count-1);
                }
            }
        }
        return map.isEmpty();*/
    }


    /**
     * 字符串中的第一个唯一字符
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     * <p>
     * 示例：
     * s = "leetcode"
     * 返回 0
     * <p>
     * s = "loveleetcode"
     * 返回 2
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        int[] result = new int[26];
        for (int i = 0; i < s.length(); i++) {
            result[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (result[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * <p>
     * 示例 1:
     * 输入: 123
     * 输出: 321
     * <p>
     *  示例 2:
     * 输入: -123
     * 输出: -321
     * <p>
     * 示例 3:
     * 输入: 120
     * 输出: 21
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int y = 0;
        while (x != 0) {
            if (y > 214748364 || y < -214748364) {
                return 0;
            }
            y = y * 10 + x % 10;
            x = x / 10;
        }
        return y;
    }
}
