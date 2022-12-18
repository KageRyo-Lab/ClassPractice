import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class week14_5 {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        System.out.println("請輸入檔名，例如: d:\\1.png");
        String string =scanner.nextLine();
        File file=new File(string);
        System.out.println("檔案名："+file.getName());
        System.out.println("文件大小為："+file.length()+"位元組");
        System.out.println("檔案所在路徑為："+file.getAbsolutePath());
        if(file.isHidden()){
            System.out.println("該檔是一個隱藏檔");
        }else{
            System.out.println("該檔不是一個隱藏檔");
        }

        if(!file.exists()){
            System.out.println("該文件不存在");
            try{
                file.createNewFile();
                System.out.println("新檔案創建成功");
            }catch (IOException e){}
        }
    }
}
