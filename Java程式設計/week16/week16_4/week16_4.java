package week16_4;

import java.io.*;
import java.util.Scanner;

public class week16_4 {
    public static void main(String args[]){
        try{
            File file;
            FileInputStream fileInputStream;
            FileOutputStream fileOutputStream;
            ObjectInputStream objectInputStream;
            ObjectOutputStream objectOutputStream;
            Scanner scanner=new Scanner(System.in);
            System.out.println("請輸入檔案名，例如 d:\\foo.txt");
            String filename=scanner.nextLine();
            file=new File(filename);
            if(!file.exists()){
                file.createNewFile();
                fileOutputStream=new FileOutputStream(file);
                objectOutputStream=new ObjectOutputStream(fileOutputStream);
                Person person=new Person("張三",20);
                objectOutputStream.writeObject(person);
                objectOutputStream.close();
                fileOutputStream.close();
                System.out.println("物件寫入完畢!");
                System.out.println("檔案"+filename+"的內容是:");
                Person object;
                fileInputStream=new FileInputStream(file);
                objectInputStream=new ObjectInputStream(fileInputStream);
                try {
                    object=(Person) objectInputStream.readObject();
                    System.out.println("讀取到的物件資訊:");
                    System.out.println("用戶名: "+object.getName());
                    System.out.println("年齡: "+object.getAge());
                }catch (ClassNotFoundException e){
                    System.out.println("讀取物件失敗!");
                }
                objectInputStream.close();
                fileInputStream.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
