package week10;

public class week10_1 {
    public static void main(String args[]){
        int[] num = new int[11];
        for(int i=1;i<num.length;i++){
            num[i]=i;
        }

        int sum=0;
        for(int i:num){
            sum+=i;
        }
        System.out.println(sum);
    }
}
