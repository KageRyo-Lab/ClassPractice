package week10.week10_2;

import java.util.Scanner;

public class week10_2 {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入底... ");
        double x = scanner.nextDouble();
        System.out.print("請輸入高... ");
        double y = scanner.nextDouble();
        Calc calc = new Calc();
        calc.Calc(x, y);
    }
}
