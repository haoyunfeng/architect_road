package cn.haoyunfeng.datastruc.sort;

/**
 * @Description: 冒泡排序
 * 不稳定排序 时间复杂度O（n²）
 * @author: haoyunfeng2
 * @Date: 2019/10/28 2:30 下午
 */
public class BubbleSort {

    public static int[] bubbleSort(int []array){
        for(int i =0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i] > array[j]){
                    int temp;
                    temp = array[i];
                    array[i] = array[j];
                    array[j] =temp;
                }
            }
        }

        return array;
    }

    public static void main(String []args){
        int [] array = {4,5,6,3,1,2};
        bubbleSort(array);
    }

}
