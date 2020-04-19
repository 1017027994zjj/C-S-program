package �̼ҽ���;
import �û�����.Customerframe;
import ��½����.Login;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.*;
import Dateclass.Goods;
import Dateclass.Store;
import Dateclass.Userorder;
import Toolclass.ImageDemo;
import Toolclass.ImageUtil;
import Toolclass.Mytablerencender;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;
import java.util.*;
public class Businessframe {
    public JFrame businessframe;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JFrame modifystoreframe;
    private JTextField textField213;
    private JTextField textField_106;
    private JTextField textField_278;
    private JTextArea textArea006;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";//���ڸ�ʽ
    private String time;
    private int ONE_SECOND = 1000;

    /**
     * Create the application.
     */
    public Businessframe() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Businessframe window = new Businessframe();
                    window.businessframe.setVisible(true);
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
        int store_id = Login.store_id;
        businessframe = new JFrame();
        businessframe.setBounds(100, 100, 1073, 763);
        businessframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        businessframe.setLocationRelativeTo(null);
        businessframe.getContentPane().setLayout(null);

        MyTableModel dtm = new MyTableModel();
        table = new JTable(dtm);
        table.setFont(new Font("����", Font.BOLD, 15));

        RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(dtm);
        table.setRowSorter(rowSorter);// �� ��� ���� ��������
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(1023, 512);
        scrollPane.setLocation(10, 174);
        // scrollPane.set
        businessframe.getContentPane().add(scrollPane, BorderLayout.CENTER);
        List<Goods> goods = Goods.getgoods(store_id);
        refreshGoodsTable(goods, dtm);

