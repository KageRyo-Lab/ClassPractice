package week04.week04_6;

// 物件導向練習(class分開)
public class week04_6 {
    public static void main(String args[]){
        Account accountA = new Account(2000);
        Account accountB = new Account(4000);
        System.out.println("帳戶A(初始)："+accountA.getBalance());
        System.out.println("帳戶B(初始)："+accountB.getBalance());
        accountA.withdraw(1000);
        accountB.deposit(1000);
        System.out.println("帳戶A（交易後）："+accountA.getBalance());
        System.out.println("帳戶B（交易後）："+accountB.getBalance());
        accountA.addInterest(1);
        System.out.println("帳號A（利息1%）："+accountA.getBalance());
        System.out.println("帳號B（利息1%）："+accountB.getBalance());
    }
}
