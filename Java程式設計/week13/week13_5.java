import java.util.HashSet;
import java.util.Set;

public class week13_5 {
    public static void main(String args[]){
        Set<String> set=new HashSet<>();
        System.out.println(set.add("abc"));
        System.out.println(set.add("xyz"));
        System.out.println(set.add("xyz"));
        System.out.println(set.contains("xyz"));
        System.out.println(set.contains("XYZ"));
        System.out.println(set.remove("hello"));
        System.out.println(set.size());
    }
}
