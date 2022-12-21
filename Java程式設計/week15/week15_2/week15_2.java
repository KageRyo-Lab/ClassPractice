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
            FileInputStream fileInputStream=new FileInputStream(str);
            while((n= fileInputStream.read(b,0,1024))!=-1){
                String show=new String(b,0,n);
                System.out.println(show);
            }
            fileInputStream.close();
        }catch (IOException e){
            System.out.println("檔案讀取錯誤");
        }
    }
}
