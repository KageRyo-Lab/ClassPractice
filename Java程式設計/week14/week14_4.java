import java.util.ArrayList;
import java.util.Collections;

public class week14_4 {
    public static void main(String args[]){
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.add("55");
        arrayList.add("04");
        arrayList.add("13");

        System.out.println("arrayList排序前："+arrayList);
        Collections.sort(arrayList);
        System.out.println("arrayList排序後 Collections.sort()："+arrayList);
    }
}
