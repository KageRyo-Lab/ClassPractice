package week07.week07_6;

// 繼承練習
public class week07_6 {
    public static void main(String args[]){
        Car car = new Car();
        PiliCar piliCar = new PiliCar();
        car.showSpeed("car1");
        piliCar.showSpeed("car2");
        piliCar.showSpeed("car2",180);
    }
}
