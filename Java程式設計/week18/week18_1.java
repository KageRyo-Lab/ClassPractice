import java.util.ArrayList;
import java.util.List;

public class week18_1 {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        list.add(123);
        list.add(456);
        list.add("abc");
        list.add(0);
        try {
            double a = (Integer) list.get(0);
            double b = (Integer) list.get(1);
            System.out.println("(1) " + a + "/" + b + "=" + a / b);

            a = (Double) list.get(0);
            String bString = (String) list.get(2);
            System.out.println("(2) " + a + "/" + bString + "=" + a / Double.parseDouble(bString));

        } catch (ArithmeticException e) {
            System.out.println("(3) 除以零："+e.getMessage());
        } catch (Exception e) {
            System.out.println("(2) 輸入錯誤：" + e.getMessage());
        }
        try {
            int a = (Integer)list.get(0);
            int b = (Integer)list.get(3);
            System.out.println("(3) "+a+"/"+b+"="+a/b);
        } catch (ArithmeticException e) {
            System.out.println("(3) 除以0："+e.getMessage());
        }finally {
            System.out.println("程式結束");
        }
    }
}