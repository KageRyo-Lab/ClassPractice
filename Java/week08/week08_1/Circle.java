package week08.week08_1;

public class Circle extends Shape implements ShapeInterface{
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
}
