package week05;

public class week05_4 {
    public static void main(String args[]){
        int sum=0;
        double total=0.0;
        int[] tmp;
        int[] grades={87,78,95};

        double[] sales = new double[4];
        sales[0]=145.6;
        sales[1]=178.9;
        sales[2]=197.3;
        sales[3]=156.7;
        tmp=grades;
        for(int i:tmp){
            sum+=i;
            System.out.print(" / "+i);
        }
        System.out.println("\n成績總分："+sum);
        for(int i=0;i<sales.length;i++){
            total+=sales[i];
            System.out.print(" / "+sales[i]);
        }
        System.out.println("\n業績總和："+total);
        System.out.println("平均業績："+total/sales.length);
    }
}
