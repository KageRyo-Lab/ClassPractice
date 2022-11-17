package week04;

import java.util.Scanner;

// 攝氏溫度轉換練習
public class week04_3 {
    static double convert2F(double c){
        double f;
        f=(9.0*c)/5.0+32.0;
        return f;
    }
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        System.out.print("請輸入攝氏溫度... ");
        double c=scanner.nextDouble();
        double f=convert2F(c);
        System.out.println("攝氏"+c+" = 華氏"+f);
    }
}
