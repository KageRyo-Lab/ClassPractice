package week07.week07_7;

public class Circle extends Shape{
    public double r;
    public Circle(double x,double y,double r){
        this.x=x;
        this.y=y;
        this.r=r;
    }
    public void area() {
        System.out.println("圓面積："+Math.round(Math.PI*r*r*100.0)/100.0);
    }
}
