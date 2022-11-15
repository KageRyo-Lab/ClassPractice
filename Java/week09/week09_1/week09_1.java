package week09.week09_1;

// 匿名練習
public class week09_1 {
    public static void main(String args[]){
        Teacher teacher = new Teacher();
        teacher.look(new Student() {
            @Override
            void speak() {
                System.out.println("這是匿名類別中的方法");
            }
        });
    }
}
