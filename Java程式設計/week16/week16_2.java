import java.io.*;
import java.util.Scanner;

public class week16_2 {
    public static void main(String args[]){
        File file;
        FileReader fileReader;
        FileWriter fileWriter;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        Scanner scanner=new Scanner(System.in);
        System.out.println("請輸入檔案名，例如 d:\\hello.txt");
        String filename=scanner.nextLine();
        try{
            file =new File(filename);
            if(!file.exists()){
                file.createNewFile();
                fileWriter=new FileWriter(file);
            }else{
                fileWriter=new FileWriter(file,true);
            }
            fileReader=new FileReader(file);
            bufferedReader=new BufferedReader(fileReader);
            bufferedWriter=new BufferedWriter(fileWriter);
            System.out.println("請輸入資料，最近一行為字元'0'時結束。");
            String str=scanner.nextLine();
            while(!str.equals("0")){
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                str=scanner.nextLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("文件寫入完畢！");

            System.out.println("檔"+filename+"的內容是：");
            while((str=bufferedReader.readLine())!=null){
                System.out.println(str);
            }
            bufferedReader.close();
            fileReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
