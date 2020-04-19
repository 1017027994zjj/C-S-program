package 登陆界面;

import JDBCUtil.JDBCUtils;

import org.springframework.jdbc.core.JdbcTemplate;

import Dateclass.Store;
import Dateclass.User;
import 商家界面.Businessframe;
import 商家界面.Storeregistframe;
import 用户界面.Customerframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    public static String usernum;//用户名
    public static int store_id;//商店号
    private JFrame Loginframe;
    private JTextField textField;
    private JPasswordField passwordField;
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * Create the application.
     */
    public Login() {
        initialize();
    }

    /**
     * Launch the application.
     *
     * @throws UnsupportedLookAndFeelException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    /*SubstanceBusinessBlackSteelLookAndFeel()
     * SubstanceCremeCoffeeLookAndFeel()
     * SubstanceCremeLookAndFeel()
     * SubstanceMistAquaLookAndFeel()
     * SubstanceOfficeSilver2007LookAndFeel()
     * substance.skin.SubstanceSaharaLookAndFeel()
     * */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        //UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel());
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                try {
                    UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel());
                    //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    Login window = new Login();
                    window.Loginframe.setVisible(true);
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
        Loginframe = new JFrame();
        Loginframe.setTitle("欢迎来到登陆界面");
        Loginframe.setBounds(100, 100, 547, 418);
        Loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Loginframe.getContentPane().setLayout(null);
        
        Customerframe.setCenter(Loginframe);
        textField = new JTextField();
        textField.setBounds(173, 146, 243, 25);
        Loginframe.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("\u8D26\u53F7\u540D:");
        lblNewLabel.setIcon(new ImageIcon("src/images/用户名.png"));
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel.setBounds(62, 140, 101, 34);
        Loginframe.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\u5BC6 \u7801:");
        lblNewLabel_1.setIcon(new ImageIcon("src/images/密码.png"));
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel_1.setBounds(62, 211, 101, 25);
        Loginframe.getContentPane().add(lblNewLabel_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(173, 211, 243, 28);
        Loginframe.getContentPane().add(passwordField);

        JButton btnNewButton = new JButton();
        //btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
        btnNewButton.setIcon(new ImageIcon("src/images/提交.png"));
        //btnNewButton.setHorizontalTextPosition(SwingConstants.RIGHT);//
        btnNewButton.setText("\u767B\u9646");

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usernum = textField.getText();
                String password = String.valueOf(passwordField.getPassword());
                try {
                    if (loginSQL(usernum, password) == 0)
                        JOptionPane.showMessageDialog(null, "输入不能为空请重新输入！");
                    if (loginSQL(usernum, password) == 1)
                        JOptionPane.showMessageDialog(null, "用户名不存在");
                    if (loginSQL(usernum, password) == 2)
                        JOptionPane.showMessageDialog(null, "密码错误请重新输入");
                    if (loginSQL(usernum, password) == 3) {
                        JOptionPane.showMessageDialog(null, "登陆成功");
                        Login.usernum = usernum;      //登陆成功时用来记录用户名
                        // Loginframe.setVisible(false);
                        Loginframe.dispose();
                        if (User.getUserType(usernum).equals("顾客")) {
                            Customerframe window = new Customerframe();
                            window.customerframe.setVisible(true);
                            Customerframe.setCenter(window.customerframe);
                        } else {
                            if (Store.ifRegist(usernum) == false) {
                                JOptionPane.showMessageDialog(null, "您还没有注册商店请先进行注册！", "提示", JOptionPane.PLAIN_MESSAGE);
                                Storeregistframe window = new Storeregistframe();
                                window.frame.setVisible(true);
                                Customerframe.setCenter(window.frame);
                            } else {
                                store_id = Store.getStore_id(usernum);
                                Businessframe window = new Businessframe();
                                window.businessframe.setVisible(true);
                                Customerframe.setCenter(window.businessframe);
                            }
                        }
                    }
                } catch (HeadlessException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton.setBounds(54, 295, 100, 34);
        Loginframe.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("重置");
        btnNewButton_1.setIcon(new ImageIcon("src/images/重置.png"));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                passwordField.setText("");
            }
        });
        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_1.setBounds(207, 295, 100, 34);
        Loginframe.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("注册");//注册
        btnNewButton_2.setIcon(new ImageIcon("src/images/注册.png"));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Registeframe window = new Registeframe();
                window.frame1.setVisible(true);
                Customerframe.setCenter(window.frame1);
            }
        });

        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_2.setBounds(361, 294, 100, 34);
        Loginframe.getContentPane().add(btnNewButton_2);

        JLabel lblNewLabel_2 = new JLabel("\u723D\u6781\u4E86\u8BA2\u9910\u7CFB\u7EDF\u6B22\u8FCE\u60A8\uFF01");
        lblNewLabel_2.setIcon(new ImageIcon("src/images/美食.png"));
        lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 20));
        lblNewLabel_2.setBounds(110, 26, 342, 70);
        Loginframe.getContentPane().add(lblNewLabel_2);

    }

    public int loginSQL(String usernum, String password) throws ClassNotFoundException {
        if (usernum.length() == 0 || password.length() == 0) {
            return 0;
        }
        //连接数据库判断是否登录成功
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        //1.获取连接
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://47.95.200.90:3306/takeaway_platform?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT", "takeaway_platform", "311214");
            conn.setAutoCommit(false);
            //2.定义sql
            String sql1 = "select * from user where usernum = ? ";
            String sql2 = "select * from user where password = ?";
            //3.获取执行sql的对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            //给?赋值
            pstmt1.setString(1, usernum);
            pstmt2.setString(1, password);
            //4.执行查询,不需要传递sql
            rs1 = pstmt1.executeQuery();
            rs2 = pstmt2.executeQuery();
            conn.commit();
            if (rs1.next() == false)
                return 1;
            else if (rs2.next() == false)
                return 2;
            else return 3;
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs1, pstmt1, conn);
            JDBCUtils.close(rs2, pstmt2, null);
        }

        return 0;
    }
}

