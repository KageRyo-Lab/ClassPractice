package week11;

public class week11_2 {
	public static void main(String args[]) {
		int m[] = new int[5];
		System.out.println("為陣列元素賦值");
		try {
			m[5]=62;
			System.out.println("能執行這行代碼嗎？");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("陣列越界異常");
		}
		System.out.println("賦值完畢");
	}
}
