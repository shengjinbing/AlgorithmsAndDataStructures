package cn.modesty.suanfa.practice;


/**
 * 实现 strStr() 函数(28)
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 简单
 */
public class StrStr {
    public static void main(String[] args) {
        System.out.println(strStr("aaaaa", "bba"));

    }

    public static int strStr(String haystack, String needle) {
        if(haystack == null || needle == null || needle.length() == 0)return -1;
        if(needle == "")return 0;
        for(int i = 0;i< haystack.length()-needle.length() +1;i++){
            int n = i;
            for(int j = 0;j<needle.length();j++){
                if(haystack.charAt(n) == needle.charAt(j)){
                    n++;
                }else{
                    break;
                }
            }
            if(n - i == needle.length()) return i;
        }
        return -1;

    }
}