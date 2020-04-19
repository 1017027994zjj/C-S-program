package 用户界面;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Dateclass.Evaluate;

public class Userremarkframe {

	public JFrame frame;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Userremarkframe window = new Userremarkframe();
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
	public Userremarkframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * 用户评价界面
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setTitle("用户评价:");
		frame.setBounds(100, 100, 591, 655);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	int store_id = Customerframe.store_id;
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
		
		
		
		JLabel fengexian=new JLabel("______________________________________________________");
		fengexian.setFont(new Font("宋体", Font.BOLD, 14));
		
		panel.add(nickname);
		panel.add(userremark);
		panel.add(huifu);
		panel.add(storereply);
		panel.add(fengexian);
		frame.validate();
	}	
}	
}	
		
		
		