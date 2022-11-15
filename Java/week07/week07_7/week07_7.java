package week07.week07_7;

// 抽象類別練習
public class week07_7 {
    public static void main(String args[]){
        Shape shape;
        Circle circle = new Circle(5.0,10.0,4.0);
        Rectangle rectangle = new Rectangle(10.0,10.0,20.0,20.0);
        for(int i=0;i<2;i++){
            if(i==0){
                shape=circle;
            }else{
                shape=rectangle;
            }
            shape.area();
        }
    }
}
