package 用户界面;

import 登陆界面.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Dateclass.Receiveaddress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MyReceivinginformationframe {
    private static DefaultTableModel df;
    public JFrame frame;
    private JTable table;
    /**
     * Launch the application.
     */

    public MyReceivinginformationframe() {
        initialize();
    }

    /**
     * Create the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyReceivinginformationframe window = new MyReceivinginformationframe();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void refreshTable() {
        df.setRowCount(0);//清空列表
        List<Receiveaddress> list = Receiveaddress.getReceiveAddress(Login.usernum);
        for (int i = 0; i < list.size(); i++) {
            Object data[] = {list.get(i).contact, list.get(i).address, list.get(i).phonenum,
                    list.get(i).sex};
            df.addRow(data);

        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 682, 407);
        frame.setTitle("\u6536\u8D27\u4FE1\u606F\u7BA1\u7406");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        List<Receiveaddress> list = Receiveaddress.getReceiveAddress(Login.usernum);
        int size = list.size();
        df = new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "\u8054\u7CFB\u4EBA\t", "\u5730\u5740", "\u8054\u7CFB\u7535\u8BDD", "\u6027\u522B", "\u662F\u5426\u9009\u4E2D"
                }
        ) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;
            @SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[]{
                    Object.class, Object.class, Object.class, Object.class, Boolean.class
            };

            @SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        };
        for (int i = 0; i < size; i++) {
            Object data[] = {list.get(i).contact, list.get(i).address, list.get(i).phonenum, list.get(i).sex};
            df.addRow(data);
        }

        table = new JTable(df);
        TableColumnModel tcm = table.getColumnModel();
        //tcm.getColumn(2).setWidth(185);
        tcm.getColumn(1).setMinWidth(135);
        tcm.getColumn(3).setMaxWidth(60);
        tcm.getColumn(4).setMaxWidth(60);
        //tcm.getColumn(3).setWidth(5);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);

        table.setDefaultRenderer(Object.class, r);
        table.setFont(new Font("宋体", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 38, 531, 317);
        frame.getContentPane().add(scrollPane);
        //refreshTable();

        JButton btnNewButton_1 = new JButton("新增地址");
        btnNewButton_1.setIcon(new ImageIcon("src/images/添加.png"));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//新增地址按钮
                Addaddressframe window = new Addaddressframe();
                window.frame.setVisible(true);

            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton_1.setBounds(549, 10, 109, 23);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("修改");
        btnNewButton_2.setIcon(new ImageIcon("src/images/修改.png"));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//修改功能
                boolean ifchange = false;//是否改变的标志
                for (int i = 0; i < table.getRowCount(); i++) {
                    String contact = table.getValueAt(i, 0).toString();
                    String newaddress = table.getValueAt(i, 1).toString();
                    String newphonenum = table.getValueAt(i, 2).toString();
                    String newsex = table.getValueAt(i, 3).toString();
                    String value;
                    try {
                        value = table.getValueAt(i, 4).toString();
                    } catch (Exception e2) {
                        value = "false";
                    }
                    if (value.equals("true")) {
                        ifchange = true;
                        Receiveaddress receiveaddress = new Receiveaddress();
                        receiveaddress.usernum = Login.usernum;
                        receiveaddress.contact = contact;
                        receiveaddress.address = newaddress;
                        receiveaddress.phonenum = newphonenum;
                        receiveaddress.sex = newsex;
                        Receiveaddress.updateAddress(receiveaddress);
                    }
                }
                refreshTable();
                if (ifchange == true)
                    JOptionPane.showMessageDialog(null, "修改成功", "提示:", JOptionPane.PLAIN_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "未作任何修改", "提示:", JOptionPane.PLAIN_MESSAGE);
            }
        });
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton_2.setBounds(551, 154, 93, 23);
        frame.getContentPane().add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("删除");
        btnNewButton_3.setIcon(new ImageIcon("src/images/删除.png"));
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//删除地址按钮
                for (int i = 0; i < table.getRowCount(); i++) {
                    String contact = table.getValueAt(i, 0).toString();
                    String value;
                    try {
                        value = table.getValueAt(i, 4).toString();
                    } catch (Exception e2) {
                        value = "false";
                    }
                    if (value.equals("true")) {
                        Receiveaddress.deleteAddress(Login.usernum, contact);
                        refreshTable();
                    }
                }
            }
        });
        btnNewButton_3.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton_3.setBounds(551, 216, 93, 23);
        frame.getContentPane().add(btnNewButton_3);

    }
}