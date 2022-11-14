package week03;

// while迴圈練習
public class week03_6 {
    public static void main(String args[]){
        while (true){
            var number= (int)(Math.random()*10);
            System.out.println(number);
            if(number==6){
                System.out.println("Found number "+number+", leaving...");
                break;
            }
        }
    }
}
