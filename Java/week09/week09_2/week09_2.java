package week09.week09_2;

// 介面匿名類別練習
public class week09_2 {
    public static void main(String args[]){
        A a = new A();
        a.f(new Show() {
            @Override
            public void show() {
                System.out.println("這是實現介面的匿名類別");
            }
        });
    }
}
