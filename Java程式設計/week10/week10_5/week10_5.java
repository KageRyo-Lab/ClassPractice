package week10.week10_5;

import java.util.Scanner;

public class week10_5 {
    public static void main(String args[]){
        Tran tran = new Tran();
        tran.f(new Calc(){
            @Override
            public void calc(){
                Scanner scanner = new Scanner(System.in);
                System.out.print("請輸入底... ");
                double x = scanner.nextDouble();
                System.out.print("請輸入高... ");
                double y = scanner.nextDouble();
                tran.area(x,y);
            }
        });
    }
}
