import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class week14_1 {
    public static void main(String args[]){
        HashMap<String,String> hashMap=new HashMap<>();
        String key = "Tom", name="卡麥隆";
        System.out.println("新增的元素前是否是空的="+hashMap.isEmpty());

        hashMap.put("Joe",name);
        hashMap.put("Jane","泰諾克");
        hashMap.put(key,"憂鬱之島");
        hashMap.put("Hueyan",name);

        System.out.println("新增後尺寸="+hashMap.size());
        System.out.println("是否是空的="+hashMap.isEmpty());
        System.out.println("HashMap內容："+hashMap);
        System.out.println("HashMap是否有["+key+"]："+hashMap.containsKey(key));
        System.out.println("HashMap是否有["+name+"]："+hashMap.containsValue(name));

        Set<String> keys= hashMap.keySet();
        System.out.println("Keys內容："+keys);
        Collection<String> values= hashMap.values();
        System.out.println("Values內容："+values);
        hashMap.remove(key);
        System.out.println("HashMap刪除["+keys+"]："+hashMap);
        hashMap.clear();
    }
}
