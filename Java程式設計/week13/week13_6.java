import java.util.HashSet;

public class week13_6 {
    public static void main(String args[]){
        HashSet<String> hset = new HashSet<>();
        String name0="阿凡達";
        String name1="終局之戰";
        System.out.println("新增元素前是否是空的="+hset.isEmpty());
        hset.add("無限之戰");
        hset.add(name0);
        hset.add("鐵達尼號");
        System.out.println("新增後尺寸="+hset.size());
        System.out.println("是否是空的="+hset.isEmpty());
        System.out.print("HashSet內容：");
        System.out.println(hset);
        System.out.println("HashSet是否有["+name0+"]: "+hset.contains(name0));
        System.out.println("HashSet是否有["+name1+"]: "+hset.contains(name1));
        hset.remove(name0);
        System.out.print("HashSet刪除["+name0+"]: ");
        System.out.println(hset);
        hset.clear();
    }
}
