package cn.haoyunfeng.datastruc.sort;

/**
 * @Description: 插入排序
 * @author: haoyunfeng2
 * @Date: 2019/10/28 3:45 下午
 */
public class InsertionSort {
    public static void insertionSort(int []array){
        for(int i=1;i<array.length;i++){
            int value = array[i];
            int j = i-1;
            for(;j>=0;j--){
                if(array[j]>value){
                    array[j+1] = array[j];
                }else {
                    break;
                }
            }
            array[j+1] =value;
        }
        System.out.println(array);
    }

    public static void main(String []args){
        int[]array = {4,5,6,1,2,3};
        insertionSort(array);
    }
}
