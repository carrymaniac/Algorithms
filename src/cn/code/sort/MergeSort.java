package cn.code.sort;

import java.util.Arrays;

/**
 * 归并排序
 * step1.分,先将数组分为两个子序列,先将两个子序列进行排序
 * step2.治,两个子序列进行比较,若a[i]<b[j],将a[i]放进temp数组中,同时将i++,
 * 这样迭代下去可以获得一个有序的temp数组,再将temp中的内容拷贝到原数组中
 */
public class MergeSort {
    public static void main(String[] args) {
        int []arr = {9,8,7,6,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int arr[]){
        int[] temp = new int[arr.length];//建立一个temp数组以便于暂时存储数据
        sort(arr,0,arr.length-1,temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if(left<right){
            int mid =(left+right)/2;
            sort(arr,left,mid,temp);//左边进行归并排序
            sort(arr,mid+1,right,temp);//右边进行归并排序
            merge(arr,left,mid,right,temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
            int i = left;//左序列指针
            int j = mid+1 ;//右序列指针
            int t = 0 ;//临时数组指针
            while(i<=mid && j<=right){
                if(arr[i]<=arr[j]){
                    temp[t++] = arr[i++];
                }else {
                    temp[t++] = arr[j++];
                }
            }
            while(i<=mid){//将左边的剩余元素填充进temp中
                temp[t++] = arr[i++];
            }
            while(j<=right){//将右边的剩余元素填充进temp中
                temp[t++] = arr[j++];
            }
            t = 0 ;
            while(left<=right){
                arr[left++] = temp[t++];
            }

    }
}
