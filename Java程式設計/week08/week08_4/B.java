package week08.week08_4;

public class B implements Computable{
    public int f(int x,int y) {
        return x*y;
    }
    public int g(int x) {
        return x/2;
    }

    @Override
    public int g(int x, int y) {
        return 0;
    }

    @Override
    public int f(int x) {
        return 0;
    }
}
