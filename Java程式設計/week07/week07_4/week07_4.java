package week07.week07_4;

// 類別繼承練習
public class week07_4 {
    public static void main(String args[]){
        Animal animal = new Animal();
        Dog dog = new Dog();
        animal.eat();
        dog.eat();

        System.out.print("super呼叫父類別：");
        dog.eat1();
        System.out.print("this呼叫自己：");
        dog.eat2();
    }
}
