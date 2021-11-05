package algorithms;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

public class AlgorithmsUtil {
    /* @description: 冒泡排序
     * @author: Sherwin Liang
     * @timestamp: 2021/10/2 9:50
    */
    public static void bubbleSort(Integer[] arr){
        System.out.println("Before sorted: "+ Arrays.toString(arr));
        int temp;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("After sorted: "+ Arrays.toString(arr));
    }
    /* @description: 分治法快速排序
     * @author: Sherwin Liang
     * @timestamp: 2021/10/2 11:24
    */
    public static void quickSort(Integer[] arr, int low, int high){
        if(low>high)
            return;
        //选中一个基准，按基准分割大小
        int pivot = arr[high];
        int temp = 0;
        int pivotIndex = low;
        for(int i=low;i<high;i++){
            if(arr[i]<pivot){
                temp=arr[pivotIndex];
                arr[pivotIndex]=arr[i];
                arr[i]=temp;
                pivotIndex++;
            }
        }
        arr[high]=arr[pivotIndex];
        arr[pivotIndex] = pivot;
        quickSort(arr,0,pivotIndex-1);
        quickSort(arr,pivotIndex+1,high);
    }
    /* @description: 选择排序，选出每个位置上属于该位置的数字
     * @author: Sherwin Liang
     * @timestamp: 2021/10/2 12:26
    */
    public static void selectionSort(Integer[] arr){
        int temp = 0;
        for(int i=0;i<arr.length;i++){
            for(int j=i;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    /* @description: 插入排序,每次插入一个数字进行排序
     * @author: Sherwin Liang
     * @timestamp: 2021/10/2 15:16
    */
    public static void insertSort(Integer[] arr){
        int temp = 0;
        for(int i=1;i<arr.length;i++){
            for(int j=0;j<i;j++){
                if(arr[j]>arr[i]){
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
    /* @description: 生成斐波那契数列
     * @author: Sherwin Liang
     * @timestamp: 2021/10/2 16:58
    */
    public static int[] generateFiboArr(int length){
        int[] arr = new int[length];
        for(int i=0;i<length;i++){
            arr[i] = fibo(i);
        }
        return arr;
    }
    public static int fibo(int count){
        if(count<=1)
            return count;
        return fibo(count-1)+fibo(count-2);
    }
    /* @description: 滑动窗口查找
     * @author: Sherwin Liang
     * @timestamp: 2021/10/6 15:47
    */
    public static boolean shiftSearch(@NotNull String str, @NotNull String needFind){
        int start = 0;
        int end=needFind.length();
        String temp = null;
        while(end<str.length()){
            temp = str.substring(start,end);
            if(temp.equals(needFind)){
                return true;
            }
            start++;
            end++;
        }
        return false;
    }
    /* @description: 二分查找有序数组
     * @author: Sherwin Liang
     * @timestamp: 2021/10/6 23:31
    */
    public static boolean binarySearch(Integer[] arr, int needFind){
        int start = 0;
        int end = arr.length-1;
        int index = end/2;
        while(index>=1 && index<=(arr.length-1)){
            if(arr[index] == needFind){
                return true;
            }else if(arr[index] < needFind){
                start = index;
            }else{
                end = index;
            }
            index = (start+end)/2;
        }
        return false;
    }
    /* @description: 希尔排序，分辨率越低数组越有序
     * @author: Sherwin Liang
     * @timestamp: 2021/10/7 11:31
    */
    public static void shellSort(double[] arr, int resolution){
        int length = arr.length;
        double temp = 0d;
        while(resolution>0){
            for(int i=resolution;i<length;i++){
                for(int j=i;j>=resolution;j-=resolution){
                    temp=arr[j];
                    if(arr[j-resolution]>temp){
                        arr[j] = arr[j-resolution];
                        arr[j-resolution] = temp;
                    }
                }
            }
        }
    }
}
