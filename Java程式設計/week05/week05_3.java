package week05;

// 字串陣列練習
public class week05_3 {
    public static void main(String args[]){
        String[] name = new String[5];
        name[0]="楊過";
        name[1]="小龍女";
        name[2]="金庸";
        name[3]="江小魚";
        name[4]="陳浩南";
        for(String i:name){
            System.out.println("\""+i+"\"（字串長度："+i.length()+"）");
        }
    }
}
