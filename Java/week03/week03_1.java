package week03;

import java.util.Scanner;

// 輸入與奇偶數判斷練習
public class week03_1 {
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        var input=scanner.nextInt();
        var remain=input%2;
        if(remain==0){
            System.out.println(input + " is even.");
        }else{
            System.out.println(input + " is odd.");
        }
    }
}
