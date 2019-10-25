package cn.haoyunfeng.AlgorithmDiagram.chapter4;

/**
 * 
 * @author Hanwen
 * @date 2017��8��29�� ����5:58:38
 * @version
 */
public class QuickSort {
	
	private static int[] array = new int[]{1,3,2,5,4,8,6,7};
	
	public static void main(String[] args) {
		quickSort(array, 0, array.length-1);
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
	
	/**
	 * ��������
	 * @param array
	 * @param left
	 * @param right
	 */
	private static void quickSort(int[] array, int left, int right) {
		if(left < right) {
			int pivot = sort(array, left, right);
			quickSort(array, left, pivot-1);
			quickSort(array, pivot+1, right);
		}
	}

	/**
	 * �ҳ���׼ֵ
	 * @param array
	 * @param left
	 * @param right
	 * @return
	 */
	private static int sort(int[] array, int left, int right) {
	    int pivotValue = array[left];  
        while (left < right) {  
            while (left < right && array[right] >= pivotValue) {  
                right--;  
            }  
            array[left] = array[right];  
            while (left < right && array[left] <= pivotValue) {  
                left++;  
            }  
            array[right] = array[left];  
        }  
        array[left] = pivotValue;  
        return left;  
	}
}