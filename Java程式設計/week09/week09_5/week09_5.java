package week09.week09_5;

public class week09_5 {
    public static void main(String args[]){
        for(Size size : Size.values()){
            System.out.println(size + "的序號是:"+size.ordinal());
            System.out.print(size.compareTo(Size.SMALL) + " ");
            System.out.print(size.equals(Size.SMALL) + " ");
        }
    }
}
