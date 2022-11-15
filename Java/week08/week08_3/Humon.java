package week08.week08_3;

public class Humon extends Submarine{
    public Humon(String name) {
        super(name);
    }
    @Override
    public void swim() {
        System.out.printf("淺水挺 %s 潛行 %n",name);
    }
}
