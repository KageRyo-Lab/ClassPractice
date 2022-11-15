package week06.week06_2;

public class SonCMath extends CMath{
    public void getFactorial(int num){
        int ans=1;
        System.out.print(num+"!=");
        for(int i=0;i<num;i++){
            System.out.print(i+"*");
            ans=ans+i;
        }
        ans=ans*num;
        System.out.println(num+"="+ans);
    }
}
