package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanCus;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CustomerChangePwd extends JFrame {

	private JPanel contentPane;
	private JTextField textField_users;
	private JButton btn_confirm;
	private JPasswordField oldpassword;
	private JPasswordField newpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerChangePwd frame = new CustomerChangePwd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerChangePwd() {
		setTitle("口袋妖精用户密码修改界面");
		setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/cw/Downloads/db/image/login.jpeg"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 434, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_users = new JTextField();
		textField_users.setBounds(48, 130, 82, 26);
		contentPane.add(textField_users);
		textField_users.setColumns(10);
		
		btn_confirm = new JButton("");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cusers = textField_users.getText();
				String oldpwd = new String(oldpassword.getPassword());
				String newpwd = new String(newpassword.getPassword());
				try {
					PetsysUtil.cusManager.changePwd(cusers, oldpwd, newpwd);
					CustomerLogin cuslogin = new CustomerLogin();
					cuslogin.setVisible(true);
					dispose();
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					// TODO: handle exception
				}
			}
		});
		btn_confirm.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/bgr.png"));
		btn_confirm.setBounds(263, 175, 43, 47);
		contentPane.add(btn_confirm);
		
		oldpassword = new JPasswordField();
		oldpassword.setBounds(48, 168, 82, 26);
		contentPane.add(oldpassword);
		
		newpassword = new JPasswordField();
		newpassword.setBounds(48, 206, 82, 26);
		contentPane.add(newpassword);
		
		JLabel label_1 = new JLabel("用户名：");
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_1.setBounds(6, 135, 61, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("原密码：");
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_2.setBounds(6, 173, 61, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("新密码：");
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_3.setBounds(6, 212, 61, 16);
		contentPane.add(label_3);
		
		JLabel lblBack = new JLabel("back");
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setBounds(391, 244, 43, 16);
		contentPane.add(lblBack);
		
		JButton btn_back = new JButton("");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerLogin cuslog = new CustomerLogin();
				cuslog.setVisible(true);
				dispose();
			}
		});
		btn_back.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon2.jpeg"));
		btn_back.setBounds(361, 163, 73, 81);
		contentPane.add(btn_back);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/bgs_icon.jpeg"));
		label.setBounds(0, 0, 434, 260);
		contentPane.add(label);
	}
}
