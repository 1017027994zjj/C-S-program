package 用户界面;

import 商家界面.Storeorderframe;
import 登陆界面.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Dateclass.*;
import Toolclass.Mytablerencender;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class newgoodframe {
    public static List<Order_goods> ordergoodslist;//记录订单中的物品编号和数量
    public JFrame frame3;
    private JTable table_001;
    private JTextField textField001;

    /**
     * Create the application.
     */
    public newgoodframe() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    newgoodframe window = new newgoodframe();
                    window.frame3.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        String usernum = Login.usernum;
        int store_id = Customerframe.store_id;

        frame3 = new JFrame();
        frame3.setTitle("欢迎来到" + Store.getStoreName(store_id));//设置标题
        frame3.setBounds(100, 100, 970, 760);
        frame3.getContentPane().setLayout(null);

        table_001 = new JTable();
        table_001.setFont(new Font("宋体", Font.BOLD, 12));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table_001.setDefaultRenderer(Object.class, r);
        table_001.setRowHeight(100);
        DefaultTableModel dtm = (DefaultTableModel) table_001.getModel();
        JScrollPane scrollPane = new JScrollPane(table_001);
        scrollPane.setSize(718, 500);
        scrollPane.setLocation(10, 180);
        frame3.getContentPane().add(scrollPane);

        JLabel lblNewLabel001 = new JLabel("\u8F93\u5165\u5546\u54C1\u540D\uFF1A");
        lblNewLabel001.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel001.setBounds(10, 51, 97, 19);
        frame3.getContentPane().add(lblNewLabel001);

        textField001 = new JTextField();
        textField001.setFont(new Font("宋体", Font.BOLD, 14));
        textField001.setBounds(150, 50, 138, 21);
        frame3.getContentPane().add(textField001);
        textField001.setColumns(10);

        JButton btnNewButton005 = new JButton("搜索");
        btnNewButton005.setIcon(new ImageIcon("src/images/搜索.png"));
        btnNewButton005.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    //搜索商品
                String Str = textField001.getText();
                List<Goods> goods = Goods.getgoods(store_id, Str, Str);
                refreshGoodsTable(goods, dtm);
            }
        });
        btnNewButton005.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton005.setBounds(335, 49, 111, 25);
        frame3.getContentPane().add(btnNewButton005);

        JButton btnNewButton_001 = new JButton("浏览菜品");
        btnNewButton_001.setIcon(new ImageIcon("src/images/浏览.png"));
        btnNewButton_001.addActionListener(new ActionListener() {            //获取全部商品
            public void actionPerformed(ActionEvent e) {
                List<Goods> goods = Goods.getgoods(store_id);
                refreshGoodsTable(goods, dtm);
            }
        });
        btnNewButton_001.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton_001.setBounds(10, 18, 111, 25);
        frame3.getContentPane().add(btnNewButton_001);

        JButton btnNewButton_100 = new JButton("加入购物车");
        btnNewButton_100.setIcon(new ImageIcon("src/images/添加.png"));
        btnNewButton_100.addActionListener(new ActionListener() {            //加入购物车按钮
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < table_001.getRowCount(); i++) {
                	int ifselectcolumn = dtm.findColumn("是否选中");
                	int idcolumn = dtm.findColumn("编号");
                	int buynumcolumn = dtm.findColumn("购买数量");
                	String getvalue = table_001.getValueAt(i, ifselectcolumn).toString(); 	
                	int good_id = Integer.valueOf(table_001.getValueAt(i, idcolumn).toString());
                    int buynum = Integer.valueOf(table_001.getValueAt(i, buynumcolumn).toString());
                    if (getvalue.equals("true"))
                        Shoppinglist.addShoppinglist(usernum, store_id, good_id, buynum);
                }
            }
        });
        btnNewButton_100.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton_100.setBounds(756, 321, 136, 27);
        frame3.getContentPane().add(btnNewButton_100);

        JButton btnNewButton_002 = new JButton("");
      //  System.out.println(User.ifFollow(usernum, store_id));
        if (User.ifFollow(usernum, store_id))  //判断是否关心某店铺，给予相应的图标

            btnNewButton_002.setIcon(new ImageIcon("src/images/关注.png"));
        else
            btnNewButton_002.setIcon(new ImageIcon("src/images/未关注.png"));
        btnNewButton_002.addActionListener(new ActionListener() {            //添加关心的店铺
            public void actionPerformed(ActionEvent e) {
                if (!User.ifFollow(usernum, store_id)) {
                    User.addUserfollow(usernum, store_id);
                    btnNewButton_002.setIcon(new ImageIcon("src/images/关注.png"));
                } else {
                    User.deleteUserfollow(usernum, store_id);
                    btnNewButton_002.setIcon(new ImageIcon("src/images/未关注.png"));
                }
            }
        });

        btnNewButton_002.setContentAreaFilled(false);
        btnNewButton_002.setFocusPainted(false);
        //btnNewButton_002.setBorder(null);
        btnNewButton_002.setBounds(756, 28, 75, 54);
        frame3.getContentPane().add(btnNewButton_002);

        JLabel lblNewLabel111 = new JLabel("\u516C\u544A\uFF1A");
        lblNewLabel111.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel111.setBounds(33, 108, 58, 19);
        frame3.getContentPane().add(lblNewLabel111);

        JButton btnNewButton = new JButton("\u7ED3\u7B97");
        btnNewButton.setIcon(new ImageIcon("src/images/结算.png"));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {             //结算按钮
                ordergoodslist = new ArrayList<Order_goods>();
                DefaultTableModel model = (DefaultTableModel) table_001.getModel();
                int sfxzcolumn = model.findColumn("是否选中");//获得标题是是否选中的列
                int gmslcolumn = model.findColumn("购买数量");//获得标题是购买数量的列
                for (int i = 0; i < table_001.getRowCount(); i++) {
                    String value = table_001.getValueAt(i, sfxzcolumn).toString();//读取你获取行号的某一列的值（也就是字段）
                    int good_id = Integer.valueOf(table_001.getValueAt(i, 0).toString());
                    int buynum = Integer.valueOf(table_001.getValueAt(i, gmslcolumn).toString());
                    if (value.equals("true")) {
                        Order_goods order_goods = new Order_goods();
                        //order_goods.ordernum=ordernum;
                        order_goods.store_id = store_id;
                        order_goods.good_id = good_id;
                        order_goods.buynum = buynum;
                        ordergoodslist.add(order_goods);
                        //System.out.println(good_id);
                    }
                }
                //for (int j = 0; j < ordergoodslist.size(); j++)
                  //  System.out.println(ordergoodslist.get(j).good_id);
                Payframe window = new Payframe();
                window.frame.setVisible(true);
                Customerframe.setCenter(window.frame);
            }

        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton.setBounds(756, 495, 136, 27);
        frame3.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("\u8D2D\u7269\u7BEE");
        btnNewButton_1.setIcon(new ImageIcon("src/images/购物车.png"));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//购物车按钮
                refreshShoppinListTable(usernum, store_id, dtm);
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton_1.setBounds(154, 18, 111, 25);
        frame3.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("从购物车删除");
        btnNewButton_2.setIcon(new ImageIcon("src/images/从购物车删除.png"));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            //从购物篮中删除
                for (int i = 0; i < table_001.getRowCount(); i++) {
                	int ifselectcolumn = dtm.findColumn("是否选中");
                	int idcolumn = dtm.findColumn("编号");     	
                	String getvalue = table_001.getValueAt(i, ifselectcolumn).toString();
                    int good_id = Integer.valueOf(table_001.getValueAt(i, idcolumn).toString());
                    if (getvalue == "true")
                        Shoppinglist.deleteFromShoppinglist(usernum, store_id, good_id);
                }
                refreshShoppinListTable(usernum, store_id, dtm);
            }
        });
        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton_2.setBounds(756, 405, 136, 27);
        frame3.getContentPane().add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("\u8054\u7CFB\u5546\u5BB6");//联系电话
        btnNewButton_3.setIcon(new ImageIcon("src/images/联系商家.png"));
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, Store.getStorePhone(store_id), "商家联系电话:", JOptionPane.PLAIN_MESSAGE);
            }
        });
        btnNewButton_3.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton_3.setBounds(309, 18, 111, 25);
        frame3.getContentPane().add(btnNewButton_3);

        JTextArea textArea100 = new JTextArea(Store.getNotice(store_id));
        textArea100.setForeground(Color.RED);
        textArea100.setRows(5);
        textArea100.setLineWrap(true);
        textArea100.setFont(new Font("宋体", Font.BOLD, 16));
        textArea100.setBackground(Color.WHITE);
        textArea100.setBounds(101, 80, 415, 90);
        frame3.getContentPane().add(textArea100);

        JButton btnNewButton_4 = new JButton("查看评论");
        btnNewButton_4.setIcon(new ImageIcon("src/images/查看评论.png"));
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Userremarkframe window = new Userremarkframe();
                window.frame.setVisible(true);
                Customerframe.setCenter(window.frame);
            }
        });
        btnNewButton_4.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton_4.setBounds(449, 18, 111, 25);
        frame3.getContentPane().add(btnNewButton_4);
    }

    public void refreshGoodsTable(List<Goods> goods, DefaultTableModel dtm) {    //刷新商品信息的函数
        dtm.setRowCount(0);//清空列表
    
        String[] tableHeads = new String[]{"编号", "图片","名称", "种类", "价格", "销售量", "购买数量", "增加", "是否选中"};
        dtm.setColumnIdentifiers(tableHeads);
        //设置JTable的表格数
        for (int i = 0; i < goods.size(); i++) {
            Vector<Object> v = new Vector<Object>();
            v.add(goods.get(i).good_id);
            v.add(goods.get(i).imageicon);
            v.add(goods.get(i).name);
            v.add(goods.get(i).type);
            v.add(goods.get(i).price);
            v.add(goods.get(i).salesnum);
            v.add(1);
            v.add("");
            v.add(false);
            dtm.addRow(v);
        }
        /*****设置table的列模型****/
        TableColumnModel tcm = table_001.getColumnModel();
        int buynumcolumn = dtm.findColumn("增加");
        tcm.getColumn(buynumcolumn).setCellEditor(new MyButtonEditor(table_001));
        tcm.getColumn(buynumcolumn).setCellRenderer(new MyButtonRender());
        tcm.getColumn(buynumcolumn).setPreferredWidth(40);
        tcm.getColumn(buynumcolumn).setWidth(40);
        tcm.getColumn(buynumcolumn).setMaxWidth(40);
        
        int imagecolumn= dtm.findColumn("图片");
        tcm.getColumn(imagecolumn).setCellRenderer(new Mytablerencender());
        tcm.getColumn(imagecolumn).setPreferredWidth(100);
        tcm.getColumn(imagecolumn).setWidth(100);
        tcm.getColumn(imagecolumn).setMaxWidth(100);
        
        int namecolumn = dtm.findColumn("名称");
        tcm.getColumn(namecolumn).setPreferredWidth(100);
        tcm.getColumn(namecolumn).setWidth(100);
        tcm.getColumn(namecolumn).setMaxWidth(100);
        
        int ifselectcolumn = dtm.findColumn("是否选中");
        //tcm.getColumn(ifselectcolumn).setCellEditor(new DefaultCellEditor(new JCheckBox()));
       // tcm.getColumn(ifselectcolumn).setCellRenderer(new TestTableCellRenderer());
        tcm.getColumn(ifselectcolumn).setCellEditor(table_001.getDefaultEditor(Boolean.class));  
        tcm.getColumn(ifselectcolumn).setCellRenderer(table_001.getDefaultRenderer(Boolean.class));  
        tcm.getColumn(ifselectcolumn).setPreferredWidth(80);
        tcm.getColumn(ifselectcolumn).setWidth(80);
        tcm.getColumn(ifselectcolumn).setMaxWidth(80);
        table_001.setRowHeight(100);
    }

    public void refreshShoppinListTable(String usernum, int store_id, DefaultTableModel dtm) {    //刷新商品信息的函数
        dtm.setRowCount(0);//清空列表
        String[] tableHeads = new String[]{"编号", "图片","名称", "种类", "价格", "购买数量", "增加", "是否选中"};
        dtm.setColumnIdentifiers(tableHeads);
        List<Goods> goods = Shoppinglist.getShoppinglist(usernum, store_id);
        //设置JTable的表格数
        for (int i = 0; i < goods.size(); i++) {
            Vector<Object> v = new Vector<Object>();
            v.add(goods.get(i).good_id);
            v.add(goods.get(i).imageicon);
            v.add(goods.get(i).name);
            v.add(goods.get(i).type);
            v.add(goods.get(i).price);
            v.add(Shoppinglist.getBuyNum(usernum, store_id, goods.get(i).good_id));
            v.add("");
            v.add(false);
            dtm.addRow(v);
        }
        /*****设置table的列模型****/
        TableColumnModel tcm = table_001.getColumnModel();
        int buynumcolumn = dtm.findColumn("增加");
        tcm.getColumn(buynumcolumn).setCellEditor(new MyButtonEditor(table_001));
        tcm.getColumn(buynumcolumn).setCellRenderer(new MyButtonRender());
        tcm.getColumn(buynumcolumn).setPreferredWidth(40);
        tcm.getColumn(buynumcolumn).setWidth(40);
        tcm.getColumn(buynumcolumn).setMaxWidth(40);
        
        int imagecolumn= dtm.findColumn("图片");
        tcm.getColumn(imagecolumn).setCellRenderer(new Mytablerencender());
        tcm.getColumn(imagecolumn).setPreferredWidth(100);
        tcm.getColumn(imagecolumn).setWidth(100);
        tcm.getColumn(imagecolumn).setMaxWidth(100);
        
        int namecolumn = dtm.findColumn("名称");
        tcm.getColumn(namecolumn).setPreferredWidth(100);
        tcm.getColumn(namecolumn).setWidth(100);
        tcm.getColumn(namecolumn).setMaxWidth(100);
        
        int ifselectcolumn = dtm.findColumn("是否选中");
        //tcm.getColumn(ifselectcolumn).setCellEditor(new DefaultCellEditor(new JCheckBox()));
       // tcm.getColumn(ifselectcolumn).setCellRenderer(new TestTableCellRenderer());
        tcm.getColumn(ifselectcolumn).setCellEditor(table_001.getDefaultEditor(Boolean.class));  
        tcm.getColumn(ifselectcolumn).setCellRenderer(table_001.getDefaultRenderer(Boolean.class));  
        tcm.getColumn(ifselectcolumn).setPreferredWidth(80);
        tcm.getColumn(ifselectcolumn).setWidth(80);
        tcm.getColumn(ifselectcolumn).setMaxWidth(80);
        table_001.setRowHeight(100);
    }
}

