import javax.swing.*;
import java.awt.*;

public class myTable extends JTable {
    Color color = getForeground();

    public myTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
    }

    //设置table中值不可编辑
    public boolean isCellEditable(int row,int col){
        return false;
    }
    @Override
    public java.awt.Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
        int modelRow = convertRowIndexToModel(row);
        int modelColumn = convertColumnIndexToModel(column);
        java.awt.Component comp = super.prepareRenderer(renderer, row, column);
        if (!isRowSelected(modelRow)) {
            switch ((State)Global.window.getTable().getValueAt(modelRow,modelColumn)){
                case mon:
                    comp.setBackground(Color.orange);
                    break;
                case ban:
                    comp.setBackground(Color.yellow);
                    break;
                case box:
                    comp.setBackground(Color.magenta);
                    break;
                case wall:
                    comp.setBackground(Color.BLACK);
                    break;
                case road:
                    comp.setBackground(Color.BLUE);
                    break;
                case search:
                    comp.setBackground(Color.cyan);
                    break;
                default:
                    comp.setBackground(Color.WHITE);
            }
        }
        return comp;
    }

}
