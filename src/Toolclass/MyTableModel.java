package Toolclass;
import javax.swing.table.DefaultTableModel;
//设置表格具体哪些列可以编辑
class MyTableModel extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    public boolean isCellEditable(int row, int column) {
        if (column == 0 || column == 1||column == 4)
            return false;
        else
            return true;
    }

}