package week07.week07_1;

// 繼承練習
public class week07_1 {
    public static void main(String args[]){
        animal animal = new animal();
        bird bird = new bird();
        dog dog = new dog();
        monkey monkey = new monkey();
        animal.sound();
        bird.sound(1);
        dog.sound(1,"狗叫");
        monkey.sound("狗叫",1);
    }
}
