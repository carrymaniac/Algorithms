package cn.code.collection.tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 大顶堆的实现
 * 不知道为什么90爬不上去
 */
public class MaxPQ {
    private int[] heap;

    public MaxPQ(int[] arr){
        heap = new int[arr.length];
        for(int i =0;i<arr.length;i++){
            heap[i]=arr[i];
        }
        init();
        for(int a:heap){
            System.out.println(a);
        }
    }

    private void init(){
        int current,maxChildren;
        boolean flag;
        for(int i =(heap.length-2)/2;i>=0;i--) {
            current = i;
            flag = false;
            while (!flag) {
                if (current * 2 + 1 > heap.length-1) {
                    //说明没有子节点 跳出
                    flag = true ;
                }else {
                    //有子节点,判断一下哪个大
                    maxChildren = maxChildren(current,heap.length-1);
                    if(heap[maxChildren]>heap[current]){
                        //子节点大于父节点,交换
                        swap(heap,current,maxChildren);
                        current = maxChildren;
                    }else {
                        flag = true;
                    }
                }
            }
        }
    }

    /**
     * 判断左右节点谁大谁小
     * @param index
     * @param end
     * @return
     */
    private int maxChildren(int index,int end){
        if(index*2+2<=end&&heap[index*2+2]>heap[index*2+1]){
            //存在右节点且比左节点大
            return index*2+2;
        }else {
            return index*2+1;
        }
    }

    private void swap(int[] heap,int a,int b){
        heap[a]=heap[a]+heap[b];
        heap[b]=heap[a]-heap[b];
        heap[a]=heap[a]-heap[b];

    }

    public static void main(String[] args) {
        int[] test = new int[]{17,23,77,12,5,38,84,44,90};
        MaxPQ maxPQ = new MaxPQ(test);
    }

}
