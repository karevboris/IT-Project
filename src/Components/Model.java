package Components;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractTableModel {

    private List<Component> components = new ArrayList<>();
    private String fileName;

    public void setFileName(String fileName){
        this.fileName = fileName;
        fireTableDataChanged();
    }

    public void deleteComponent(int row){
        components.remove(row);
        fireTableDataChanged();
    }

    public Component getComponent(int row){
        return components.get(row);
    }

    public List<Component> getComponents(){
        return components;
    }

    public String getFileName(){
        return fileName;
    }

    public void setComponent(int row, Component component){
        components.set(row, component);
        fireTableDataChanged();
    }

    public void clear(){
        components.clear();
        fireTableDataChanged();
    }

    public void addComponent(Component component){
        components.add(component);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return components.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Component component = components.get(rowIndex);
        switch (columnIndex){
            case 0:
                return component.getName();
            case 1:
                return component.getMi();
            case 2:
                return component.getOi();
            case 3:
                return component.getPi();
            case 4:
                return component.getCount();
            case 5:
                return component.getEstimation();
            case 6:
                return component.getDeviation();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "<html><center>Name of project<br>component";
            case 1:
                return "<html><center>Index Mi";
            case 2:
                return "<html><center>Index Oi";
            case 3:
                return "<html><center>Index Pi";
            case 4:
                return "<html><center>Count";
            case 5:
                return "<html><center>Estimation of complexity<br>(human*hour<br>/count)";
            case 6:
                return "<html><center>Deviation<br>(human*hour<br>/count)";
        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return Double.class;
            case 2:
                return Double.class;
            case 3:
                return Double.class;
            case 4:
                return Integer.class;
            case 5:
                return Double.class;
            case 6:
                return Double.class;
        }
        return Object.class;
    }
}
