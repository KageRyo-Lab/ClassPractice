package week08.week08_2;

public class Circle extends Shape implements ShapeInterface {
    private double r;
    public Circle(double x,double y,double r){
        this.x=x;
        this.y=y;
        this.r=r;
    }
    public void area() {
        System.out.println("圓面積："+PI*r*r);
    }
    public void perimeter() {
        System.out.println("圓周長："+2.0*PI*r);
    }

    public void show(){
        System.out.println("X座標："+x);
        System.out.println("Y座標："+y);
        System.out.println("圓半徑："+r);
    }
}
