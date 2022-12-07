package week13_2;

import java.util.EnumSet;

import static week13_2.Weeks.MONDAY;
import static week13_2.Weeks.TUESDAY;

public class week13_2 {
    public static void main(String args[]){
        EnumSet<Weeks> weeks = EnumSet.noneOf(Weeks.class);
        weeks.add(MONDAY);
        System.out.println("EnumSet中的元素："+weeks);
        weeks.remove(MONDAY);
        System.out.println("EnumSet中的元素："+weeks);
        weeks.addAll(EnumSet.complementOf(weeks));
        System.out.println("EnumSet中的元素："+weeks);
        weeks.removeAll(EnumSet.range(MONDAY,TUESDAY));
        System.out.println("EnumSet中的元素："+weeks);
    }
}
