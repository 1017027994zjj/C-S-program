package 用户界面;

import 登陆界面.Login;

import javax.swing.*;

import Dateclass.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Payframe {

    public JFrame frame;
    public JComboBox<String> comboBox;

    /**
     * Create the application.
     */
    public Payframe() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Payframe window = new Payframe();
                    window.frame.setVisible(true);
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
        int store_id = Customerframe.store_id;
        frame = new JFrame();
        frame.setTitle("\u63D0\u4EA4\u8BA2\u5355");
        frame.setBounds(100, 100, 462, 429);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("\u9009\u62E9\u6536\u83B7\u5730\u5740\uFF1A");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 29, 98, 37);
        frame.getContentPane().add(lblNewLabel);

        comboBox = new JComboBox<String>();
        comboBox.setFont(new Font("宋体", Font.BOLD, 11));
        comboBox.setBounds(112, 37, 267, 23);
        List<Receiveaddress> addresslist = Receiveaddress.getReceiveAddress(Login.usernum);
        String[] items = new String[addresslist.size()];
        for (int i = 0; i < addresslist.size(); i++) {
            items[i] = addresslist.get(i).contact + addresslist.get(i).sex + "  " + addresslist.get(i).phonenum + "  " + addresslist.get(i).address;
        }
        comboBox.setModel(new DefaultComboBoxModel<String>(items));
        frame.getContentPane().add(comboBox);

        double sumprice = 0;//总价
        String text = "";//获得购物清单
        for (int i = 0; i < newgoodframe.ordergoodslist.size(); i++) {
            String goodsname = Goods.getGoodsName(newgoodframe.ordergoodslist.get(i).store_id, newgoodframe.ordergoodslist.get(i).good_id);
            String price = String.valueOf(Goods.getGoodsPrice(newgoodframe.ordergoodslist.get(i).store_id, newgoodframe.ordergoodslist.get(i).good_id));
            String buynum = String.valueOf(newgoodframe.ordergoodslist.get(i).buynum);
            text = text + goodsname + "     " + price + "     " + "X" + buynum + "\n";
            sumprice += Double.valueOf(price) * Integer.valueOf(buynum);
        }
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("宋体", Font.BOLD, 14));
        textArea.setWrapStyleWord(true);
        textArea.setRows(10);
        //textArea.setBounds(145, 75, 253, 155);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(115, 75, 240, 145);
        frame.getContentPane().add(scrollPane);

        JLabel lblNewLabel_1 = new JLabel("\u8D2D\u4E70\u6E05\u5355\uFF1A");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 14));
        lblNewLabel_1.setBounds(10, 136, 70, 23);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("\u5171\u8BA1:");
        lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 14));
        lblNewLabel_2.setBounds(112, 230, 43, 23);
        frame.getContentPane().add(lblNewLabel_2);


        JLabel lblNewLabel_3 = new JLabel(String.valueOf(sumprice) + "元");//总价的标签

        lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 14));
        lblNewLabel_3.setBounds(159, 232, 78, 19);
        frame.getContentPane().add(lblNewLabel_3);

        JTextArea textArea_1 = new JTextArea();
        textArea_1.setFont(new Font("宋体", Font.BOLD, 14));
        textArea_1.setLineWrap(true);
        textArea_1.setWrapStyleWord(true);
        textArea_1.setBounds(76, 260, 267, 37);
        frame.getContentPane().add(textArea_1);

        JButton btnNewButton = new JButton("提交");//提交按钮
        btnNewButton.setIcon(new ImageIcon("src/images/提交.png"));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Userorder userorder = new Userorder();
                    String ordernum = Userorder.makeOrdernum();//生成订单号
                    Order_goods.addorder_goods(ordernum, newgoodframe.ordergoodslist);//添加进入order_goods
                    String buytime = Userorder.makeBuytime();//生成下单时间
                    String note = textArea_1.getText();
                    String orderstatus = "等待商家接单";
                    String str;
                    str = comboBox.getSelectedItem().toString();
                    String[] ss = str.split("  ");
                    userorder.setOrdernum(ordernum);
                    userorder.setContact(ss[0]);
                    userorder.setAddress(ss[2]);
                    userorder.setPhonenum(ss[1]);
                    userorder.setBuytime(buytime);
                    userorder.setNote(note);
                    userorder.setOrderstatus(orderstatus);
                    userorder.setStore_id(store_id);
                    userorder.setUsernum(Login.usernum);

                    Userorder.addUserorder(userorder);//添加订单
                    Store.updateSalesNum(store_id);//更新商店销售量
                    for (int i = 0; i < newgoodframe.ordergoodslist.size(); i++) {
                        int store_id = newgoodframe.ordergoodslist.get(i).store_id;
                        int good_id = newgoodframe.ordergoodslist.get(i).good_id;
                        int buynum = newgoodframe.ordergoodslist.get(i).buynum;
                        Store.upDateGoodsSaleNum(store_id, good_id, buynum);
                    }//更新商品销售量
                    JOptionPane.showMessageDialog(null, "下单成功！", "提示", JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                } catch (Exception e2) {
                    // TODO: handle exception
                    JOptionPane.showMessageDialog(null, "下单失败，必须先添加地址！", "提示", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton.setBounds(112, 321, 93, 25);
        frame.getContentPane().add(btnNewButton);

        JLabel lblNewLabel_4 = new JLabel("\u5907\u6CE8\uFF1A");
        lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 14));
        lblNewLabel_4.setBounds(10, 270, 54, 15);
        frame.getContentPane().add(lblNewLabel_4);

        //frame.getContentPane().add(textArea);
        // textArea.add
    }
}
