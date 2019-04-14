package cn.code.LeetCode.char1;

import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 *
 * 示例 1:
 *
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * 输出:
 * "apple"
 */
public class findLongestWord {
    public String findLongestWord(String s, List<String> d) {
        String longestWord = "";
        for (String target : d) {
            int l1 = longestWord.length(), l2 = target.length();
            //1.若现有的字符串比新的字符串长的话,直接跳过这一层for循环
            //2.若等长,但现有的字符串的字典集比新的字符串小的话,也直接跳过这一层for循环
            if (l1 > l2 || (l1 == l2 && longestWord.compareTo(target) < 0)) {
                continue;
            }
            //进行校验,若合法,则将其放在待选的字符串中.
            if (isValid(s, target)) {
                longestWord = target;
            }
        }
        return longestWord;
    }

    private boolean isValid(String s, String target) {
        int i = 0, j = 0;
        while (i < s.length() && j < target.length()) {
            if (s.charAt(i) == target.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == target.length();
    }

}
