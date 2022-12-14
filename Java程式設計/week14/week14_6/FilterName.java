package week14_6;

import java.io.File;
import java.io.FilenameFilter;

public class FilterName implements FilenameFilter {
    String extendName;

    public void setExtendName(String extendName) {
        extendName=extendName;
    }
    public boolean accept(File dir, String name) {
        return name.endsWith(extendName);
    }
}
