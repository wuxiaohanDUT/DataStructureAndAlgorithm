package QuickSort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public void sort(int[] nums,int begin,int end){
        if(begin>=end){
            return;
        }
        //随机选取key元素
        int index=new Random().nextInt(end-begin)+begin;
        int key=nums[index];
        swap(nums,index,end-1);
        int i=begin-1;
        //将数组以key为临界值进行划分
        for(int j=begin;j<end-1;++j){
            if(nums[j]<=key){
                i++;
                swap(nums,i,j);
            }
        }
        //交换key，并对前后两部分进行排序
        swap(nums,i+1,end-1);
        sort(nums,begin,i+1);
        sort(nums,i+2,end);
    }
    public void swap(int[] nums,int i,int j){
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
        new QuickSort().sort(nums,0,randomLen);
        System.out.println(Arrays.toString(nums));
    }
}
