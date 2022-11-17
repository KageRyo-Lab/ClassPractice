package week10.week10_5;

public class Tran extends Shape implements ShapeInterface{
    public void f(Calc calc){
        calc.calc();
    }
    public void area(double x,double y) {
        System.out.println("三角形面積："+(x+y)/2);
    }

    @Override
    public void area() {

    }
}
