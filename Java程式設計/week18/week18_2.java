import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class week18_2 {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<Integer>();
        set.add(88);
        set.add(12);
        set.add(456);
        set.add(22);
        set.add(65);

        // (1)找出最大值和最小值的位置
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int minIndex = 0;
        int maxIndex = 0;
        int index = 0;
        for (Integer value : set) {
            if (value < min) {
                min = value;
                minIndex = index;
            }
            if (value > max) {
                max = value;
                maxIndex = index;
            }
            index++;
        }
        System.out.println("最小值：" + min + "，位置：" + minIndex);
        System.out.println("最大值：" + max + "，位置：" + maxIndex);

        // (2)將最小值改成100
        Iterator<Integer> iterator = set.iterator();
        for (int i = 0; i < minIndex; i++) {
            iterator.next();
        }
        set.remove(min);
        set.add(100);

        // (3)將集合內元素由小到大排列顯示
        System.out.println("集合內元素由小到大排列：" + set);

        // (4)再一次找出最大值與最小值位置
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        minIndex = 0;
        maxIndex = 0;
        index = 0;
        for (Integer value : set) {
            if (value < min) {
                min = value;
                minIndex = index;
            }
            if (value > max) {
                max = value;
                maxIndex = index;
            }
            index++;
        }
        System.out.println("最小值：" + min + "，位置：" + minIndex);
        System.out.println("最大值：" + max + "，位置：" + maxIndex);
    }
}
