package week03;

// return練習
public class week03_10 {
    public static void main(String args[]){
        for(var i=0; i<11; i++){
            System.out.println("i="+i);
            if(i==5){
                System.out.println("return...");
                return;
            }
        }
    }
}
