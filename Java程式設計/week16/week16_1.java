import java.io.*;

public class week16_1 {
    public static void main(String args[]) {
        File file = new File("data.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeChar('A');
            dataOutputStream.writeInt(10);
            dataOutputStream.writeLong(88888888);
            dataOutputStream.writeFloat(3.14f);
            dataOutputStream.writeDouble(3.1415926897);
            dataOutputStream.writeChars("hello, everyone!");
        } catch (IOException e) {
        }
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            System.out.println(dataInputStream.readBoolean());
            System.out.println(dataInputStream.readChar());
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readLong());
            System.out.println(dataInputStream.readFloat());
            System.out.println(dataInputStream.readDouble());
            char c='\0';
            while((c= dataInputStream.readChar())!='\0'){
                System.out.print(c);
            }
        }catch (IOException e){
        }
    }
}
