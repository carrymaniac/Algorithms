package cn.code.LeetCode.char1;

/**
 * 题目描述：判断一个数是否为两个数的平方和。
 * Input: 5
 * Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 * 从头和尾两端进行尝试
 */
public class judgeSquareSum {

    public boolean judgeSquareSum(int c) {
        int i = 0;
        int j = (int) Math.sqrt(c);
        while (i<=j){
            int sum = i*i +j*j;
            if(sum==c){
                return true;
            }else if(sum>c){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }
}