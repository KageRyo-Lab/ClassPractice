package week06.week06_3;

// 繼承練習
public class week06_3 {
    public static void main(String args[]){
        int n1=5,n2=3;
        Son son = new Son();
        GrandSon grandSon = new GrandSon();
        son.changeMoneyNTD(666);
        son.changeMoneyUSD(5000);

        System.out.println("兒子的台幣是繼承的屬性，當前的值為："+son.moneyNTD);
        System.out.println("兒子的美金是新增的屬性，當前的值為："+son.moneyUSD);
        System.out.printf("減法是兒子新增的功能,%d-%d=%d\n",n1,n2,son.subs(n1,n2));
        System.out.printf("加法是兒子繼承的功能,%d+%d=%d\n",n1,n2,son.add(n1,n2));
        System.out.println("孫子的台幣和美金都是繼承的屬性,當前的值是：");
        System.out.println("台幣："+grandSon.moneyNTD+"　美金："+grandSon.moneyUSD);
        System.out.printf("乘法是兒子新增的功能,%d*%d=%d\n",n1,n2,grandSon.multi(n1,n2));
        System.out.printf("加法是兒子新增的功能,%d+%d=%d\n",n1,n2,grandSon.add(n1,n2));
        System.out.printf("減法是兒子新增的功能,%d-%d=%d\n",n1,n2,grandSon.subs(n1,n2));
    }
}
