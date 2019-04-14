package cn.code.LeetCode.char1;

/**
 * 88. 合并两个有序数组
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 *
 * 实现两个指针同时从后面进行遍历,进行比较.哪方较大,哪方的数字先写入,指针向前挪动;
 * 当有一方的指针小于0,说明已经完成读取,此时只需要读取未读完的一边的数据
 * 注意要从后往前读(?)
 */
public class merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m -1 ;
        int index2 = n -1 ;
        int mergeindex = m + n -1;
        while(index1>=0||index2>=0){
            if (index1<0){

                nums1[mergeindex--]=nums2[index2--];

            }else if(index2<0){

                nums1[mergeindex--]=nums1[index1--];

            }else if(nums1[index1]>nums2[index2]){

                nums1[mergeindex--]=nums1[index1--];

            }else {

                nums1[mergeindex--]=nums2[index2--];

            }
        }
    }
}
