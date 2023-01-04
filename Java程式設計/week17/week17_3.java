import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

public class week17_3 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile=new RandomAccessFile("data.txt","rw");
        FileChannel fileChannel=randomAccessFile.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(128);
        System.out.println("文件大小:"+fileChannel.size());
        while(fileChannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            String string = new String(byteBuffer.array());
            System.out.println(string);
        }
        Date date = new Date();
        String newData="read file time is:"+date;
        byteBuffer.clear();
        byteBuffer.put(newData.getBytes());
        byteBuffer.flip();
    }
}
