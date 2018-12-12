package SwingWorker;

import javax.swing.*;
import java.io.File;

public class FileCreator extends SwingWorker{
    protected String fileName;
    public FileCreator(String fileName) {
        this.fileName=fileName;
    }

    @Override
    protected Object doInBackground() throws Exception {
        File file = new File(fileName+".pert");
        file.createNewFile();
        return null;
    }
}
