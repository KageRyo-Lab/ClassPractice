import java.util.ArrayList;
import java.util.ListIterator;

public class week14_3 {
    public static void main(String args[]){
        ArrayList<String> arrayList=new ArrayList<String>(4);
        arrayList.add("奇異冒險");
        arrayList.add("冰雪奇緣");
        arrayList.add("魔法滿屋");
        System.out.println("ArrayList元素："+arrayList);

        System.out.print("List元素（ListIterator）：");
        ListIterator<String> iterator=arrayList.listIterator(0);
        while (iterator.hasNext())
            System.out.print(" "+iterator.next());
        System.out.println();
        System.out.print("反向顯示元素（ListIterator）：");
        ListIterator<String> iterator1=arrayList.listIterator(arrayList.size());
        while (iterator1.hasPrevious())
            System.out.print(" "+iterator1.previous());
        System.out.println();
    }
}
