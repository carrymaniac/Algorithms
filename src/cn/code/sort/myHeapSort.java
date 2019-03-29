package cn.code.sort;

import java.util.Arrays;

/**
 * 我的堆排序
 */
public class myHeapSort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Heap heap = new Heap();
        heap.setData(arr);
        System.out.println("排序结果：" + Arrays.toString(heap.sort()));
    }
}

class Heap{
    private int[] heap;//堆数组
    private int[] sortedList;//排列完成后的数组
    public int[] sort(){
        construct();
        extract();
        return sortedList;
    }
    //填充数据
    public void setData(int[] data){
        heap = new int[data.length];
        sortedList = new int[data.length];
        for(int i = 0 ; i<data.length;i++){
            heap[i] = data[i];
        }
    }
    //构造一个
    private void construct(){
        int current,maxChildIndex;
        boolean flag;
        for(int i = (heap.length-2)/2;i>=0;i--){
            current = i ;
            flag = false;
            while (!flag){
                if(2*current+1>heap.length-1){
                    //不存在左子节点,跳出
                    flag = true;
                }else {
                    maxChildIndex = maxChild(current,heap.length-1);
                    if(heap[current]<heap[maxChildIndex]){
                        swap(heap,current,maxChildIndex);
                        current = maxChildIndex;
                    }else {
                        flag = true ;
                    }
                }

            }
        }
        testPrint(heap.length);
    }
    private void extract(){
        int current,maxChildIndex;
        boolean flag;
        for(int size = heap.length-1;size>=0;size--){
            sortedList[size] = heap [0];
            heap[0] = heap[size];
            current = 0 ;
            flag = false;
            while (!flag){
                if(2*current+1>heap.length-1){
                    //不存在左子节点,跳出
                    flag = true;
                }else {
                    maxChildIndex = maxChild(current,heap.length-1);
                    if(heap[current]<heap[maxChildIndex]){
                        swap(heap,current,maxChildIndex);
                        current = maxChildIndex;
                    }else {
                        flag = true ;
                    }
                }
            }
            testPrint(size);
        }
    }
    private void testPrint(int limit) {
        for(int i = 0;i<limit;i++){
            System.out.println(""+heap[i]);
        }
        System.out.println("    ");
    }

    private int maxChild(int location, int end) {
        int result ,leafChild,rightChild;
        rightChild = 2*location+2;
        leafChild = 2*location+1;

        if(rightChild<=end&&heap[leafChild]<heap[rightChild]){
            //存在右节点(即左节点也存在)且左节点小于右节点,此时返回右节点
            result = rightChild ;
        }else {
            result = leafChild;
        }
        return result;
    }

    private void swap(int[] ints,int a,int b){
        ints[a] = ints[a]+ints[b];
        ints[b] = ints[a]-ints[b];
        ints[a] = ints[a]-ints[b];
    };

}
