package 商家界面;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import 用户界面.Customerframe;
import 登陆界面.Login;

import javax.swing.SwingConstants;

import Dateclass.Evaluate;
import Dateclass.Goods;

public class Storereplyframe {

	public JFrame frame;
	private JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Storereplyframe window = new Storereplyframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Storereplyframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u7528\u6237\u8BC4\u8BBA:");
		frame.setBounds(100, 100, 591, 655);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(35, 38, 399, 531);
		frame.getContentPane().add(scrollPane);
		
		RefreshPanel();
		
		JButton btnNewButton = new JButton("刷新");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefreshPanel();		
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 14));
		btnNewButton.setBounds(456, 30, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		}
	
public void RefreshPanel() {
	int store_id = Login.store_id;
	List<Evaluate> evaluatelist=Evaluate.getEvaluate(store_id);//获取用户评论
	int size=evaluatelist.size();
	panel.removeAll();
	for(int i=0;i<size;i++) {
		Evaluate a=evaluatelist.get(i);
		JLabel nickname=new JLabel("用户昵称："+a.nickname+"     评分："+String.valueOf(a.grade));
		nickname.setFont(new Font("宋体", Font.BOLD, 14));
		
		JLabel pinlun=new JLabel("用户评论：");//常量
		pinlun.setFont(new Font("宋体", Font.BOLD, 14));
		
		JTextArea userremark=new JTextArea(a.comment);//用户的评论
		userremark.setFont(new Font("宋体", Font.BOLD, 14));
		userremark.setBounds(53, 220, 329, 114);
		
		JLabel huifu=new JLabel("商家回复：");//常量
		huifu.setFont(new Font("宋体", Font.BOLD, 14));
		
		JTextArea storereply=new JTextArea(a.reply);//商家回复
		storereply.setFont(new Font("宋体", Font.BOLD, 14));
		storereply.setBounds(53, 220, 329, 114);
		
		JButton commit=new JButton("提交");
		commit.setBounds(53, 220, 329, 114);
		commit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    //全部商品
               String reply=storereply.getText();
               try {
				Evaluate.modifyEvaluate(a.ordernum, reply);
				JOptionPane.showMessageDialog(null, "回复成功！", "提示", JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "回复失败！", "提示", JOptionPane.PLAIN_MESSAGE);
			}
            }
        });
		
		JLabel fengexian=new JLabel("______________________________________________________");
		fengexian.setFont(new Font("宋体", Font.BOLD, 14));
		
		panel.add(nickname);
		panel.add(userremark);
		panel.add(huifu);
		panel.add(storereply);
		panel.add(commit);
		panel.add(fengexian);
		frame.validate();
	}	
}	
}	
		
		
		
	

