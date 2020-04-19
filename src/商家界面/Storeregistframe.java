package 商家界面;

import 用户界面.Customerframe;
import 登陆界面.Login;

import javax.swing.*;

import Dateclass.Store;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Storeregistframe {
    public JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextArea textArea006;

    /**
     * Create the application.
     */
    public Storeregistframe() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Storeregistframe window = new Storeregistframe();
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
        frame = new JFrame();
        frame.setTitle("\u5546\u5E97\u6CE8\u518C");
        frame.setBounds(100, 100, 478, 478);
        //	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("\u5546\u5E97\u540D\uFF1A");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 14));
        lblNewLabel.setBounds(23, 61, 80, 29);
        frame.getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(102, 65, 143, 21);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u5546\u5E97\u5730\u5740\uFF1A");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 14));
        lblNewLabel_1.setBounds(23, 120, 80, 21);
        frame.getContentPane().add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setBounds(101, 120, 144, 21);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("\u7535\u8BDD\uFF1A");
        lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 14));
        lblNewLabel_2.setBounds(23, 180, 70, 21);
        frame.getContentPane().add(lblNewLabel_2);

        textField_2 = new JTextField();
        textField_2.setBounds(101, 180, 143, 21);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("\u516C\u544A\uFF1A");
        lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 14));
        lblNewLabel_3.setBounds(23, 238, 54, 15);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("\u5E97\u4E3B\u8D26\u53F7\uFF1A");
        lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 14));
        lblNewLabel_4.setBounds(23, 10, 93, 31);
        frame.getContentPane().add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel(Login.usernum);
        lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 14));
        lblNewLabel_5.setBounds(138, 10, 174, 31);
        frame.getContentPane().add(lblNewLabel_5);

        JButton btnNewButton = new JButton("\u5730\u5740\u81EA\u52A8\u8BC6\u522B(\u5F00\u53D1\u4E2D)");
        btnNewButton.setBounds(265, 119, 166, 23);
        frame.getContentPane().add(btnNewButton);


        textArea006 = new JTextArea();
        textArea006.setFont(new Font("宋体", Font.BOLD, 14));
        textArea006.setWrapStyleWord(true);
        textArea006.setLineWrap(true);
        textArea006.setBounds(102, 238, 276, 98);
        frame.getContentPane().add(textArea006);

        JButton btnNewButton_1 = new JButton("注册");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//注册商店
                try {
                    String usernum = Login.usernum;
                    String storename = textField.getText();
                    String address = textField_1.getText();
                    String storephone = textField_2.getText();
                    String notice = textArea006.getText();
                    Store.addStore(usernum, storename, notice, address, storephone);
                    JOptionPane.showMessageDialog(null, "注册成功！", "提示", JOptionPane.PLAIN_MESSAGE);
                    frame.setVisible(false);
                    Login.store_id = Store.getStore_id(usernum);
                    Businessframe window = new Businessframe();
                    window.businessframe.setVisible(true);
                    Customerframe.setCenter(window.businessframe);
                } catch (Exception e2) {
                    // TODO: handle exception

                }
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 14));
        btnNewButton_1.setBounds(179, 375, 93, 23);
        frame.getContentPane().add(btnNewButton_1);
    }
}
