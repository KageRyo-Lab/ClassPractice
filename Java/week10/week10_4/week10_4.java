package week10.week10_4;

import java.util.Scanner;

public class week10_4 {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入底... ");
        double x = scanner.nextDouble();
        System.out.print("請輸入高... ");
        double y = scanner.nextDouble();

        Tran tran = new Tran(x,y);
        tran.area();
    }
}
