package week04;

// 傳遞呼叫練習
class Some{
    void someMethod(int i){
        System.out.println("i被呼叫");
    }
    void someMethod(){
        System.out.println("無參數傳遞被呼叫");
    }
}
public class week04_4 {
    public static void main(String args[]){
        var some = new Some();
        some.someMethod();
        some.someMethod(0);
    }
}
