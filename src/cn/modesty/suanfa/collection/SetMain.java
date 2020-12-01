package cn.modesty.suanfa.collection;

import java.util.HashSet;
import java.util.Set;

public class SetMain {
    public static void main(String[] args) {
        //System.out.println(lengthOfLongestSubstring("aab"));
    }

    /**
     * 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        HashSet<Character> set = new HashSet();
        int max = 0;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                if (max < set.size()) {
                    max = set.size();
                }
                set.clear();
                i = ++index;
                set.add(s.charAt(i));
            } else {
                set.add(c);
            }
        }
        return max > set.size() ? max : set.size();
    }
}
