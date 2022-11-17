package week03;

import java.util.Scanner;

// 輸入與判斷練習(switch case)
public class week03_4 {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        final var warning = "(Failed)";
        var score = scanner.nextInt();
        var quotient=score/10;
        var level= switch (quotient){
            case 10,9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> {
                String message = "E" + warning;
                yield message;
            }
        };
        System.out.println("Your level = "+level+".");
    }
}
