package week12_3;

public class week12_3 {
	public static void main(String[] args) {
		Box<Integer> intBox=new Box<Integer>();
		Box<String> strBox=new Box<String>();
		intBox.set(5);
		strBox.set("Hello World");
		System.out.println("整數："+intBox.get());
		System.out.println("字串："+strBox.get());
	}
}
