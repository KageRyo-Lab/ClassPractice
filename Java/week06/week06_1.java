package week06;

// StringBuffer練習
public class week06_1 {
    public static void main(String args[]){
        StringBuffer stringBuffer = new StringBuffer("程式");
        char[] charArr={'j','a','v','a'};
        System.out.println("原始字串內容："+stringBuffer);

        stringBuffer.append('-');
        System.out.println("新增字元\'-\'："+stringBuffer);

        stringBuffer.append(charArr,0,4);
        System.out.println("新增字元陣列："+stringBuffer);

        stringBuffer.append("程式範例教本");
        System.out.println("新增字串："+stringBuffer);

        stringBuffer.deleteCharAt(2);
        System.out.println("刪除第3個字元："+stringBuffer);

        stringBuffer.delete(0,2);
        System.out.println("刪除0~2（前2個）字："+stringBuffer);

        stringBuffer.insert(4,"SE");
        System.out.println("新增英文字串："+stringBuffer);

        stringBuffer.setCharAt(5,'E');
        System.out.println("取代英文字元："+stringBuffer);
    }
}
