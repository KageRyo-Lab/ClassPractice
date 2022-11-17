package week08.week08_3;

public class Anemonefish extends Fish{
    public Anemonefish(String name){
        super(name);
    }
    @Override
    public void swim(){
        System.out.printf("小丑魚 %s 游泳 %n",name);
    }
}
