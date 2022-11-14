package week03;

import java.util.Scanner;

// 輸入與分數判斷練習
public class week03_3 {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        var score=scanner.nextInt();
        var level='\0';
        if(score>=90) {
            level='A';
        }else if(score>=80){
            level='B';
        }else if(score>=70){
            level='C';
        }else if(score>=60){
            level='D';
        }else{
            level='E';
        }
        System.out.println("The grade of level is "+level+".");
    }
}
