package week07.week07_4;

public class Dog extends Animal{
    void eat(){
        System.out.println("Dog子類別");
    }
    void eat1(){
        super.eat();
    }
    void eat2(){
        this.eat();
    }
}
