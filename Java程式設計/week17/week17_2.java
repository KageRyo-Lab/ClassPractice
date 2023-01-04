import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class week17_2 {
    public static void main(String[] args) throws IOException {
        System.out.println("請輸入資料若輸入\"exit\"則結束");
        ReadableByteChannel readableByteChannel = Channels.newChannel(System.in);
        WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while(readableByteChannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            String string = new String(byteBuffer.array()).trim();
            if(string.equals("exit")){
                readableByteChannel.close();
                writableByteChannel.close();
                break;
            }
            System.out.print("輸入資料=");
            writableByteChannel.write(byteBuffer);
            while(byteBuffer.hasRemaining()){
                writableByteChannel.write(byteBuffer);
            }
            byteBuffer.clear();
        }
    }
}
