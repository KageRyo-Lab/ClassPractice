package week11;

public class week11_3 {
	public static void main(String args[]) {
		try {
			for(int i=2;i>-1;i--) {
				System.out.println("計算結果："+6/i);
			}
		}catch(ArithmeticException ex) {
			System.out.println("例外說明："+ex.getMessage());
			System.out.println("例外原因：");
			ex.printStackTrace();
		}finally {
			System.out.println("錯誤處理結束");
		}
		System.out.println("程式結束！");
	}
}
