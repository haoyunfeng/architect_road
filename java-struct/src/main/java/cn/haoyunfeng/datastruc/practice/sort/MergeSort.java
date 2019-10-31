package cn.haoyunfeng.datastruc.practice.sort;

/**
 * @Description: 归并排序
 * @author: haoyunfeng2
 * @Date: 2019/10/29 12:11 下午
 */
public class MergeSort {

    public static void mergeSort(int []data){
        int start = 0;
        int end = data.length-1;
        divide(data,start,end);
    }

    //分解任务
    public static void divide(int []data,int start,int end){
        if(start >= end)return;
        int mid = start +(end-start)/2;
        divide(data,start,mid);
        divide(data,mid+1,end);
        merge(data,start,end,mid);
    }

    //合并数据
    public static void merge(int []data,int start,int end ,int mid){
        int topindex = start;
        int midindex = mid+1;
        int []temp = new int[end-start+1];
        int tempindex =0;
        while (topindex <= mid && midindex <= end){
            if(data[topindex] < data[midindex]){
                temp[tempindex++] = data[topindex++];
            } else {
                temp[tempindex++] = data[midindex++];
            }
        }

        int copyStart = topindex;
        int copyEnd = mid;
        if(copyStart>=midindex){
            copyStart = midindex;
            copyEnd = end;
        }

        //拷贝剩余数组数据
        while (copyStart <= copyEnd){
            temp[tempindex++] = data[copyStart++];
        }

        for(int i=0;i<temp.length;i++){
            data[start+i] = temp[i];
        }
        System.out.println(data);
    }

    public static void main(String []args){
        int []data = {4,5,6,3,1,2};
        mergeSort(data);
        System.out.println(data);
    }
}
