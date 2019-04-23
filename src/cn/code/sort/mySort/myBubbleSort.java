package cn.code.sort.mySort;

import cn.code.sort.Sort;

/**
 * 我的冒泡排序
 * 从左到右不断交换相邻逆序的元素，在一轮的循环之后，可以让未排序的最大元素上浮到右侧。不断缩小右边界
 * 在一轮循环中，如果没有发生交换，就说明数组已经是有序的，此时可以直接退出。
 *
 */
public class myBubbleSort <T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        boolean hasSort = false;
        for(int i = N-1;i>0&&!hasSort;i--){
            hasSort =true;
            for(int j = 0;j<i;j++){
                if(less(nums[j+1],nums[j])){
                    swap(nums,j+1,j);
                    hasSort = false;
                }
            }
        }
    }
}
