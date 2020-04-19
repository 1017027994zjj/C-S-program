package 用户界面;

import 登陆界面.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Dateclass.Goods;
import Dateclass.Store;
import Dateclass.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class Customerframe {
    public static int store_id;//用来记录选中的商店号
    public JFrame customerframe;
    private JTable table_001;
    private JTable table;
    private JTextField textField;

    /**
     * Create the application.
     */
    public Customerframe() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Customerframe window = new Customerframe();
                    window.customerframe.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void setCenter(JFrame frame) {
        int windowWidth = frame.getWidth();                     //获得窗口宽
        int windowHeight = frame.getHeight();                   //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
        Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
        int screenWidth = screenSize.width;                     //获取屏幕的宽
        int screenHeight = screenSize.height;                   //获取屏幕的高
        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        customerframe = new JFrame();
        customerframe.getContentPane().setFont(new Font("宋体", Font.BOLD, 16));
        customerframe.setBounds(100, 100, 942, 558);
        customerframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerframe.getContentPane().setLayout(null);

        int windowWidth = customerframe.getWidth();                     //获得窗口宽
        int windowHeight = customerframe.getHeight();                   //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
        Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
        int screenWidth = screenSize.width;                     //获取屏幕的宽
        int screenHeight = screenSize.height;                   //获取屏幕的高
        customerframe.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示

        table = new JTable();
        //table.setBounds(10, 148, 613, 414);
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(704, 363);
        scrollPane.setLocation(10, 58);
        customerframe.getContentPane().add(scrollPane);
        refreshStoresTable(Store.getstores(), dtm);

        JButton btnNewButton_1 = new JButton("\u8FDB\u5165");
        btnNewButton_1.setIcon(new ImageIcon("src/images/进入.png"));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < table.getRowCount(); i++) {
                    String value = table.getValueAt(i, 4).toString();//读取你获取行号的某一列的值（也就是字段）
                    int store_id = Integer.valueOf(table.getValueAt(i, 0).toString());
                    if (value == "true") {
                        Customerframe.store_id = store_id;
                        //进入选择商店后的窗口
                        newgoodframe window = new newgoodframe();
                        window.frame3.setVisible(true);
                        Customerframe.setCenter(window.frame3);
                    }
                }
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_1.setBounds(738, 234, 100, 30);
        customerframe.getContentPane().add(btnNewButton_1);

        JMenuBar menuBar = new JMenuBar();
        customerframe.setJMenuBar(menuBar);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u641C\u7D22\uFF08Search\uFF09");
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String storename = textField.getText();
                List<Store> stores = Store.getstores(storename);
                refreshStoresTable(stores, dtm);
            }
        });

        JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5546\u5BB6\u540D:");
        menuBar.add(lblNewLabel);
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 16));

        textField = new JTextField();
        menuBar.add(textField);
        textField.setFont(new Font("宋体", Font.BOLD, 16));
        textField.setColumns(10);
        mntmNewMenuItem_2.setFont(new Font("宋体", Font.BOLD, 16));
        menuBar.add(mntmNewMenuItem_2);

        JMenu mnNewMenu = new JMenu("\u6392\u5E8F\uFF08Sort\uFF09");
        mnNewMenu.setBounds(10, 0, 100, 100);
        menuBar.add(mnNewMenu);
        mnNewMenu.setFont(new Font("宋体", Font.BOLD, 16));

        JMenuItem mntmNewMenuItem = new JMenuItem("\u6309\u8BC4\u5206\u6392\u5E8F");
        mntmNewMenuItem.setFont(new Font("宋体", Font.BOLD, 16));
        mntmNewMenuItem.addActionListener(new ActionListener() {//按评分排序
            public void actionPerformed(ActionEvent e) {
                refreshStoresTable(Store.sqlSortByGrade(), dtm);

            }
        });
        mnNewMenu.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u6309\u9500\u91CF\u6392\u5E8F");
        mntmNewMenuItem_1.setFont(new Font("宋体", Font.BOLD, 16));
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    //按销售量排序
                refreshStoresTable(Store.sqlSortByMonthsale(), dtm);
            }
        });
        mnNewMenu.add(mntmNewMenuItem_1);

        JMenu mnNewMenu_1 = new JMenu("\u7528\u6237\u7BA1\u7406\uFF08Manage\uFF09");
        mnNewMenu_1.setFont(new Font("宋体", Font.BOLD, 16));
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("\u6211\u7684\u4E2A\u4EBA\u4FE1\u606F");
        mntmNewMenuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//我的个人信息
                Personal_information_frame window = new Personal_information_frame();
                window.frame.setVisible(true);
            }
        });
        mntmNewMenuItem_6.setFont(new Font("宋体", Font.BOLD, 16));
        mnNewMenu_1.add(mntmNewMenuItem_6);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u6211\u7684\u6536\u8D27\u4FE1\u606F");
        mntmNewMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyReceivinginformationframe window = new MyReceivinginformationframe();
                window.frame.setVisible(true);
                Customerframe.setCenter(window.frame);

            }
        });
        mntmNewMenuItem_4.setFont(new Font("宋体", Font.BOLD, 16));
        mnNewMenu_1.add(mntmNewMenuItem_4);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u6211\u7684\u5173\u6CE8");
        mnNewMenu_1.add(mntmNewMenuItem_3);
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//我的关注
                List<Store> stores = User.getYourFollwStores(Login.usernum);
                refreshStoresTable(stores, dtm);
            }
        });
        mntmNewMenuItem_3.setFont(new Font("\u5B8B\u4F53", mntmNewMenuItem_3.getFont().getStyle() | Font.BOLD, mntmNewMenuItem_3.getFont().getSize() + 4));

        JMenuItem menuItem = new JMenuItem("\u6211\u7684\u8BA2\u5355");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//我的订单
                Myorderframe window = new Myorderframe();
                window.orderframe.setVisible(true);
                Customerframe.setCenter(window.orderframe);
            }
        });
        menuItem.setFont(new Font("宋体", Font.BOLD, 16));
        mnNewMenu_1.add(menuItem);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("\u5237\u65B0\uFF08Refresh\uFF09");
        mntmNewMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//刷新
                refreshStoresTable(Store.getstores(), dtm);
            }
        });
        mntmNewMenuItem_5.setFont(new Font("宋体", Font.BOLD, 16));
        menuBar.add(mntmNewMenuItem_5);
    }

    public void refreshGoodsTable(List<Goods> goods, DefaultTableModel dtm) {    //刷新商品信息的函数
        dtm.setRowCount(0);//清空列表
        String[] tableHeads = new String[]{"编号", "名称", "种类", "价格", "销售量", "是否选中"};
        dtm.setColumnIdentifiers(tableHeads);
        //设置JTable的表格数
        for (int i = 0; i < goods.size(); i++) {
            Vector<Object> v = new Vector<Object>();
            v.add(goods.get(i).good_id);
            v.add(goods.get(i).name);
            v.add(goods.get(i).type);
            v.add(goods.get(i).price);
            v.add(goods.get(i).salesnum);
            v.add(false);
            dtm.addRow(v);
        }
        /*****设置table的列模型****/
        TableColumnModel tcm = table_001.getColumnModel();
       // tcm.getColumn(5).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        //tcm.getColumn(5).setCellRenderer(new TestTableCellRenderer());
        tcm.getColumn(5).setCellEditor(table.getDefaultEditor(Boolean.class));  
        tcm.getColumn(5).setCellRenderer(table.getDefaultRenderer(Boolean.class));  
        
        tcm.getColumn(5).setPreferredWidth(80);
        tcm.getColumn(5).setWidth(80);
        tcm.getColumn(5).setMaxWidth(80);
    }

    public void refreshStoresTable(List<Store> stores, DefaultTableModel dtm) {
        dtm.setRowCount(0);//清空列表
        //int num=1;
        String[] tableHeads = new String[]{"编号", "名称", "评分", "销售量", "是否选中"};
        dtm.setColumnIdentifiers(tableHeads);
        //设置JTable的表格数
        for (int i = 0; i < stores.size(); i++) {
            Vector<Object> v = new Vector<Object>();
            v.add(stores.get(i).store_id);
            v.add(stores.get(i).name);
            v.add(stores.get(i).grade);
            v.add(stores.get(i).salesnum);
            v.add(false);
            // JButton button =new JButton();
            //.add(button);
            dtm.addRow(v);
        }
        /*****设置table的列模型****/
        TableColumnModel tcm = table.getColumnModel();
       // tcm.getColumn(4).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        //tcm.getColumn(4).setCellRenderer(new TestTableCellRenderer());
        tcm.getColumn(4).setCellEditor(table.getDefaultEditor(Boolean.class));  
        tcm.getColumn(4).setCellRenderer(table.getDefaultRenderer(Boolean.class));  
        tcm.getColumn(4).setPreferredWidth(80);
        tcm.getColumn(4).setWidth(80);
        tcm.getColumn(4).setMaxWidth(80);


    }

}
class MyButtonRender implements TableCellRenderer {
    private JButton button;

