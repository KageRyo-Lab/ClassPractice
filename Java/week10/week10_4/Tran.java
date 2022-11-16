package week10.week10_4;

public class Tran extends Calc{
    public Tran(double x,double y){
        this.x=x;
        this.y=y;
    }
    public void area(){
        System.out.println("三角形面積："+(x+y)/2);
    }
}
