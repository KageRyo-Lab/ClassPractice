package week05;

// 字串陣列練習
public class week05_9 {
    public static void main(String args[]){
        char[] charArr={' ','j','a','v','a',' '};
        StringBuffer stringBuffer = new StringBuffer("use");
        String str=" JAVA ";
        String str1=new String(charArr);
        String str2=new String("程式設計範例教本");
        System.out.println("str字串：\""+str+"\"");
        System.out.println("str1字串：\""+str1+"\"");
        System.out.print("str1長度："+str1.length());
        System.out.println(" / str2長度："+str2.length());
        System.out.println("str1轉小寫："+str1.toLowerCase()+" / str1轉大寫："+str1.toUpperCase());
        System.out.println("str1刪除空白字元："+str1.trim());
        System.out.println("str與str1字串是否相等："+str.equals(str1));
        System.out.println("str與str1字串是否相等-不分大小寫："+str.equalsIgnoreCase(str1));
    }
}
