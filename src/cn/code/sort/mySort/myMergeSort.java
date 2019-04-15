package cn.code.sort.mySort;

import cn.code.sort.Sort;

/**
 * 我的归并排序练习
 * @param <T>
 */
public class myMergeSort<T extends Comparable<T>> extends Sort<T> {
    protected T[] arx;
    protected void merge(T[] num,int left,int mid ,int right){
        int i = left;
        int j = mid+1;

        for(int k = left;k<=right;k++){
            arx[k]=num[k];
        }
        for(int k=left;k<=right;k++){
            if(less(num[i],num[j])){
                num[k]=arx[i++];
            }else if(less(num[j],num[i])){
                num[k]=arx[j++];
            }else if(i>mid){
                num[k]=arx[j++];
            }else if(j>right){
                num[k]=arx[i++];
            }
        }
    }

    @Override
    public void sort(T[] nums) {
        arx = (T[]) new Comparable[nums.length];

    }

    protected void sort(T[] nums,int left,int right){
        if(right<=1){
            return;
        }
        int mid = left + (right-left)/2;
        sort(nums,1,mid);
        sort(nums,mid+1,right);
        merge(nums,left,mid,right);
    }

}
