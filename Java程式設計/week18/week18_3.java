import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class week18_3 {
    public static void main(String[] args) {
        File file = new File("output.txt");
        try {
            OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file));
            output.write("tree\nA\n10\n8888\n3.14\n3.14\nhello\nhello, everyone!");
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("寫入完成!");
        }
        System.out.println("要使用收集類別排序數字後寫回檔案嗎?(y/n)");
        Scanner scanner = new Scanner(System.in);
        String isContinue = scanner.next();
        if(isContinue.equals("y")){
            try {
                BufferedReader input = new BufferedReader(new FileReader(file));
                List<Double> numbers = new ArrayList<>();
                String line;
                while ((line = input.readLine()) != null) {
                    try {
                        double number = Double.parseDouble(line);
                        numbers.add(number);
                    } catch (NumberFormatException ex) {
                    }
                }
                input.close();
                Collections.sort(numbers);
                OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file));
                for (double number : numbers) {
                    output.write(String.valueOf(number) + "\n");
                }
                output.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            System.out.println("程式結束");
        }
    }
}