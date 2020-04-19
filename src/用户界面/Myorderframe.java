package 用户界面;
import 登陆界面.Login;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import Dateclass.Order_goods;
import Dateclass.Store;
import Dateclass.Userorder;
import Toolclass.JBoxTestCell;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class Myorderframe {
    public JFrame orderframe;
    private JTable table;

    /**
     * Launch the application.
     */
    public Myorderframe() {
        String usernum = Login.usernum;
        orderframe = new JFrame();
        orderframe.getContentPane().setFont(new Font("宋体", Font.BOLD, 16));
        //orderframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        orderframe.getContentPane().setLayout(null);

        table = new JTable();
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(654, 347);
        scrollPane.setLocation(10, 52);
        orderframe.getContentPane().add(scrollPane);
        List<Userorder> userorders = Userorder.getAllUserorder(usernum);
        refreshUserorderTable(userorders, dtm);

        JButton btnNewButton = new JButton("\u53D6\u6D88\u8BA2\u5355");
        btnNewButton.setIcon(new ImageIcon("src/images/取消订单.png"));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    String value = table.getValueAt(i, 7).toString();//读取你获取行号的某一列的值（也就是字段）
                    String ordernum = table.getValueAt(i, 0).toString();
                    String orderstatus = table.getValueAt(i, 6).toString();
                    if (value == "true") {
                        if (orderstatus.equals("等待商家接单")) {
                            JOptionPane.showMessageDialog(null, "申请退款中", "提示", JOptionPane.PLAIN_MESSAGE);
                            Userorder.updateOrderstatus(ordernum, "申请退款");
                        } else {
                            JOptionPane.showMessageDialog(null, "不满足申请条件，如有需要请联系商家！", "退款失败", JOptionPane.PLAIN_MESSAGE);
                        }

                    }
                }
                refreshUserorderTable(Userorder.getAllUserorder(usernum), dtm);
            }
        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton.setBounds(674, 100, 140, 30);
        orderframe.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("联系商家");
        btnNewButton_1.setIcon(new ImageIcon("src/images/联系商家.png"));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//联系商家
                for (int i = 0; i < table.getRowCount(); i++) {
                    String value = table.getValueAt(i, 7).toString();//读取你获取行号的某一列的值（也就是字段）
                    String ordernum = table.getValueAt(i, 0).toString();
                    if (value == "true") {
                        JOptionPane.showMessageDialog(null, Userorder.Getstorephone(ordernum), "商家联系电话:", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_1.setBounds(674, 168, 140, 30);
        orderframe.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("\u5F85\u8BC4\u4EF7");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            //获得待评价的订单
                List<Userorder> userorders = Userorder.getToBeEvaluatedUserorder(usernum);
                refreshUserorderTable(userorders, dtm);
            }
        });
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_2.setBounds(338, 10, 97, 25);
        orderframe.getContentPane().add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("\u5DF2\u5B8C\u6210");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            //获得已完成的订单
                String orderstatus = "订单已完成";
                List<Userorder> userorders = Userorder.getUserorder(usernum, "orderstatus", orderstatus);
                refreshUserorderTable(userorders, dtm);
            }
        });
        btnNewButton_3.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_3.setBounds(180, 10, 97, 25);
        orderframe.getContentPane().add(btnNewButton_3);

        JButton button = new JButton("\u8BC4\u4EF7");
        button.setIcon(new ImageIcon("src/images/评价.png"));
        button.addActionListener(new ActionListener() {//评价
            //btnNewButton
            public void actionPerformed(ActionEvent e) {
                /**
                 *
                 评价窗口
                 */
                for (int i = 0; i < table.getRowCount(); i++) {
                    String value = table.getValueAt(i, 7).toString();//读取你获取行号的某一列的值（也就是字段）
                    String orderstatus = table.getValueAt(i, 6).toString();
                    String ordernum = table.getValueAt(i, 0).toString();
                    if (value == "true") {
                        if (orderstatus.equals("订单已完成")) {
                            commentfram window = new commentfram();
                            window.setOrdernum(ordernum);
                            window.evaluateframe.setVisible(true);
                            //评价窗口结束
                        } else if (orderstatus.equals("订单已评价"))
                            JOptionPane.showMessageDialog(null, "订单已经评价过了！", "提示", JOptionPane.PLAIN_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(null, "订单未完成不能评价！", "提示", JOptionPane.PLAIN_MESSAGE);
                    }
                }

            }
        });
        button.setFont(new Font("宋体", Font.BOLD, 16));
        button.setBounds(674, 240, 140, 30);
        orderframe.getContentPane().add(button);

        JButton btnNewButton_4 = new JButton("\u5168\u90E8\u8BA2\u5355");//获得用户的所有订单
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Userorder> userorders = Userorder.getAllUserorder(usernum);
                refreshUserorderTable(userorders, dtm);
            }
        });
        btnNewButton_4.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_4.setBounds(23, 10, 105, 25);
        orderframe.getContentPane().add(btnNewButton_4);

        JButton btnNewButton_5 = new JButton("再来一单");
        btnNewButton_5.setIcon(new ImageIcon("src/images/结算.png"));
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < table.getRowCount(); i++) {
                    String value = table.getValueAt(i, 7).toString();//读取你获取行号的某一列的值（也就是字段）
                    String ordernum = table.getValueAt(i, 0).toString();
                    if (value == "true") {
                        newgoodframe.ordergoodslist = Order_goods.getOrder_GoodsList(ordernum);

                        //进入选择商店后的窗口
                        Payframe window = new Payframe();
                        window.frame.setVisible(true);
                        Customerframe.setCenter(window.frame);
                    }
                }
            }
        });
        btnNewButton_5.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_5.setBounds(674, 315, 140, 30);
        orderframe.getContentPane().add(btnNewButton_5);

        JButton btnNewButton_6 = new JButton("退款");
        btnNewButton_6.setIcon(new ImageIcon("src/images/退款.png"));
        btnNewButton_6.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {        //获取已退款的订单
                String orderstatus = "已退款";
                List<Userorder> userorders = Userorder.getUserorder(usernum, "orderstatus", orderstatus);
                refreshUserorderTable(userorders, dtm);
            }
        });
        btnNewButton_6.setBounds(489, 10, 97, 25);
        orderframe.getContentPane().add(btnNewButton_6);
        orderframe.setTitle("\u6211\u7684\u8BA2\u5355");
        orderframe.setBounds(100, 100, 840, 484);
    }

    public void refreshUserorderTable(List<Userorder> userorders, DefaultTableModel dtm) {
        dtm.setRowCount(0);//清空列表
        String[] tableHeads = new String[]{"订单号", "商家名字", "所买物品", "总价", "下单时间", "预计送达时间", "订单状态", "是否选中"};
        dtm.setColumnIdentifiers(tableHeads);
        //设置JTable的表格数
        for (int i = 0; i < userorders.size(); i++) {
            Vector<Object> v = new Vector<Object>();
            v.add(userorders.get(i).ordernum);
            v.add(Store.getStoreName(userorders.get(i).store_id));
            v.add(null);
            v.add(Userorder.countTotalprice(userorders.get(i).ordernum));
            v.add(userorders.get(i).buytime);
            v.add(Userorder.makeServicetime(userorders.get(i).buytime, userorders.get(i).store_id));
            v.add(userorders.get(i).orderstatus);
            v.add(false);
            dtm.addRow(v);
        }
        /*****设置table的列模型****/
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(2).setCellEditor(new JBoxTestCell(table));

        //tcm.getColumn(7).setCellEditor(new DefaultCellEditor(new JCheckBox()));
       // tcm.getColumn(7).setCellRenderer(new TestTableCellRenderer());
        tcm.getColumn(7).setCellEditor(table.getDefaultEditor(Boolean.class));  
        tcm.getColumn(7).setCellRenderer(table.getDefaultRenderer(Boolean.class));  
        tcm.getColumn(7).setPreferredWidth(50);
        tcm.getColumn(7).setWidth(50);
        tcm.getColumn(0).setMaxWidth(70);
        tcm.getColumn(3).setMaxWidth(40);
        tcm.getColumn(4).setMaxWidth(50);
        tcm.getColumn(5).setMaxWidth(65);
        tcm.getColumn(6).setMaxWidth(80);
        tcm.getColumn(7).setMaxWidth(50);
    }

	private void setCellRenderer(TableCellRenderer defaultRenderer) {
		// TODO Auto-generated method stub
		
	}

	private void setCellEditor(TableCellEditor defaultEditor) {
		// TODO Auto-generated method stub
		
	}
}

