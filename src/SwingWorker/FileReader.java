package SwingWorker;

import Components.Model;
import Components.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileReader extends FileCreator {
    protected Model model;
    public FileReader(String fileName, Model model) {
        super(fileName);
        this.model = model;
    }

    @Override
    protected Object doInBackground() throws Exception {
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        String data;
        while (scanner.hasNext()) {
            data = scanner.useDelimiter("\n").next();

            StringTokenizer stok = new StringTokenizer(data, "?@");

            String name = stok.nextToken();
            double mi = Double.parseDouble(stok.nextToken());
            double oi = Double.parseDouble(stok.nextToken());
            double pi = Double.parseDouble(stok.nextToken());
            int count = Integer.parseInt(stok.nextToken());
            double estimation = Double.parseDouble(stok.nextToken());
            double deviation = Double.parseDouble(stok.nextToken());

            model.getComponents().add(new Component(name, mi, oi, pi, count, estimation, deviation));

            model.setFileName(fileName);
        }
        scanner.close();
        return null;
    }
}
