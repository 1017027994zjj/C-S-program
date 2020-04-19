package 登陆界面;
import JDBCUtil.JDBCUtils;
import Toolclass.ImageDemo;
import 用户界面.Customerframe;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registeframe {
    public JFrame frame1;
    private JTextField textField001;
    private JPasswordField passwordField001;
    private JPasswordField passwordField_001;
    private JComboBox<String> comboBox;
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * Create the application.
     */
    public Registeframe() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Registeframe window = new Registeframe();
                    window.frame1.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */

    /**
     * 注册界面
     **/
    private void initialize() {
        frame1 = new JFrame();
        frame1.setBounds(100, 100, 526, 375);
        //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setLayout(null);

        JLabel label = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u7528\u6237\u540D\uFF1A");
        label.setFont(new Font("宋体", Font.BOLD, 16));
        label.setBounds(10, 49, 166, 33);
        frame1.getContentPane().add(label);

        textField001 = new JTextField();
        textField001.setFont(new Font("宋体", Font.BOLD, 16));
        textField001.setBounds(183, 50, 232, 25);
        frame1.getContentPane().add(textField001);
        textField001.setColumns(10);

        JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u5BC6\u7801\uFF1A");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel.setBounds(10, 92, 166, 33);
        frame1.getContentPane().add(lblNewLabel);

        passwordField001 = new JPasswordField();
        passwordField001.setFont(new Font("宋体", Font.BOLD, 16));
        passwordField001.setBounds(183, 96, 232, 25);
        frame1.getContentPane().add(passwordField001);

        JLabel lblNewLabel_1 = new JLabel("\u8BF7\u518D\u6B21\u8F93\u5165\u60A8\u7684\u5BC6\u7801\uFF1A");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel_1.setBounds(10, 135, 166, 33);
        frame1.getContentPane().add(lblNewLabel_1);

        passwordField_001 = new JPasswordField();
        passwordField_001.setFont(new Font("宋体", Font.BOLD, 16));
        passwordField_001.setBounds(183, 139, 232, 25);
        frame1.getContentPane().add(passwordField_001);

        JLabel lblNewLabel_2 = new JLabel("\u9009\u62E9\u60A8\u7684\u7528\u6237\u7C7B\u578B:");
        lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel_2.setBounds(10, 10, 190, 21);
        frame1.getContentPane().add(lblNewLabel_2);

        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("宋体", Font.BOLD, 16));
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"顾客", "店主"}));
        comboBox.setBounds(220, 8, 85, 27);
        frame1.getContentPane().add(comboBox);

        JTextField textField_002 = new JTextField();
        textField_002.setFont(new Font("宋体", Font.BOLD, 16));
        textField_002.setBounds(183, 178, 232, 25);
        frame1.getContentPane().add(textField_002);
        textField_002.setColumns(10);

        JTextField textField_003 = new JTextField();
        textField_003.setFont(new Font("宋体", Font.BOLD, 16));
        textField_003.setBounds(183, 222, 232, 25);
        frame1.getContentPane().add(textField_003);
        textField_003.setColumns(10);

        JButton btnNewButton001 = new JButton("提交");
        btnNewButton001.setIcon(new ImageIcon("src/images/提交.png"));
        btnNewButton001.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton001.setBounds(80, 280, 100, 34);
        frame1.getContentPane().add(btnNewButton001);
        btnNewButton001.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = String.valueOf(comboBox.getSelectedItem());
                String usernum = textField001.getText();
                String password1 = String.valueOf(passwordField001.getPassword());
                String password2 = String.valueOf(passwordField_001.getPassword());
                String nickname = textField_002.getText();
                String iphonenum = textField_003.getText();
                try {
                    zhuce(type, usernum, password1, password2, nickname, iphonenum);
                    ImageDemo.readImage2DB(usernum, ImageDemo.initIcon("3").getImage());
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        });
        JButton btnNewButton_001 = new JButton("重置");
        btnNewButton_001.setIcon(new ImageIcon("src/images/重置.png"));
        btnNewButton_001.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField001.setText("");
                passwordField001.setText("");
                passwordField_001.setText("");
                comboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"顾客", "店主"}));
                textField_002.setText("");
                textField_003.setText("");
            }
        });
        btnNewButton_001.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton_001.setBounds(250, 280, 100, 34);
        frame1.getContentPane().add(btnNewButton_001);

        JLabel lblNewLabel_3 = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u6635\u79F0\uFF1A");
        lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel_3.setBounds(10, 180, 166, 21);
        frame1.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("\u8BF7\u8F93\u5165\u60A8\u7684\u624B\u673A\u53F7\uFF1A");
        lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel_4.setBounds(10, 224, 149, 21);
        frame1.getContentPane().add(lblNewLabel_4);

        frame1.setVisible(true);


    }

    /*注册功能*/
    public void zhuce(String type, String usernum, String password1, String password2, String nickname, String iphonenum) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs1 = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://47.95.200.90:3306/takeaway_platform?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT", "takeaway_platform", "311214");
            conn.setAutoCommit(false);
            String sql1 = "select * from user where usernum = ? ";
            String sql2 = "insert into user(type,usernum,password,nickname,phonenum) values(?,?,?,?,?)";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            //给?赋值
            pstmt1.setString(1, usernum);
            pstmt2.setString(1, type);
            pstmt2.setString(2, usernum);
            pstmt2.setString(3, password1);
            pstmt2.setString(4, nickname);
            pstmt2.setString(5, iphonenum);
            rs1 = pstmt1.executeQuery();
            conn.commit();
            if (usernum.length() == 0 || password1.length() == 0 || password2.length() == 0)
                JOptionPane.showMessageDialog(null, "输入不能为空！");
            else if(!isNumericZidai(usernum)||usernum.length()<6||usernum.length()>12)
            	 JOptionPane.showMessageDialog(null, "账号不符合规则，请重新输入");//账号必须为数字串，且位数为6~12
            else if(isContainChinese(password1)||password1.length()<6||password1.length()>12)
           	 JOptionPane.showMessageDialog(null, "密码不符合规则，请重新输入");//密码不能含有汉字，且位数为6~12 
            else {
                if (rs1.next())
                    JOptionPane.showMessageDialog(null, "您输入的账号已存在请重新输入！");
                else if (!password1.equals(password2)) {
                    JOptionPane.showMessageDialog(null, "您输入的两次密码不一致请重新输入！");
                } else {
                    template.update(sql2, type, usernum, password1, nickname, iphonenum);
                    JOptionPane.showMessageDialog(null, "恭喜您，注册成功！");
                    frame1.setVisible(false);
              
                    
                }
            }
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
        }

    }


    public static boolean isNumericZidai(String str) {//判断字符串是否为数字
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;

    }
    public static boolean isContainChinese(String str) {//判断字符串中是否含有中文
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
