package cn.haoyunfeng.datastruc.practice.sort;

/**
 * @Description:
 * @author: haoyunfeng2
 * @Date: 2019/11/1 2:44 下午
 */
public class QuickSort {

    public static void quickSort(int []array,int start,int end){
        if(start >= end) return;
        int pivot = partition(array,start,end);
        quickSort(array,start,pivot-1);
        quickSort(array,pivot+1,end);
    }

    public static int partition(int []array,int start,int end){
        int pivotValue = array[end];
        int pivot =start;

        for(int cursor = start;cursor < end;cursor++){
            if(array[cursor] <pivotValue){
                if(cursor == pivot){
                    pivot ++;
                }else {
                    int temp = array[pivot];
                    array[pivot++] = array[cursor];
                    array[cursor] = temp;
                }
            }
        }

        array[end] = array[pivot];
        array[pivot] = pivotValue;
        return pivot;
    }


    public static void main(String []args){
        int []array = {4,5,6,3,1,2};
        int start = 0;
        int end = array.length-1;
        quickSort(array,start,end);
        System.out.println(array);
    }
}
