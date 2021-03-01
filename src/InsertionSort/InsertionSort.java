package InsertionSort;

import HeapSort.HeapSort;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序
 * 稳定
 */
public class InsertionSort {
    public void sort(int[] arr,int begin,int end){
        for(int i=begin;i<end;++i){
            int key=arr[i];
            int j;
            for(j=i;j>0&&arr[j-1]>key;--j){
                arr[j]=arr[j-1];
            }
            arr[j]=key;
            System.out.println(Arrays.toString(arr));
        }
    }
    public static void main(String[] argc){
        int randomLen=new Random().nextInt(20);
        int[] nums=new int[randomLen];
        for(int i=0;i<randomLen;++i){
            nums[i]=new Random().nextInt(100);
        }
        System.out.println(Arrays.toString(nums));
        new InsertionSort().sort(nums,0,randomLen);
        System.out.println(Arrays.toString(nums));
    }
}
