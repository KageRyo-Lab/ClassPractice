package week14_6;

import java.io.File;
import java.util.Scanner;

public class week14_6 {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        System.out.println("請輸入要訪問的目錄：");
        String string=scanner.nextLine();
        File dirFile=new File(string);
        String[] allresults=dirFile.list();
        for(String name:allresults)
            System.out.println(name);
        System.out.println("請輸入檔副檔名：");
        string=scanner.nextLine();
        FilterName filterName = new FilterName();
        filterName.setExtendName(string);
        String result[]= dirFile.list(filterName);
        for(String name:result)
            System.out.println(name);
    }
}