package week03;

// continue練習
public class week03_9 {
    public static void main(String args[]){
        for(var i=0; i<10; i++){
            if(i==5){
                System.out.println("Found number "+i+", continue...");
                continue;
            }
            System.out.println(i);
        }
    }
}
