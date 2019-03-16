package cn.code.sort;


public class selectSort {
    public static void main(String[] args) {
        int arr[] ={8,9,7,6,3,1,10};
        bubbleSort(arr);
        for(int i :arr){
            System.out.println(i);
        }
    }

    /**
     * 选择排序
     * 简单选择排序是最简单直观的一种算法，
     * 基本思想为每一趟从待排序的数据元素中选择最小（或最大）的一个元素作为首元素，
     * 直到所有元素排完为止，简单选择排序是不稳定排序
     * 在这种排序方法中,要比较的次数是不变的,交换次数在最优正序情况下为0,最差情况为n-1次,时间复杂度为O(n2)
     * 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，
     * 所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。
     * @param arr 要排序的数组
     */
    public static void selectSort(int[] arr){
        //
        for(int i =0;i<arr.length-1;i++){
            int min = i ;//每次进行一次循环比较的时候,min用来存放这次循环中最小的数的下标.
            for(int j = i+1 ; j<arr.length;j++){
                if(arr[j]<arr[min]){
                    min = j ;
                }
            }
            if(min!=i){
                swap(arr,min,i);//循环结束,若存在比当前第一位更小的数,将两者进行交换.
            }
        }
    }

    /**
     * 冒泡排序
     * 对相邻的元素进行两两比较，顺序相反则进行交换，这样，每一趟会将最小或最大的元素“浮”到顶端，最终达到完全有序
     *  若数组本身有序,仅仅需要n-1次比较即可完成;若果是倒序,则需要n(n-1)/2次.交换次数等于比较次数.时间复杂度为O(n2）
     * @param arr
     */
    public static void bubbleSort(int arr[]){
        for(int i = 0;i<arr.length-1;i++){
            boolean flag = true ;//一个标记,若一轮循环下来,没有发生任何交换,则说明此时数组的数据顺序已经是正序,无须再次进行循环
            for(int j = 0 ;j<arr.length-1;j++){
                if(arr[j]>arr[j+1]){//相邻的两个元素进行比较,如果反序则进行位置交换
                    swap(arr,j,j+1);
                    flag = false;
                }
            }

        }
    }

    /**
     * 插入排序
     * 直接插入排序基本思想是每一步将一个待排序的记录，插入到前面已经排好序的有序序列中去，直到插完所有元素为止。
     * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
     * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
     * @param arr
     */
    public static void insertSort(int arr[]){
        for(int i = 1 ;i<arr.length;i++){
            int j = i ;//限制当前已经排好序的矛点
            while(j>0&&arr[j]<arr[j-1]){//新插入了数据 进行排序
                swap(arr,j,j-1);
                j--;
            }
        }
    }

    /**
     * 实现了数组的两个数据之间的交换..
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }
}

