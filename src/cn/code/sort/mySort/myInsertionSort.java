package cn.code.sort.mySort;

import cn.code.sort.Sort;

/**
 * 我的插入排序
 * 每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左侧数组依然有序。
 */
public class myInsertionSort  <T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        for(int i = 0 ;i<N;i++){
            for(int j = i ;j>0&&less(nums[j],nums[j-1]);j--){
                swap(nums,j,j-1);
            }
        }
    }
}
