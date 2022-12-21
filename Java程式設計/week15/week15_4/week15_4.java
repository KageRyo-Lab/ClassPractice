package week15_4;

import java.io.*;
import java.util.Scanner;

public class week15_4 {
    public static void main(String args[]) throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("請輸入來源檔案名和目的檔案名，中間用空格分隔");
        String source=scanner.next();
        String dest=scanner.next();
        File sourceFile=new File(source);
        File destFile=new File(dest);
        if(!sourceFile.exists()){
            System.out.println("被複製的檔案不存在");
            System.exit(1);
        }
        InputStream inputStream=new FileInputStream(sourceFile);
        OutputStream outputStream=new FileOutputStream(destFile);
        if((inputStream!=null)&&(outputStream!=null)){
            int tmp=0;
            while((tmp= inputStream.read())!=(-1));
            outputStream.write(tmp);
        }
        inputStream.close();
        outputStream.close();
        System.out.println("檔複製成功");
    }
}
