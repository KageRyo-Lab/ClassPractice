import java.io.IOException;
import java.io.RandomAccessFile;

public class week16_3 {
    public static void main(String args[]){
        try{
            RandomAccessFile randomAccessFile=new RandomAccessFile("file","rw");
            randomAccessFile.writeInt(10);
            randomAccessFile.writeDouble(3.14159);
            randomAccessFile.writeUTF("UTF字串");
            randomAccessFile.writeBoolean(true);
            randomAccessFile.writeShort(100);
            randomAccessFile.writeLong(12345678);
            randomAccessFile.writeUTF("又一個UTF字串");
            randomAccessFile.writeFloat(3.14f);
            randomAccessFile.writeChar('a');
            randomAccessFile.seek(0);
            System.out.println("-- 從file檔起始位置開始讀數據 --");
            System.out.println(randomAccessFile.readInt());
            System.out.println(randomAccessFile.readDouble());
            System.out.println(randomAccessFile.readUTF());
            randomAccessFile.skipBytes(3);
            System.out.println(randomAccessFile.readLong());
            randomAccessFile.skipBytes(randomAccessFile.readShort());
            System.out.println(randomAccessFile.readFloat());
            randomAccessFile.close();
        }catch (IOException e){
            System.out.println("檔讀寫錯誤！");
        }
    }
}