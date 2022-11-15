package week07.week07_5;

public class B extends A{
    int n3;
    B(int n1,int n2){
        super(n1,n2);
        n3=300;
    }
    B(){
        super();
        n3=300;
    }
    public void output(){
        System.out.println("n1="+n1+", n2="+n2+", n3="+n3);
    }
}
