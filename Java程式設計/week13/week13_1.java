import java.util.LinkedList;

public class week13_1 {
    public static void main(String args[]){
        LinkedList myList = new LinkedList();
        myList.add("你");
        myList.add("好");
        int myListSize= myList.size();
        for(int i=0; i<myListSize; i++){
            String tmp=(String)myList.get(i);
            System.out.println("第"+i+"個節點中的資料："+tmp);
        }
    }
}
