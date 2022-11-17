package week05;

// 字串陣列練習
public class week05_10 {
    public static void main(String args[]){
        char[] charArr={' ','J','a','v','a',' '};
        String str=" Java ";
        String str1 = new String(charArr);
        String str2=new String("程式設計範例");

        System.out.println("str字串：\""+str+"\"");
        System.out.println("str1字串：\""+str1+"\"");
        System.out.println("str2字串：\""+str2+"\"");

        System.out.println("str1 indexOf('a',2)："+str1.indexOf('a',2));
        System.out.println("str1 lastIndexOf('b',2)："+str1.lastIndexOf('b',2));

        System.out.println("英文str1.charAt(3)："+str1.charAt(4));
        System.out.println("中文str2.substring(2,6)："+str2.substring(2,6));
    }
}
