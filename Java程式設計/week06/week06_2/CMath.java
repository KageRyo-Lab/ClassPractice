package week06.week06_2;

public class CMath {
    public void getMath(int n1,int n2){
        int bigNum;
        if(n1>n2)bigNum=n1;
        else bigNum=n2;
        System.out.println(n1+"與"+n2+"最大數為："+bigNum);
    }
}
