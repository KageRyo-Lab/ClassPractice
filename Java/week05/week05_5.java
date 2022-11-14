package week05;

// 二維陣列練習
public class week05_5 {
    public static void main(String args[]){
        int total=0;
        int[][] scores={{54,68},
                        {67,78},
                        {89,93}};
        for(int i=0; i<scores.length; i++){
            int sum=0;
            for(int j=0; j<scores[i].length; j++){
                System.out.println(scores[i][j]+" ");
                sum+=scores[i][j];
                total+=scores[i][j];
            }
            System.out.println("==> 總得分："+sum);
        }
        System.out.println("得分總和："+total);

        double[][] sales=new double[4][2];
        for(int i=0; i<sales.length; i++){
            sales[0][0]=123.4; sales[0][1]=143.5;
            sales[1][0]=142.3; sales[1][1]=198.4;
            sales[2][0]=234.6; sales[2][1]=200.5;
            sales[3][0]=167.1; sales[3][1]=150.4;
            System.out.println("業績報表：\t季\t\t第一年\t第二年");
            for(int j=0;j< sales.length;j++){
                System.out.print("\t\t\t第"+(j+1)+"季\t");
                for(int k=0;k<sales[i].length;k++){
                    System.out.print(sales[j][k]+"\t");
                }
                System.out.println();
            }
            break;
        }
    }
}