    public MyButtonRender() {
        this.initButton();

        // 添加按钮。
    }

    private void initButton() {
        this.button = new JButton("+");
        // 设置按钮的大小及位置。
        this.button.setBounds(0, 0, 40, 5);
        ;
        this.button.setFont(new Font("宋体", Font.BOLD, 4));
        // 在渲染器里边添加按钮的事件是不会触发的
        // this.button.addActionListener(new ActionListener()
        // {
        //
        // public void actionPerformed(ActionEvent e)
        // {
        // // TODO Auto-generated method stub
        // }
        // });
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                                                   int column) {
        // 只为按钮赋值即可。也可以作其它操作，如绘背景等。
        this.button.setText(value == null ? "" : String.valueOf(value));
        return this.button;
    }
}

class MyButtonEditor extends DefaultCellEditor {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6546334664166791132L;
    private JButton button;

    public MyButtonEditor(JTable table) {
        // DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。
        super(new JTextField());

        // 设置点击几次激活编辑。
        this.setClickCountToStart(1);
        this.initButton(table);
    }

    private void initButton(JTable table) {
        this.button = new JButton("+");
        this.button.setFont(new Font("宋体", Font.BOLD, 4));
        //  button.setIcon(icon);
        // 设置按钮的大小及位置。
        this.button.setBounds(0, 0, 40, 5);
        // ImageIcon icon=new ImageIcon("icon/添加.gif");
        // icon.setImage(icon.getImage().getScaledInstance(30,7,Image.SCALE_DEFAULT ));
        // this.button.setIcon(icon);
        // 为按钮添加事件。这里只能添加ActionListner事件，Mouse事件无效。
        this.button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 触发取消编辑的事件，不会调用tableModel的setValue方法。
                MyButtonEditor.this.fireEditingCanceled();

                // 这里可以做其它操作。
                // 可以将table传入，通过getSelectedRow,getSelectColumn方法获取到当前选择的行和列及其它操作等。
                int i = table.getSelectedRow();//获得选中行
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int column = model.findColumn("购买数量");
                int buynum = Integer.valueOf(table.getValueAt(i, column).toString());
                table.setValueAt(buynum + 1, i, column);
                //JOptionPane.showMessageDialog(null, "您还没有注册商店请先进行注册！", "提示", JOptionPane.PLAIN_MESSAGE);
            }

        });

    }

    /**
     * 这里重写父类的编辑方法，返回一个JPanel对象即可（也可以直接返回一个Button对象，但是那样会填充满整个单元格）
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // 只为按钮赋值即可。也可以作其它操作。
        this.button.setText(value == null ? "" : String.valueOf(value));

        return this.button;
    }

    /**
     * 重写编辑单元格时获取的值。如果不重写，这里可能会为按钮设置错误的值。
     */
    @Override
    public Object getCellEditorValue() {
        return this.button.getText();
    }

    public boolean isCellEditable(int row, int column) {
        // 带有按钮列的功能这里必须要返回true不然按钮点击时不会触发编辑效果，也就不会触发事件。
        if (column == 6) {
            return true;
        } else {
            return false;
        }
    }
}