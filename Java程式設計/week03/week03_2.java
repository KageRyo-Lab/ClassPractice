package week03;

import java.util.Scanner;

// 輸入與分數判斷練習
public class week03_2 {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the grade... ");
        int grade = scanner.nextInt();
        if(grade>=90) {
            System.out.println("Level = A");
        }else if(grade>=80){
            System.out.println("Level = B");
        }else if(grade>=70){
            System.out.println("Level = C");
        }else if(grade>=60){
            System.out.println("Level = D");
        }else{
            System.out.println("Level = E");
        }
    }
}
