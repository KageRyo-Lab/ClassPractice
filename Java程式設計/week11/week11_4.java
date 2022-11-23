package week11;

public class week11_4 {
	public static void main(String args[]) {
		try {
			String str=args[0];
			for(int i=2;i>-1;i--) {
				System.out.println("計算結果："+6/i);
			}
		}catch(ArithmeticException ex) {
			System.out.println("例外說明："+ex.getMessage());
			System.out.println("例外原因：");
			ex.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException ex) {
			System.out.println("例外說明："+ex.getMessage());
			System.out.println("例外原因：");
			ex.printStackTrace();
		}finally {
			System.out.println("錯誤處理結束");
		}
	}
}
