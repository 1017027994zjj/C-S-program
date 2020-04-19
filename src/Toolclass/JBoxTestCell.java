package Toolclass;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import Dateclass.Goods;
import Dateclass.Order_goods;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
/**
 * 自定义celleditor实现
 * 指定单元格设置下拉框，其他单元格设置文本框
 */
public class JBoxTestCell extends AbstractCellEditor implements
        TableCellEditor {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public List<JComboBox<String>> jboxlist;
    int row;

    //  private Object val = null;
    public JBoxTestCell(JTable table) {
        jboxlist = new ArrayList<JComboBox<String>>();
        int Row = table.getRowCount();
        for (int i = 0; i < Row; i++) {
            String text = "";
            String ordernum = table.getValueAt(i, 0).toString();
            List<Order_goods> ordergoodslist = Order_goods.getOrder_GoodsList(ordernum);
            int listsize = ordergoodslist.size();
            String items[] = new String[listsize];
            for (int j = 0; j < listsize; j++) {
                String goodsname = Goods.getGoodsName(ordergoodslist.get(j).store_id, ordergoodslist.get(j).good_id);

                String price = String.valueOf(Goods.getGoodsPrice(ordergoodslist.get(j).store_id, ordergoodslist.get(j).good_id));
                String buynum = String.valueOf(ordergoodslist.get(j).buynum);
                text = goodsname + " " + price + " " + "X" + buynum;
                items[j] = text;

            }
            JComboBox<String> jbox = new JComboBox<String>(items);
            jbox.setPreferredSize(new Dimension(200, 25));
            jbox.setSelectedIndex(0);
            jboxlist.add(jbox);


        }
        for (int i = 0; i < jboxlist.size(); i++)
            System.out.println(jboxlist.get(i).getItemAt(0));
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    public void showSave() {
        for (int i = 0; i < jboxlist.size(); i++)
            jboxlist.get(i).setVisible(true);
    }

    public Object getCellEditorValue() {
        String v1 = jboxlist.get(row).getSelectedItem().toString();
        return v1;
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        return this.jboxlist.get(row);
    }
}
 