package week15_1;

import java.io.File;
import java.util.Scanner;

public class week15_1 {
    public static void main(String args[]){
        File file = new File("src/week15_1/record.txt");
        Scanner reader = null;
        double price,total=0;
        try{
            reader = new Scanner(file);
            reader.useDelimiter("[^0-9.]+");
            while(reader.hasNextDouble()){
                price = reader.nextDouble();
                total = total + price;
            }
            System.out.println("總花費："+total+"元");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
