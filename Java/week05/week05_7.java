package week05;

import java.util.Arrays;

// 複製陣列練習
public class week05_7 {
    public static void main(String args[]){
        int[] org=new int[] {1,2,3,4,5};
        System.out.println("原始陣列：");
        for(int i=0;i<org.length;i++){
            System.out.print(org[i]+" ");
        }
        int[] copy= Arrays.copyOf(org,7);
        System.out.println("\n複製陣列：");
        for(int i=0;i< copy.length;i++){
            System.out.print(copy[i]+" ");
        }
    }
}
