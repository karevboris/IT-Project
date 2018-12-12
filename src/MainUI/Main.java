package MainUI;

import Components.*;
import Components.Component;
import Method.Pert;
import UIElements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;

public class Main extends JFrame {

    JTextField nameField, miField, oiField, piField, countField;
    Model model;
    JTable table;
    JButton create, delete, change, calculate;
    JLabel label;
    JLabel value;

    public Main() {
        super("Method PERT");
        setSize(1000, 450);
        setLocation(300, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        nameField = new JTextField();
        miField = new JTextField();
        oiField = new JTextField();
        piField = new JTextField();
        countField = new JTextField();

        model = new Model();
        table = new JTable(model);
        table.getTableHeader().setPreferredSize(
                new Dimension(table.getColumnModel().getTotalColumnWidth(), 90));
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane, BorderLayout.CENTER);

        create = new JButton("Create");
        create.setEnabled(false);
        delete = new JButton("Delete");
        delete.setEnabled(false);
        change = new JButton("Change");
        change.setEnabled(false);
        calculate = new JButton("Calculate total estimation");
        calculate.setEnabled(false);
        label = new JLabel("Total estimation of complexity:");
        label.setHorizontalAlignment(JLabel.CENTER);
        value = new JLabel("");
        value.setHorizontalAlignment(JLabel.CENTER);

        JPanel grid = new JPanel(new GridLayout(6, 1, 0, 5));

        grid.add(create);
        grid.add(delete);
        grid.add(change);
        grid.add(calculate);
        grid.add(label);
        grid.add(value);

        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                value.setText(String.valueOf(Pert.calculateTotalEstimation(model.getComponents())));
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) model.deleteComponent(row);
            }
        });

        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) openDialog("Change component", model.getComponent(table.getSelectedRow()));
            }
        });

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDialog("New component", null);
            }
        });

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(grid);
        add(flow, BorderLayout.EAST);

        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        Menu item = new Menu(this, file);
        item.addNewFile();
        item.addOpen();
        item.addSave();

        menu.add(file);
        setJMenuBar(menu);
        setVisible(true);
    }

    private void openDialog(String name, Component component) {
        TextDialog dialog = new TextDialog();
        dialog.showDialog(name, 350, 250, false, getX() + getWidth() / 2, getY() + getHeight() / 2);

        EditPanel panel = new EditPanel(new GridLayout(5, 2, 5, 5));
        panel.create(nameField, miField, oiField, piField, countField);
        dialog.add(panel);

        if (component != null) {
            nameField.setText(component.getName());
            miField.setText(String.valueOf(component.getMi()));
            oiField.setText(String.valueOf(component.getOi()));
            piField.setText(String.valueOf(component.getPi()));
            countField.setText(String.valueOf(component.getCount()));
        }

        JButton ok = new JButton("OK");
        dialog.add(ok, BorderLayout.SOUTH);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = true;
                double mi = 0;
                double oi = 0;
                double pi = 0;
                int count = 0;

                try {
                    mi = Double.valueOf(miField.getText());
                    oi = Double.valueOf(oiField.getText());
                    pi = Double.valueOf(piField.getText());
                    count = Integer.valueOf(countField.getText());
                    if ((mi <= 0) || (oi <= 0) || (pi <= 0) || (count <= 0)) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    new TextDialog().showDialog("  Incorrect Number Format!", 175, 150, true, getX() + getWidth() / 2, getY() + getHeight() / 2);
                    flag = false;
                }

                if (nameField.getText().isEmpty()) {
                    new TextDialog().showDialog("  Remained Empty Field!", 175, 150, true, getX() + getWidth() / 2, getY() + getHeight() / 2);
                    flag = false;
                }

                if (flag) {
                    double estimation = Pert.calculateEstimation(mi, oi, pi);
                    double deviation = Pert.calculateDeviation(oi, pi);
                    if (component != null) {
                        model.setComponent(table.getSelectedRow(), new Component(nameField.getText(), mi, oi, pi, count, estimation, deviation));
                    } else {
                        model.addComponent(new Component(nameField.getText(), mi, oi, pi, count, estimation, deviation));
                    }
                    dialog.dispose();
                }
            }
        });
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
