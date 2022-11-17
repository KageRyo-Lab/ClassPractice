package week10.week10_3;

import java.util.Scanner;

public class week10_3 {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.print("輸入一串字串... ");
        StringBuffer stringBuffer = new StringBuffer(scanner.next());

        System.out.println("字串長度："+stringBuffer.length());

        System.out.print("要刪除還是新增 (1)新增 (2)刪除... ");
        int choose=scanner.nextInt();
        switch(choose){
            case 1:
                System.out.print("新增什麼呢... ");
                String addstr = scanner.next();
                stringBuffer.append(addstr);
                System.out.println("結果："+stringBuffer+", 字串長度："+stringBuffer.length());
                break;
            case 2:
                System.out.print("刪除第幾到第幾個字... ");
                int n1=scanner.nextInt();
                int n2=scanner.nextInt();
                stringBuffer.delete(n1-1,n2);
                System.out.println("結果："+stringBuffer+", 字串長度："+stringBuffer.length());
                break;
            default:
                System.out.println("輸入錯誤");
                break;
        }
    }
}
