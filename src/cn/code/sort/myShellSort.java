package cn.code.sort;

public class myShellSort {

    public void shellSort(int[] arr){
        for(int gap = arr.length/2;gap>0;gap/=2){
            for(int i = gap ;i<arr.length;i++){
                int j = i ;
                while(j-gap>=0&&arr[j]<arr[j-gap]){
                    swap(arr,j,j-gap);
                    j-=gap;
                }
            }
        }
    }

    public static void swap(int[] arr,int a , int b){
        arr[a] = arr[a]+arr[b];
        arr[b] = arr[a]-arr[b];
        arr[a] = arr[a]-arr[b];
    }
}