        JButton btnNewButton = new JButton("ȫ����Ʒ");
        btnNewButton.setIcon(new ImageIcon("src/images/���.png"));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    //ȫ����Ʒ
                List<Goods> goods = Goods.getgoods(store_id);
                refreshGoodsTable(goods, dtm);
            }
        });
        btnNewButton.setFont(new Font("����", Font.BOLD, 16));
        btnNewButton.setBounds(10, 10, 127, 25);
        businessframe.getContentPane().add(btnNewButton);

        textField = new JTextField();
        textField.setFont(new Font("����", Font.ITALIC, 16));
        textField.setText("\u8F93\u5165\u54C1\u7C7B\u6216\u8005\u540D\u79F0");
        textField.setBounds(147, 10, 153, 25);
        businessframe.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnNewButton_1 = new JButton("����");
        btnNewButton_1.setIcon(new ImageIcon("src/images/����.png"));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {        //��������
                String Str = textField.getText();
                List<Goods> goods = Goods.getgoods(store_id, Str, Str);
                refreshGoodsTable(goods, dtm);
            }
        });
        btnNewButton_1.setFont(new Font("����", Font.BOLD, 16));
        btnNewButton_1.setBounds(330, 10, 127, 25);
        businessframe.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("\u4FEE\u6539\u5546\u54C1\u4FE1\u606F");
        btnNewButton_2.setIcon(new ImageIcon("src/images/�޸�.png"));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//�޸���Ʒ���ԣ���Ʒ�Ų����޸�
                for (int i = 0; i < table.getRowCount(); i++) {
                    int good_id = Integer.valueOf(table.getValueAt(i, 0).toString());
                    String goodname = table.getValueAt(i, 1).toString();
                    String type = table.getValueAt(i, 2).toString();
                    double price = Double.valueOf(table.getValueAt(i, 3).toString());
                    int salesnum = Integer.valueOf(table.getValueAt(i, 4).toString());
                    String value = table.getValueAt(i, 5).toString();//��ȡ���ȡ�кŵ�ĳһ�е�ֵ��Ҳ�����ֶΣ�
                    if (value == "true") {
                        Goods newgoods = new Goods();
                        newgoods.good_id = good_id;
                        newgoods.name = goodname;
                        newgoods.type = type;
                        newgoods.price = price;
                        newgoods.salesnum = salesnum;
                        Store.modifyGoods(store_id, good_id, newgoods);
                    }
                }
            }
        });
        btnNewButton_2.setFont(new Font("����", Font.BOLD, 16));
        btnNewButton_2.setBounds(330, 61, 161, 25);
        businessframe.getContentPane().add(btnNewButton_2);

        JButton btnNewButton_4 = new JButton("�޸���Ϣ");
        btnNewButton_4.setIcon(new ImageIcon("src/images/�޸�.png"));
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /**
                 �޸��̵���Ϣ����
                 */
                modifystoreframe = new JFrame();
                modifystoreframe.setTitle("\u4FEE\u6539\u5546\u5E97\u4FE1\u606F");
                modifystoreframe.setBounds(100, 100, 500, 405);
                //modifystoreframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                modifystoreframe.setLocationRelativeTo(null);
                modifystoreframe.getContentPane().setLayout(null);

                JLabel lblNewLabel04 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
                lblNewLabel04.setFont(new Font("����", Font.BOLD, 16));
                lblNewLabel04.setBounds(10, 118, 85, 29);
                modifystoreframe.getContentPane().add(lblNewLabel04);

                JLabel lblNewLabel_106 = new JLabel("\u5730\u5740\uFF1A");
                lblNewLabel_106.setFont(new Font("����", Font.BOLD, 16));
                lblNewLabel_106.setBounds(10, 77, 85, 19);
                modifystoreframe.getContentPane().add(lblNewLabel_106);

                JLabel lblNewLabel_212 = new JLabel("\u5546\u5E97\u540D\uFF1A");  //�̵�����ǩ
                lblNewLabel_212.setFont(new Font("����", Font.BOLD, 16));
                lblNewLabel_212.setBounds(10, 28, 98, 19);
                modifystoreframe.getContentPane().add(lblNewLabel_212);

                JButton btnNewButton003 = new JButton("\u63D0\u4EA4");
                btnNewButton003.addActionListener(new ActionListener() {//�޸��̵����Ϣ
                    public void actionPerformed(ActionEvent e) {

                        try {
                            String newname = textField213.getText();
                            String storephone = textField_278.getText();
                            String address = textField_106.getText();
                            String newnotice = textArea006.getText();
                            Store.updateName(store_id, newname);
                            Store.updateAddress(store_id, address);
                            Store.updateNotice(store_id, newnotice);
                            Store.updatePhoneNum(store_id, storephone);
                            JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "��ʾ", JOptionPane.PLAIN_MESSAGE);

                        } catch (Exception e2) {
                            JOptionPane.showMessageDialog(null, "���ʧ�ܣ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                });
                btnNewButton003.setFont(new Font("����", Font.BOLD, 16));
                btnNewButton003.setBounds(103, 312, 97, 29);
                modifystoreframe.getContentPane().add(btnNewButton003);

                textField213 = new JTextField(Store.getStoreName(store_id));//��ȡ�û���
                textField213.setFont(new Font("����", Font.BOLD, 16));
                textField213.setBounds(103, 28, 188, 21);
                modifystoreframe.getContentPane().add(textField213);
                textField213.setColumns(10);

                textField_106 = new JTextField(Store.getAddress(store_id));//��ȡ��ַ
                textField_106.setFont(new Font("����", Font.BOLD, 16));
                textField_106.setBounds(105, 77, 186, 21);
                modifystoreframe.getContentPane().add(textField_106);
                textField_106.setColumns(10);

                textField_278 = new JTextField(Store.getStorePhone(store_id));
                textField_278.setFont(new Font("����", Font.BOLD, 16));
                textField_278.setBounds(105, 123, 148, 21);
                modifystoreframe.getContentPane().add(textField_278);
                textField_278.setColumns(10);

                JLabel lblNewLabel_333 = new JLabel("\u516C\u544A\uFF1A");
                lblNewLabel_333.setFont(new Font("����", Font.BOLD, 16));
                lblNewLabel_333.setBounds(10, 170, 58, 19);
                modifystoreframe.getContentPane().add(lblNewLabel_333);

                textArea006 = new JTextArea();
                textArea006.setFont(new Font("����", Font.BOLD, 16));
                textArea006.setWrapStyleWord(true);
                textArea006.setLineWrap(true);
                textArea006.setBounds(103, 174, 346, 117);
                modifystoreframe.getContentPane().add(textArea006);
                //���ڽ���
                modifystoreframe.setVisible(true);
            }
        });
        btnNewButton_4.setFont(new Font("����", Font.BOLD, 16));
        btnNewButton_4.setBounds(162, 61, 128, 25);
        businessframe.getContentPane().add(btnNewButton_4);

        JButton btnNewButton_5 = new JButton("��������");
        btnNewButton_5.setIcon(new ImageIcon("src/images/Ǯ.png"));
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Lable1 = "���ն�����(��)��";
                String Lable2 = "��������ɶ�����(��)��";
                String Lable3 = "��������(Ԫ)��";
                int dayordersalenum = Userorder.getDayAllOrderSaleNum(store_id);
                int dayfinishordersalenum = Userorder.getDayFinishOrderSaleNum(store_id);
                double dayincome = Userorder.getDayIncome(store_id);
                String Lable4 = Lable1 + String.valueOf(dayordersalenum) + "\n";
                String Lable5 = Lable2 + String.valueOf(dayfinishordersalenum) + "\n";
                String Lable6 = Lable3 + String.valueOf(dayincome);
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("����", Font.BOLD, 16)));//��������
                JOptionPane.showMessageDialog(null, Lable4 + Lable5 + Lable6, "����Ӫҵ�����", JOptionPane.PLAIN_MESSAGE);
            }
        });
        btnNewButton_5.setFont(new Font("����", Font.BOLD, 16));
        btnNewButton_5.setBounds(10, 61, 127, 25);
        businessframe.getContentPane().add(btnNewButton_5);

        JButton btnNewButton_6 = new JButton("��������");
        btnNewButton_6.setIcon(new ImageIcon("src/images/��������.png"));
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Storeorderframe window = new Storeorderframe();
                window.frame.setVisible(true);
                Customerframe.setCenter(window.frame);
            }
        });
        btnNewButton_6.setFont(new Font("����", Font.BOLD, 16));
        btnNewButton_6.setBounds(10, 112, 127, 25);
        businessframe.getContentPane().add(btnNewButton_6);

        JButton btnNewButton_8 = new JButton("�û�����");
        btnNewButton_8.setIcon(new ImageIcon("src/images/�鿴����.png"));
        btnNewButton_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Storereplyframe window = new  Storereplyframe ();
                window.frame.setVisible(true);
                Customerframe.setCenter(window.frame);
            }
        });
        btnNewButton_8.setFont(new Font("����", Font.BOLD, 16));
        btnNewButton_8.setBounds(162, 112, 127, 25);
        businessframe.getContentPane().add(btnNewButton_8);

        JLabel lblNewLabel_3 = new JLabel("\u6211\u7684\u8BC4\u5206\uFF1A");
        lblNewLabel_3.setFont(new Font("����", Font.BOLD, 25));
        lblNewLabel_3.setBounds(740, 91, 142, 36);
        businessframe.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel(String.valueOf(Store.getGrade(store_id)));
        lblNewLabel_4.setForeground(new Color(255, 51, 0));
        lblNewLabel_4.setBackground(Color.WHITE);
        lblNewLabel_4.setFont(new Font("����", Font.BOLD, 60));
        lblNewLabel_4.setBounds(902, 61, 114, 77);
        businessframe.getContentPane().add(lblNewLabel_4);


        lblNewLabel_5 = new JLabel();//��̬ʱ����ʾ
        lblNewLabel_5.setFont(new Font("����", Font.BOLD, 20));
        lblNewLabel_5.setBounds(708, 6, 309, 30);
        businessframe.getContentPane().add(lblNewLabel_5);
        
        JLabel lblNewLabel_7 = new JLabel("\u4E0A\u4F20\u56FE\u7247\u9884\u89C8:");
        lblNewLabel_7.setFont(new Font("����", Font.BOLD, 16));
        lblNewLabel_7.setBounds(570, 10, 114, 25);
        businessframe.getContentPane().add(lblNewLabel_7);
        
        JButton btnNewButton_3 = new JButton("\u5220\u9664\u5546\u54C1");//ɾ����Ʒ
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		for (int i = 0; i < table.getRowCount(); i++) {
                    int good_id = Integer.valueOf(table.getValueAt(i, 0).toString());
                    int ifselectcolumn = dtm.findColumn("�Ƿ�ѡ��");//��ö�Ӧ�к�
                    String value = table.getValueAt(i, ifselectcolumn).toString();//��ȡ���ȡ�кŵ�ĳһ�е�ֵ��Ҳ�����ֶΣ�
                    if (value == "true") {
                        Store.DeleteyGoods(store_id, good_id);
                        
                    }
                }	
        			List<Goods> goods = Goods.getgoods(store_id);//ˢ�±��
        			refreshGoodsTable(goods, dtm);
        	}
        });
        btnNewButton_3.setFont(new Font("����", Font.BOLD, 16));
        btnNewButton_3.setBounds(330, 112, 127, 25);
        businessframe.getContentPane().add(btnNewButton_3);
        
        lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setBounds(580, 45, 100, 100);
        businessframe.getContentPane().add(lblNewLabel_6);
        
       
     
     

        JMenuBar menuBar = new JMenuBar();
        businessframe.setJMenuBar(menuBar);

        JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5546\u54C1\u540D\uFF1A");
        menuBar.add(lblNewLabel);
        lblNewLabel.setFont(new Font("����", Font.BOLD, 14));

        textField_1 = new JTextField();
        menuBar.add(textField_1);
        textField_1.setFont(new Font("����", Font.BOLD, 14));
        textField_1.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u8BF7\u9009\u62E9\u54C1\u7C7B\uFF1A");
        menuBar.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("����", Font.BOLD, 14));

        JComboBox<String> comboBox = new JComboBox<String>();
        menuBar.add(comboBox);
        comboBox.setFont(new Font("����", Font.BOLD, 14));
        comboBox.setEditable(true);
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"��ʳ", "�Ȳ�", "����", "����", "���", "��ʳ", "С��", "�ײ�"}));

        JLabel lblNewLabel_2 = new JLabel("\u8BF7\u8F93\u5165\u4EF7\u683C\uFF1A");
        menuBar.add(lblNewLabel_2);
        lblNewLabel_2.setFont(new Font("����", Font.BOLD, 14));

        textField_2 = new JTextField();
        menuBar.add(textField_2);
        textField_2.setFont(new Font("����", Font.BOLD, 14));
        textField_2.setColumns(10);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u672C\u5730\u4E0A\u4F20");//�ϴ�ͼƬ
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 btn_addImgActionPerformed(e);
        	}
        });
        mntmNewMenuItem_1.setFont(new Font("����", Font.BOLD, 14));
        menuBar.add(mntmNewMenuItem_1);
        //�����Ʒ��ť
                JMenuItem mntmNewMenuItem = new JMenuItem("           �����Ʒ                               ");
                mntmNewMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String name = textField_1.getText();
                            String type = comboBox.getSelectedItem().toString();
                            double price = Double.valueOf(textField_2.getText());
                            Image image=((ImageIcon) lblNewLabel_6.getIcon()).getImage();
                            File outputfile = new File("D:\\javaͼ��store.jpg");//�洢�̼��ϴ���ͼƬ
                            if (!outputfile.exists() && !outputfile.isDirectory()) {
                                System.out.println("�ļ���·�������ڣ�����·��:");
                                outputfile.mkdirs();
                            } else {
                                System.out.println("�ļ���·������:");
                            }
                            ImageIO.write(ImageDemo.toBufferedImage(image), "jpg", outputfile);
                            FileInputStream in = ImageUtil.readImage("D:\\javaͼ��store.jpg");
                         
                            if (name.length() == 0 || type.length() == 0 ||textField_2.getText().length()==0)
                                JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
                            else {
                            	Store.addGoods(store_id, name, type, price,in);
                            	JOptionPane.showMessageDialog(null, "��ӳɹ���", "��ʾ", JOptionPane.PLAIN_MESSAGE);
                            	List<Goods> goods = Goods.getgoods(store_id);
                            	refreshGoodsTable(goods, dtm);
                            }
                        } catch (Exception e2) {
                            JOptionPane.showMessageDialog(null, "���ʧ��,�����ʽ����", "��ʾ", JOptionPane.PLAIN_MESSAGE);
                        }
                        textField_1.setText("");
                        textField_2.setText("");
                        comboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"��ʳ", "�Ȳ�", "����", "����", "���", "��ʳ", "С��", "�ײ�"}));
                    }
                });
                mntmNewMenuItem.setFont(new Font("����", Font.BOLD, 14));
                menuBar.add(mntmNewMenuItem);
        Timer tmr = new Timer();
        tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), ONE_SECOND);
    }

    public void refreshGoodsTable(List<Goods> goods, DefaultTableModel dtm) {    //ˢ����Ʒ��Ϣ�ĺ���
        dtm.setRowCount(0);//����б�
        String[] tableHeads = new String[]{"���", "ͼƬ","����", "����", "�۸�", "������", "�Ƿ�ѡ��"};
        dtm.setColumnIdentifiers(tableHeads);
        //����JTable�ı����
        for (int i = 0; i < goods.size(); i++) {
            Object[] data = {goods.get(i).good_id,goods.get(i).imageicon, goods.get(i).name, goods.get(i).type, goods.get(i).price, goods.get(i).salesnum, false};
            dtm.addRow(data);
        }
        //this.table=new JTable(dtm);
        /*****����table����ģ��****/
        int ifselectcolumn = dtm.findColumn("�Ƿ�ѡ��");
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(ifselectcolumn).setCellEditor(table.getDefaultEditor(Boolean.class));  
        tcm.getColumn(ifselectcolumn).setCellRenderer(table.getDefaultRenderer(Boolean.class));  
        //tcm.getColumn(5).setPreferredWidth(80);
        tcm.getColumn(ifselectcolumn).setWidth(60);
        tcm.getColumn(ifselectcolumn).setMaxWidth(60);
        
        int imagecolumn = dtm.findColumn("ͼƬ");
        //table.getColumnModel().getColumn(imagecolumn).setCellRenderer(new Mytablerencender());//��ȾͼƬ
        tcm.getColumn(imagecolumn).setCellRenderer(new Mytablerencender());
        tcm.getColumn(imagecolumn).setMinWidth(100);
        tcm.getColumn(imagecolumn).setWidth(100);
        tcm.getColumn(imagecolumn).setMaxWidth(100);
        table.setRowHeight(100);//����ÿ�и߶�Ϊ100     
    }
    @SuppressWarnings("unused")
	private void btn_addImgActionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg,gif,bmp,png", "jpg", "gif", "png", "gif");
        fileChooser.setFileFilter(filter);
        try {
            fileChooser.showOpenDialog(null);

        } catch (HeadlessException e1) {
            // TODO: handle exception
            e1.printStackTrace();
        }
        try {
            File f = fileChooser.getSelectedFile();
            //int x=91,y=10;
            int width = 100, height = 100;
            ImageIcon image = new ImageIcon(f.getPath());
            image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
            lblNewLabel_6.setIcon(image);
        } catch (Exception e2) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "���ͼƬʧ�ܣ�");
            return;
        }

    }
    protected class JLabelTimerTask extends TimerTask {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(
                DEFAULT_TIME_FORMAT);

        @Override
        public void run() {
            time = dateFormatter.format(Calendar.getInstance().getTime());
            lblNewLabel_5.setText(time);
        }
    }
}

class MyTableModel extends DefaultTableModel {
    private static final long serialVersionUID = 1L;

    public boolean isCellEditable(int row, int column) {
        if (column == 0 || column == 1||column == 4)
            return false;
        else
            return true;
    }

}

