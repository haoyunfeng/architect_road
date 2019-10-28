package cn.haoyunfeng.datastruc.sort;

/**
 * @Description:
 * @author: haoyunfeng2
 * @Date: 2019/10/25 3:27 下午
 */
public class SortSectionOne {

    // 插入排序，a表示数组，n表示数组大小
    public static void insertionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];  // 数据移动
                } else {
                    break;
                }
            }
            a[j+1] = value; // 插入数据
        }
        System.out.println(a);
    }
    public  static  void main(String []args){
        int [] a = {4,5,6,1,3,2};
        insertionSort(a,a.length);
    }
}
