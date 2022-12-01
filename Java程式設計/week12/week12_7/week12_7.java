package week12_7;

import java.util.ArrayList;

public class week12_7 {
	public static void main(String[] args) {
		ArrayList<String> sites=new ArrayList<String>();
		sites.add("Google");
		sites.add("Runoob");
		sites.add("Taobao");
		System.out.println(sites);
		System.out.println(sites.get(1));
		sites.remove(2);
		System.out.println(sites);
	}
}
