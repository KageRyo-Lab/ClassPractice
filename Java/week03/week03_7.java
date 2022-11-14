package week03;

// do...while 練習
public class week03_7 {
    public static void main(String args[]){
        var number=-1;
        do{
            number=(int)(Math.random()*10);
            System.out.println(number);
        }while(number!=5);
        System.out.println("Found number 5.");
    }
}
