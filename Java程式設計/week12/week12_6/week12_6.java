package week12_6;

import java.util.ArrayList;
import java.util.List;

public class week12_6 {
	public static void main(String[] args) {
		List<Integer> num=new ArrayList<>();
		num.add(1);
		num.add(2);
		num.add(3);
		System.out.println("List: "+num);
		int n=num.get(2);
		System.out.println("訪問元素: "+n);
		int index=num.indexOf(2);
		System.out.println("位置3的元素是 "+index);
		int remove=num.remove(1);
		System.out.println("刪除元素: "+remove);
	}
}
