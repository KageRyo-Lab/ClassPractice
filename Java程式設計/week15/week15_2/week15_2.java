package week15_2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class week15_2 {
    public static void main(String args[]){
        byte[] b=new byte[1024]; // 設置位元組緩衝區
        int n=1;
        System.out.println("請輸入要讀取的檔案名：例如（d:\\hello.txt）");
        Scanner scanner = new Scanner(System.in);
        String str=scanner.nextLine();
        try{
            // 建立一個新的 FileInputStream 物件，用於從指定的檔案讀取
            FileInputStream fileInputStream=new FileInputStream(str);
            // 以 1024 個位元組為單位讀取檔案並將資料存入位元組陣列
            // read() 方法在到達檔案結尾時會傳回 -1
            while((n= fileInputStream.read(b,0,1024))!=-1){
                // 從位元組陣列中建立新的 String 物件
                String show=new String(b,0,n);
                // 將資料輸出至 console
                System.out.println(show);
            }
            fileInputStream.close();  // 關閉檔案輸入串流
        }catch (IOException e){
            System.out.println("檔案讀取錯誤");
        }
    }
}
