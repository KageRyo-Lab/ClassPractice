import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class week17_1 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(40);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(40);
        String str1 = "Java NIO reader";
        byte[] b1 = str1.getBytes();
        char[] str2 = "Java NIO writer".toCharArray();
        CharBuffer charBuffer = byteBuffer2.asCharBuffer();
        for(byte i:b1){
            byteBuffer1.put(i);
        }
        System.out.print("str1=");
        System.out.println(str1);
        System.out.print("str2=");
        System.out.println(str2);
        System.out.print("b1=");
        System.out.println(b1);
        System.out.print("byteBuffer1=");
        System.out.println(byteBuffer1);
    }
}
