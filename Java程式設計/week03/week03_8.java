package week03;

// 巢狀迴圈練習
public class week03_8 {
    public static void main(String args[]){
        out: for(var i=0; i<5; i++){
                for(var j=0; j<4; j++){
                    System.out.println("i="+i+", j="+j);
                    if(j==2)break out;
                }
        }
    }
}
