package week05;

// 字串陣列練習
public class week05_8 {
    public static void main(String args[]){
        char[] charArr={' ','j','a','v','a',' '};
        StringBuffer stringBuffer = new StringBuffer("use");
        String str="JAVA";
        String str1=new String(charArr);
        String str2=new String("程式設計範例教本");
        System.out.println("str字串：\""+str+"\"");
        System.out.println("str1字串：\""+str1+"\"");
        System.out.println("str1長度："+str1.length()+" / str2長度："+str2.length());
        System.out.println("str與str1字串長度是否相等："+str.equals(str1));
    }
}
