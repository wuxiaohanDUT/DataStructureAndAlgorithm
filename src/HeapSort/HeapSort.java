package HeapSort;

import QuickSort.QuickSort;

import java.util.Arrays;
import java.util.Random;

/**
 * 堆排序
 * 不稳定
 */
public class HeapSort {

    /**
     * 维护某一个堆的性质，是一个递归的过程
     * @param arr
     * @param i
     * @param len
     */
    private void minHeapify(int arr[],int i,int len){
        int l=2*i+1,r=2*i+2;
        int minIndex=i;
        if(l<len&&arr[l]<arr[minIndex]){
            minIndex=l;
        }
        if(r<len&&arr[r]<arr[minIndex]){
            minIndex=r;
        }
        if(minIndex!=i){
            int temp=arr[minIndex];
            arr[minIndex]=arr[i];
            arr[i]=temp;
            minHeapify(arr,minIndex,len);
        }
    }

    /**
     * 建堆，从节点(len-1)/2开始维护堆的性质
     * @param arr
     */
    private void buildMinHeap(int arr[]){
        int len=arr.length;
        for(int i=(len-1)/2;i>=0;--i){
            minHeapify(arr,i,len);
        }
    }

    /**
     * 排序，将堆顶的元素和最后一个元素互换位置，然后再维护堆的性质
     * @param arr
     */
    public void sort(int arr[]){
        buildMinHeap(arr);
        for(int i=arr.length-1;i>=0;--i){
            int temp=arr[i];
            arr[i]=arr[0];
            arr[0]=temp;
            minHeapify(arr,0,i);
        }
    }
    //case
    public static void main(String[] argc){
        int randomLen=new Random().nextInt(20);
        int[] nums=new int[randomLen];
        for(int i=0;i<randomLen;++i){
            nums[i]=new Random().nextInt(100);
        }
        new HeapSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
