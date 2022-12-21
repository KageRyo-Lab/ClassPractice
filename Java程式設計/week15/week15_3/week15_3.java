package week15_3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class week15_3 {
    public static void main(String args[]){
        String content;
        byte[] bytes;
        FileOutputStream out;
        Scanner scanner=new Scanner(System.in);
        System.out.println("請輸入檔名：（例如：d:\\hello.txt）");
        String filename = scanner.nextLine();
        File file=new File(filename);
        if(!file.exists()){
            System.out.println("檔案不存在，是否創建？(y/n)");
            String f = scanner.nextLine();
            if(f.equalsIgnoreCase("n")){
                System.exit(0);
            }else{
                try{
                    file.createNewFile();
                }catch (IOException e){
                    System.out.println("創建失敗");
                    System.exit(0);
                }
            }
        }
        try{
            content="HELLO";
            bytes=content.getBytes();
            out= new FileOutputStream(file);
            out.write(bytes);
            out.close();
            System.out.println();
        }catch (IOException e){
            e.getMessage();
        }
        try{
            System.out.println("請輸入追加的內容：");
            content = scanner.nextLine();
            bytes = content.getBytes();
            out=new FileOutputStream(file,true);
            out.write(bytes);
            out.close();
            System.out.println("檔案追加寫操作成功！");
            scanner.close();
        }catch(IOException e){
            e.getMessage();
        }
    }
}
