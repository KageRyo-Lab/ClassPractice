package week11;

public class week11_5 {
	public static void main(String args[]) {
		try {
			showSalary("王小明",35000);
			showSalary("李小華",50000);
		}catch(IllegalArgumentException e) {
			System.out.println("例外內容："+e.getMessage());
		}
	}
	static void showSalary(String name, int money) throws IllegalArgumentException{
		System.out.println("員工："+name);
		if(money>=20000&&money<=40000) {
			System.out.println("底薪："+money+"獎金："+money*0.08);
		}else {
			throw new IllegalArgumentException("呼叫方法引數錯誤");
		}
	}
}
