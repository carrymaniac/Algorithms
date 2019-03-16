package cn.code.sort;

/**
 *  堆排序
 *  基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。
 *  将其与末尾元素进行交换，此时末尾就为最大值。
 *  然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。
 *  如此反复执行，便能得到一个有序序列了
 *
 */
public class HeapSort {
    public static void main(String[] args) {
        int arr[] ={8,9,7,6,3,1,10};
        sort(arr);
        for(int i :arr){
            System.out.println(i);
        }
    }

    public static void sort(int arr[]){
        //步骤一.构建大顶堆
        for(int i = arr.length/2-1;i>=0;i--){
            //从第一个非叶子节点从下而上,从右到左进行结构调整
            adjustHeap(arr,i,arr.length);
        }
        //步骤二.将最顶元素和尾元素进行交换,并重新构建大顶堆
        for (int j=arr.length-1;j>0;j--){
            swap(arr,0,j);//交换首尾元素
            adjustHeap(arr,0,j);//重新构建
        }
    }

    /**
     * 调整大顶堆的过程
     * @param arr 需要调整的数组
     * @param i 需要调整的非叶节点
     * @param length
     */
    public static void adjustHeap(int []arr,int i,int length){
        int temp = arr[i];//取出当前元素
        for(int k = i*2+1;k<length;k=k*2+1){//从i节点的左子节点开始,也就是2i+1开始
            if(k+1<length && arr[k]<arr[k+1]){//如果左子节点小于右子节点,将k指向右子节点
                k++;
            }
            if (arr[k]>temp) {//子节点大于父节点
                arr[i] = arr[k];//将子节点的值写入到父节点的位置
                i = k ;//记录被置换的节点位置
            }else{
                break;
            }
        arr[i] = temp;//将temp值放到最终的位置
        }
    }

    /**
     * 交换元素
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int []arr,int a ,int b){
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
