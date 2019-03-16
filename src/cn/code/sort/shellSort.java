package cn.code.sort;

/**
 *   希尔排序
 * 　希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 *   随着增量逐渐减少，每组包含的关键词越来越多，
 *   当增量减至1时，整个文件恰被分成一组,算法便终止。
 *
 *   希尔排序中,增量序列的选择十分重要,希尔增量{n/2,(n/2)/2...1},其最坏的时间复杂度仍为O(n2),有些优化过的增量序列可以使得最坏时间度为O(n3/2)
 */
public class shellSort {
    public static void main(String[] args) {
        int arr[] ={8,9,1,7,2,3,5,4,6,0};
        ShellSort(arr);
        for(int i :arr){
            System.out.println(i);
        }
    }

    /**
     * 希尔算法:针对有序序列在插入时使用交换法
     *
     * @param arr
     */
    public static void ShellSort(int arr[]){
        for(int gap = arr.length/2;gap>0;gap/=2){
            //设置增量gap,并且逐步缩小gap
            for(int i=gap;i<arr.length;i++){
                int j = i ;
                while(j-gap>=0 && arr[j]<arr[j-gap]){//在分组中进行直接插入排序
                    swap(arr,j,j-gap);
                    j-=gap;
                }
            }
        }
    }

    /**
     * 希尔算法:针对有序序列在插入时采用移动法。
     * @param arr
     */
    public static void ShellSort1(int arr[]){
        for(int gap = arr.length/2;gap>0;gap/=2){
            //同上,逐渐缩小增量gap;
            for(int i = gap ;i<arr.length;i++){
                int j = i ;
                int temp = arr[i];
                if(arr[j]<arr[j-gap]){
                    while (j-gap>=0 && temp<arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
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
