package cn.code.LeetCode.char1;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 反转原音字符
 * Given s = "leetcode", return "leotcede".
 * 同样的思路 头尾两个指针开始遍历
 */
public class reverseVowels {
    private final static HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public String reverseVowels(String str){
        int i = 0;
        int j =str.length();
        char[] result = new char[str.length()];
        while (i<j){
            char a = str.charAt(i);
            char b = str.charAt(j);
            if(!vowels.contains(a)){
                result[i]=a;
                i++;
            }else if(!vowels.contains(b)){
                result[j]=b;
                j--;
            }else if(vowels.contains(a)&&vowels.contains(b)){
                result[j]=a;
                result[i]=b;
                i++;
                j--;
            }
        }
        return result.toString();
    }
}
