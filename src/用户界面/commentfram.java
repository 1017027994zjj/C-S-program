package 用户界面;

import javax.swing.*;

import Dateclass.Evaluate;
import Dateclass.Userorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class commentfram {
    public JFrame evaluateframe;
    private JRadioButton rdbtnNewRadioButton;
    private JRadioButton rdbtnNewRadioButton_1;
    private JRadioButton rdbtnNewRadioButton_2;
    private JRadioButton rdbtnNewRadioButton_3;
    private JRadioButton rdbtnNewRadioButton_4;
    private JTextArea textArea999;
    private String ordernum;
    private String usernum;

    public commentfram() {

        evaluateframe = new JFrame();
        evaluateframe.setTitle("\u8BC4\u4EF7");
        evaluateframe.setBounds(100, 100, 557, 371);
        //evaluateframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        evaluateframe.getContentPane().setLayout(null);

        int windowWidth = evaluateframe.getWidth();                     //获得窗口宽
        int windowHeight = evaluateframe.getHeight();                   //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
        Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
        int screenWidth = screenSize.width;                     //获取屏幕的宽
        int screenHeight = screenSize.height;                   //获取屏幕的高
        evaluateframe.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示

        JLabel lblNewLabel999 = new JLabel("\u60A8\u5BF9\u5546\u5BB6/\u83DC\u54C1\u6EE1\u610F\u5417\uFF1F");
        lblNewLabel999.setBounds(21, 26, 194, 22);
        lblNewLabel999.setFont(new Font("宋体", Font.BOLD, 18));
        evaluateframe.getContentPane().add(lblNewLabel999);

        rdbtnNewRadioButton = new JRadioButton("1\u5206");
        rdbtnNewRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton_1.setSelected(false);
                rdbtnNewRadioButton_2.setSelected(false);
                rdbtnNewRadioButton_3.setSelected(false);
                rdbtnNewRadioButton_4.setSelected(false);
            }
        });
        rdbtnNewRadioButton.setBounds(23, 55, 56, 23);
        rdbtnNewRadioButton.setFont(new Font("宋体", Font.BOLD, 16));
        evaluateframe.getContentPane().add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_1 = new JRadioButton("2\u5206");//2分
        rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton.setSelected(false);
                rdbtnNewRadioButton_2.setSelected(false);
                rdbtnNewRadioButton_3.setSelected(false);
                rdbtnNewRadioButton_4.setSelected(false);
            }
        });
        rdbtnNewRadioButton_1.setBounds(89, 55, 56, 23);
        rdbtnNewRadioButton_1.setFont(new Font("宋体", Font.BOLD, 16));
        evaluateframe.getContentPane().add(rdbtnNewRadioButton_1);

        rdbtnNewRadioButton_2 = new JRadioButton("3\u5206");//3分
        rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton.setSelected(false);
                rdbtnNewRadioButton_1.setSelected(false);
                rdbtnNewRadioButton_3.setSelected(false);
                rdbtnNewRadioButton_4.setSelected(false);

            }
        });
        rdbtnNewRadioButton_2.setBounds(147, 55, 52, 23);
        rdbtnNewRadioButton_2.setFont(new Font("宋体", Font.BOLD, 16));
        evaluateframe.getContentPane().add(rdbtnNewRadioButton_2);

        rdbtnNewRadioButton_3 = new JRadioButton("4\u5206");//4分
        rdbtnNewRadioButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton.setSelected(false);
                rdbtnNewRadioButton_1.setSelected(false);
                rdbtnNewRadioButton_2.setSelected(false);
                rdbtnNewRadioButton_4.setSelected(false);
            }
        });
        rdbtnNewRadioButton_3.setBounds(209, 55, 57, 23);
        rdbtnNewRadioButton_3.setFont(new Font("宋体", Font.BOLD, 16));
        evaluateframe.getContentPane().add(rdbtnNewRadioButton_3);

        rdbtnNewRadioButton_4 = new JRadioButton("5\u5206");//5分
        rdbtnNewRadioButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rdbtnNewRadioButton.setSelected(false);
                rdbtnNewRadioButton_1.setSelected(false);
                rdbtnNewRadioButton_2.setSelected(false);
                rdbtnNewRadioButton_3.setSelected(false);
            }
        });
        rdbtnNewRadioButton_4.setBounds(279, 55, 51, 23);
        rdbtnNewRadioButton_4.setFont(new Font("宋体", Font.BOLD, 16));
        evaluateframe.getContentPane().add(rdbtnNewRadioButton_4);

        JButton btnNewButton999 = new JButton("\u63D0\u4EA4");
        btnNewButton999.setIcon(new ImageIcon("src/images/提交.png"));
        btnNewButton999.addActionListener(new ActionListener() {//评价提交
            public void actionPerformed(ActionEvent e) {
                int evaluategrade;
                String comment = textArea999.getText();
                if (rdbtnNewRadioButton.isSelected())
                    evaluategrade = 1;
                else if (rdbtnNewRadioButton_1.isSelected())
                    evaluategrade = 2;
                else if (rdbtnNewRadioButton_2.isSelected())
                    evaluategrade = 3;
                else if (rdbtnNewRadioButton_3.isSelected())
                    evaluategrade = 4;
                else
                    evaluategrade = 5;
                Evaluate evaluate = new Evaluate();
                String nickname = Evaluate.getNikename(ordernum);
                evaluate.ordernum = ordernum;
                evaluate.nickname = nickname;
                evaluate.comment = comment;
                evaluate.grade = evaluategrade;
                Evaluate.addEvaluate(evaluate);
                JOptionPane.showMessageDialog(null, "评价成功！", "提示", JOptionPane.PLAIN_MESSAGE);
                evaluateframe.dispose();
                Userorder.updateOrderstatus(ordernum, "订单已评价");
            }
        });
        btnNewButton999.setFont(new Font("宋体", Font.BOLD, 16));
        btnNewButton999.setBounds(33, 299, 97, 23);
        evaluateframe.getContentPane().add(btnNewButton999);

        JLabel lblNewLabel998 = new JLabel("\u53E3\u5473\u5982\u4F55\uFF0C\u5BF9\u5305\u88C5\u670D\u52A1\u6EE1\u610F\u5417\uFF1F");
        lblNewLabel998.setFont(new Font("宋体", Font.BOLD, 18));
        lblNewLabel998.setBounds(19, 84, 258, 21);
        evaluateframe.getContentPane().add(lblNewLabel998);

        textArea999 = new JTextArea();
        textArea999.setLineWrap(true);
        textArea999.setWrapStyleWord(true);
        textArea999.setFont(new Font("宋体", Font.BOLD, 16));
        textArea999.setRows(10);
        textArea999.setBounds(26, 121, 413, 154);
        evaluateframe.getContentPane().add(textArea999);
    }

    public String getUsernum() {
        return usernum;
    }

    public void setUsernum(String usernum) {
        this.usernum = usernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }
}
