package cn.code.sort.mySort;

import cn.code.sort.Sort;

/**
 * 希尔排序
 * 是针对插入排序的一种改进
 * 对于大规模的数组，插入排序很慢，因为它只能交换相邻的元素，每次只能将逆序数量减少 1。
 * 希尔排序的出现就是为了解决插入排序的这种局限性，它通过交换不相邻的元素，每次可以将逆序数量减少大于 1。
 * 希尔排序使用插入排序对间隔 h 的序列进行排序。通过不断减小 h，最后令 h=1，就可以使得整个数组是有序的。
 * @param <T>
 */
public class myShellSort <T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        int N = nums.length;
        int H = 1 ;
        //算一个分割值
        while(H<N/3){
            H = H*3+1; //1,4,13
        }
        while (H>1){
            for(int i = H;i<N;i++){
                for(int j = i;j>=H&&less(nums[j],nums[j-H]);j-=H){
                    swap(nums,j,j-H);
                }
            }
            //每做完一次,对H进行减小,当H为1时,数组基本有序,此时进行的就是插入排序
            H=H/3;
        }
    }
}
