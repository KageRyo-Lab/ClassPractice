package week15_1;

import java.io.File;
import java.util.Scanner;

public class week15_1 {
    public static void main(String args[]){
        // 建立檔案物件
        File file = new File("src/week15_1/record.txt");
        // 建立讀取器物件
        Scanner reader = null;
        // 定義價錢變數總和變數
        double price,total=0;
        try{
            reader = new Scanner(file); // 初始化讀取器，將檔案傳入
            reader.useDelimiter("[^0-9.]+"); // 設定分隔符為非數字和小數點的任意字元
            while(reader.hasNextDouble()){
                price = reader.nextDouble();
                total = total + price;
            }
            System.out.println("總花費："+total+"元");
        }catch (Exception e){
            // 如果發生錯誤，輸出錯誤訊息
            System.out.println(e.getMessage());
        }
    }
}
