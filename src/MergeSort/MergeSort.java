package MergeSort;

import QuickSort.QuickSort;

import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    public void sort(int[] nums,int begin,int end){
        if(begin>=end-1){
            return;
        }
        int mid=(begin+end)>>1;
        //先分治
        sort(nums,begin,mid);
        sort(nums,mid,end);
        //再归并
        merge(nums,begin,mid,end);
    }
    public void merge(int[] nums,int begin,int mid,int end){
        int[] arr=new int[end-begin];
        int i=begin,j=mid,k=0;
        while(i<mid&&j<end){
            if(nums[i]<nums[j]){
                arr[k++]=nums[i++];
            }else{
                arr[k++]=nums[j++];
            }
        }
        while(i<mid){
            arr[k++]=nums[i++];
        }
        while(j<end){
            arr[k++]=nums[j++];
        }
        for(i=begin,j=0;i<end;++i,j++){
            nums[i]=arr[j];
        }
    }
    public static void main(String[] argc){
        int randomLen=new Random().nextInt(20);
        int[] nums=new int[randomLen];
        for(int i=0;i<randomLen;++i){
            nums[i]=new Random().nextInt(100);
        }
        new MergeSort().sort(nums,0,randomLen);
        System.out.println(Arrays.toString(nums));
    }
}
