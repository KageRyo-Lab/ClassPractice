package week03;

// for迴圈印九九乘法表練習
public class week03_5 {
    public static void main(String args[]){
        for(var i=1; i<10; i++){
            for(var j=1; j<10; j++){
                System.out.printf("%d*%d=%2d\t",i,j,i*j);
            }
            System.out.println();
        }
    }
}
