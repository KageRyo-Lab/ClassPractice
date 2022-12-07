import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class week13_7 {
    public static void main(String args[]){
        Set<String> set=new TreeSet<String>();
        set.add("abc");
        set.add("xyz");
        set.add("rst");
        System.out.println(set);
        Iterator itSet = set.iterator();
        while(itSet.hasNext()){
            System.out.print(itSet.next()+"\t");
            System.out.println();
        }
    }
}
