package cn.code.sort;

public class myQuickSort {
    public void quickSort(int arr[], int left , int right){
       if(left<right) {
           deal(arr, left, right);
           int p = right - 1;
           int i = left;
           int j = right - 1;
           while (true) {
               while (arr[++i] < arr[p]) {

               }
               while (arr[--j] > arr[p] && j > left) {
                   //当找到一个值小于枢纽值的数字跳出循环
               }
               if (i < j) {
                   //还没有发生碰撞,可以进行交换
                   swap(arr, i, j);
               } else {
                   break;
               }
           }
           //完成所有交换后
           if(i<right){
               swap(arr,i,right-1);
           }
           quickSort(arr,left,i-1);
           quickSort(arr,i+1,right);
       }
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
