package week12_2;

// 泛型方法的定義
public class week12_2 {
	public static <E> void printArray(E[] arr) {
		for(E ele:arr) {
			System.out.print(ele + " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Integer[] intArr= {1,2,3,4,5};
		Double[] doubleArr= {1.1,2.2,3.3,4.4};
		Character[] charArr= {'H','E','L','L','O'};
		
		System.out.println("整數：");
		printArray(intArr);
		System.out.println("浮點數：");
		printArray(doubleArr);
		System.out.println("字元：");
		printArray(charArr);
	}
}