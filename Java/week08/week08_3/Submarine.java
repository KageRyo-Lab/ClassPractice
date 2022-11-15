package week08.week08_3;

public class Submarine implements Swimmer{
    protected String name;
    public Submarine(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }
    @Override
    public void swim() {
        System.out.printf("淺水挺 %s 潛行 %n",name);
    }
}
