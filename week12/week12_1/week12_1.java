package week12_1;

import java.util.Scanner;

public class week12_1 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("2+3=?");
			if(5!=sc.nextInt()) {
				throw new MyException();
			}else {
				System.out.println("回答正確");
			}
		}catch(MyException e) {
			e.ErrorAnswer();
		}
	}
}
