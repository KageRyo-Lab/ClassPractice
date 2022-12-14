import java.io.File;
import java.io.IOException;

public class week14_7 {
    public static void main(String args[]){
        try{
            Runtime runtime=Runtime.getRuntime();
            File file=new File("C:\\windows\\system32","notepad.exe");
            runtime.exec(file.getAbsolutePath());
        } catch (IOException e) {}
    }
}
