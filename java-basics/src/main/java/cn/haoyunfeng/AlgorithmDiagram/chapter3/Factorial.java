package cn.haoyunfeng.AlgorithmDiagram.chapter3;

/**
 * 
 * @author Hanwen
 * @date 2017��8��29�� ����11:17:26
 * @version
 */
public class Factorial {
	public static void main(String[] args) {
		int n = 5;
		System.out.println(factorial(n));
	}
	
	/**
	 * �ݹ���׳�
	 * @param n (�������)
	 * @return result(����Ľ��)
	 */
	private static int factorial(int n) {
		if(n == 1) {
			return 1;
		}else {
			return n * factorial(n-1);
		}
	}
}