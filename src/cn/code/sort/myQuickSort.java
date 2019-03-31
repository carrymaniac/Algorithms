package cn.code.sort;

import java.util.Arrays;

public class myQuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }

    public static void quickSort(int arr[], int left , int right) {
        if (left < right) {
            int mid = partition(arr,left,right);
            quickSort(arr, left, mid - 1);
            quickSort(arr, mid + 1, right);
        }
    }
       private static int partition(int[] number,int start,int end){
        int a = number[start];
        do{
            while (number[end]>=a&&start<end){
                end--;
            }
            //交换基准值和找到的end值位置
            if (start<end){
                number[start]=number[end];
            }
            //找到一个比基准值小的值,需要从左边找一个比基准值大的值
            while (number[start]<=a&&start<end){
                start++;
            }
            if (start<end){
                number[end]=number[start];
            }

        }while (start<end);
        number[start]=a;
        return start;
       }


    /**
     * 三数排序取中操作,先将这三个数字进行排列,然后取中间值与右边第二个数值进行交换
     * @param arr
     * @param left
     * @param right
     */
    public  static void deal(int arr[],int left ,int right){
        int mid = (left+right)/2;
        if(arr[left]>arr[mid]){
            swap(arr,left,mid);
        }
        if(arr[left]>arr[right]){
            swap(arr,left,right);
        }
        if(arr[mid]>arr[right]){
            swap(arr,mid,right);
        }
        swap(arr,mid,right-1);
    }



    /**
     * 通用交换方法
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp ;
    }
}
