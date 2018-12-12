package SwingWorker;

import Components.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileSaver extends FileCreator {
    protected List<Component> components;

    public FileSaver(List<Component> components, String fileName) {
        super(fileName);
        this.components = components;
    }

    @Override
    protected Object doInBackground() throws Exception {
        StringBuilder data = new StringBuilder();

        for (Component component : components) {
            data.append(component.getName()).append("?@").append(component.getMi()).append("?@").append(component.getOi())
                    .append("?@").append(component.getPi()).append("?@").append(component.getCount())
                    .append("?@").append(component.getEstimation()).append("?@").append(component.getDeviation()).append("\n");
        }

        File file = new File(fileName);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
