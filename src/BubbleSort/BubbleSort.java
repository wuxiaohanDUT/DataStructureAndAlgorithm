package BubbleSort;


import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序
 * 稳定。
 */
public class BubbleSort {

    public void sort(int[] nums,int begin,int end){
        for(int i=0;i<end-begin;++i){
            for(int j=begin;j<end-1-i;++j){
                if(nums[j]>nums[j+1]){
                    swap(nums,j,j+1);
                }
            }
        }
    }

    private void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    public static void main(String[] argc){
        int randomLen=new Random().nextInt(20);
        int[] nums=new int[randomLen];
        for(int i=0;i<randomLen;++i){
            nums[i]=new Random().nextInt(100);
        }
        new BubbleSort().sort(nums,0,randomLen);
        System.out.println(Arrays.toString(nums));
    }
}
