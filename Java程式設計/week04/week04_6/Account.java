package week04.week04_6;

public class Account {
    private double balance;
    public Account(){
        this.balance=0;
    }
    public Account(double balance){
        this.balance = balance;
    }
    public double getBalance(){
        return balance;
    }
    public void deposit(double amt){
        balance+=amt;
    }
    public void withdraw(double amt){
        balance+=amt;
    }
    public void addInterest(double rate){
        double amt = balance*rate/100;
        deposit(amt);
    }
}
