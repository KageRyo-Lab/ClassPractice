import java.util.TreeMap;

public class week14_2 {
    public static void main(String args[]){
        TreeMap<Integer,String> treeMap=new TreeMap<Integer,String>();  // 建立 TreeMap 物件
        treeMap.put(10,"Geeks");  // 將鍵值為 10，內容為 "Geeks" 的鍵值對加入 TreeMap
        treeMap.put(15,"4");  // 將鍵值為 15，內容為 "4" 的鍵值對加入 TreeMap

        System.out.println("Inital Mappings are: "+treeMap);  // 印出初始的 TreeMap 物件
        String returnedValue=(String)treeMap.put(20,"ALL");  // 將鍵值為 20，內容為 "ALL" 的鍵值對加入 TreeMap，並將舊的內容存到 returnedValue 變數中
        System.out.println("Returned Value is: "+returnedValue);  // 印出 returnedValue 變數的值
        System.out.println("New map is: "+treeMap);  // 印出新的 TreeMap 物件
    }
}