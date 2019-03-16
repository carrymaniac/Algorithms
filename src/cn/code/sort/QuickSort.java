package cn.code.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 步骤一.三数取中,选取3个数,首尾中,然后三个数进行排序,最后将中间的数换到最后一位数的前一位(也就是right-1).
 * 步骤二.分割序列,开始进行比较,有两个游标i和j,当i找到一个大于枢纽值的值,且当j找到一个小于枢纽值的值,两者进行交换
 * 重复以上的步骤二,直到i和j重叠碰撞.此时结束while循环,将
 *
 *
 *
 *
 * 快速排序是一种交换类的排序，它同样是分治法的经典体现。
 * 在一趟排序中将待排序的序列分割成两组，其中一部分记录的关键字均小于另一部分。
 * 然后分别对这两组继续进行排序，以使整个序列有序。
 * 在分割的过程中，枢纽值的选择至关重要
 * 采取三位取中法，可以很大程度上避免分组"一边倒"的情况。快速排序平均时间复杂度也为O(nlogn)级。
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }

    /**
     * 快排
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {
        if(left<right){
            //获取枢纽值,并将其放在当前待处理序列末尾
            dealPivot(arr,left,right);
            int pivot = right -1 ; //枢纽值位置
            int i = left ;
            int j = right-1 ;
            while(true){
                while (arr[++i]<arr[pivot]){
                    //当左边的数据小于枢纽值,无需操作 i自增一位即可
                }
                while(j>left&&arr[--j]>arr[pivot]){
                    //当j未和i相遇且右边的值大于枢纽值时,无需操作 , j自减一位即可
                }

                //此时情况为arr[i]的值大于枢纽值和arr[j];arr[j]的值小于枢纽值和arr[i],此时执行交换
                if (i < j) {
                    swap(arr, i, j);
                } else {
                    break;
                }
            }
            //最后完成的时候(即i和j碰撞的时候),需要将最右边一位和枢纽值对换,此时能够完成枢纽值的左边全部小于枢纽值,右边全部大于枢纽值
            if (i < right) {
                swap(arr, i, right - 1);
            }
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }


    //三数取中,选取3个数,首尾中,然后三个数进行排序,最后将中间的数换到最后一位数的前一位(也就是right-1).
    public static void dealPivot(int arr[],int left,int right){
        int mid = (left+right)/2;
        if(arr[left] > arr[mid]){
            swap(arr,left,mid);
        }
        if(arr[left]>arr[right]){
            swap(arr,left,right);
        }
        if(arr[right]<arr[mid]){
            swap(arr, right, mid);
        }
        swap(arr,right-1,mid);
    }

    /**
     * 交换元素通用处理
     *
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
