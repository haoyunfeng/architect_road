package cn.haoyunfeng.datastruc.practice.sort;

/**
 * @Description: 选择排序，和冒泡排序类似。比冒泡少了交换次数
 * @author: haoyunfeng2
 * @Date: 2019/10/28 7:01 下午
 */
public class SelectionSort {

    public static void selectionSort(int []array){
        for(int i=0;i<array.length;i++){
            int minindex = i;
            for(int j = i+1;j<array.length;j++){
                if(array[minindex] > array[j]){
                    minindex = j;
                }
            }

            int temp = array[i];
            array[i] = array[minindex];
            array[minindex] = temp;

        }
        System.out.println(array);
    }

    public static void main(String[]args){
        int[]array = {4,5,6,1,2,3};
        selectionSort(array);
    }
}
