package MainUI;

import UIElements.TextDialog;
import SwingWorker.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Menu {
    private Main main;
    private JMenu file;

    public Menu(Main main, JMenu file) {
        this.main = main;
        this.file = file;
    }

    public void addNewFile() {
        JMenuItem newFile = new JMenuItem("New");
        file.add(newFile);
        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextDialog dialog = new TextDialog();
                dialog.showDialog("New file", 350, 150, false, main.getX() + main.getWidth() / 2, main.getY() + main.getHeight() / 2);

                JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
                panel.add(new JLabel("File name:"));
                JTextField text = new JTextField();
                panel.add(text);
                dialog.add(panel);

                JButton ok = new JButton("OK");
                dialog.add(ok, BorderLayout.SOUTH);
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!text.getText().isEmpty()) {
                            boolean flag = true;
                            try {
                                new FileCreator(text.getText()).execute();
                                main.model.clear();
                                dialog.dispose();
                            } catch (Exception ex) {
                                new TextDialog().showDialog("  File Not Create!", 175, 150, true, main.getX() + main.getWidth() / 2, main.getY() + main.getHeight() / 2);
                                flag = false;
                            }
                            if (flag) {
                                main.create.setEnabled(true);
                                main.delete.setEnabled(true);
                                main.change.setEnabled(true);
                                main.calculate.setEnabled(true);
                                main.setEnabled(true);
                                main.setEnabled(true);
                                main.model.setFileName(text.getText() + ".pert");
                                new TextDialog().showDialog("  File  was successfully created", 200, 100, true, main.getX() + main.getWidth() / 2, main.getY() + main.getHeight() / 2);
                            }
                        }
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public void addOpen() {
        JMenuItem open = new JMenuItem("Open");
        file.add(open);
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = true;
                main.model.clear();
                JFileChooser openFile = new JFileChooser();
                openFile.setCurrentDirectory(new File("."));
                int res = openFile.showDialog(null, "Open file");
                if (res == JFileChooser.APPROVE_OPTION) {
                    File file = openFile.getSelectedFile();
                    try {
                        String extension = getFileExtension(file);
                        if (extension == null || !extension.equals("pert")) throw new Exception();
                        new FileReader(file.getAbsolutePath(), main.model).execute();
                    } catch (Exception ex) {
                        new TextDialog().showDialog(" Incorrect Selected File!", 175, 150, true, main.getX() + main.getWidth() / 2, main.getY() + main.getHeight() / 2);
                        flag = false;
                    }
                    if (flag) {
                        main.create.setEnabled(true);
                        main.delete.setEnabled(true);
                        main.change.setEnabled(true);
                        main.calculate.setEnabled(true);
                        new TextDialog().showDialog("  File  was successfully opened", 200, 100, true, main.getX() + main.getWidth() / 2, main.getY() + main.getHeight() / 2);
                    }
                    main.setVisible(true);
                }
            }
        });
    }

    public void addSave() {
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileSaver(main.model.getComponents(), main.model.getFileName()).execute();
                new TextDialog().showDialog("  File  was successfully saved", 200, 100, true, main.getX() + main.getWidth() / 2, main.getY() + main.getHeight() / 2);
            }
        });
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return null;
    }
}
