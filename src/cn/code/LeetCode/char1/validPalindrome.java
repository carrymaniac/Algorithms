package cn.code.LeetCode.char1;

/**
 * 680. 验证回文字符串 Ⅱ
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 *
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 *
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 */
public class validPalindrome {
    public boolean validPalindrome(String s) {
        int i=-1;
        int j=s.length();
        while (i++<j--){
            if(s.charAt(i)!=s.charAt(j)){
                //出现一个不相等,尝试跳过一位继续进行
                return isPalindrome(s,i+1,j)||isPalindrome(s,i,j-1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String str ,int i ,int j){
        while (i<j){
            if(str.charAt(i++)!=str.charAt(j--)){
                return false;
            }
        }
        return true;
    }
}
