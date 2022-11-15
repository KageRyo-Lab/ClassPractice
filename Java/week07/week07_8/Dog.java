package week07.week07_8;

public class Dog implements Animal{
    public void eat() {
        System.out.println("Dog eats");
    }
    public void run() {
        System.out.println("Dog runs");
    }
    public int noOfLegs(){
        return 0;
    }
}
