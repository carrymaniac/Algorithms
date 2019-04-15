package cn.code.sort.mySort;

import cn.code.sort.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 我的快速排列
 * @param <T>
 */
public class myQuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        shuffle(nums);
        sort(nums,0,nums.length-1);
    }

    private void sort(T[] nums, int left, int right) {
        if(right<=1){
            return;
        }
        int j = partition(nums,left,right);
        sort(nums,left,j-1);
        sort(nums,j+1,right);
    }


    /**
     * 打乱数组
     * @param nums
     */
    private void shuffle(T[] nums){
        List<Comparable> comparables = Arrays.asList(nums);
        Collections.shuffle(comparables);
        comparables.toArray(nums);
    }

    protected int partition(T nums[],int left,int right){
        int i =left;
        int j = right;
        T t = nums[left];
        while (true){
            while (less(nums[++i],t)&&i!=right);
            while (less(t,nums[j--])&&j!=left);
            if(i>=j){
                break;
            }
            swap(nums,i,j);
        }
        swap(nums,left,j);
        return j;
    }
}
