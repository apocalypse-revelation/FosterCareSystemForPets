package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanStaff;
import cn.edu.zucc.petsys.function.CustomerManager;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class StaffLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_user;
	private JPasswordField passwordField_pwd;
	//判断字符串是否都为数字
//		public static boolean isNumeric(String str)
//			{
//			   for (int i = str.length();--i>=0;)
//			   {  
//			        if (!Character.isDigit(str.charAt(i)))
//			        {
//			            return false;
//			        }
//			   }
//			    return true;
//			 }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffLogin frame = new StaffLogin();
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
	public StaffLogin() {
		setTitle("口袋妖精管理员后台登录界面");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 532, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_user = new JTextField();
		textField_user.setToolTipText("请输入你的员工编号");
		textField_user.setBounds(237, 136, 96, 26);
		contentPane.add(textField_user);
		textField_user.setColumns(10);
		
		JButton btn_login = new JButton("");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();
				String usertext = textField_user.getText();
				if(usertext.equals(""))
				{
					JOptionPane.showMessageDialog(null, "账号不能为空","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
					
					
					String pwd = new String(passwordField_pwd.getPassword());
					if(pwd.equals(""))
					{
						JOptionPane.showMessageDialog(null, "密码不能为空","错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						BeanStaff.currentLoginStaff = PetsysUtil.sysManager.login(usertext, pwd);
						StaffManager sm = new StaffManager();
						sm.setVisible(true);
						dispose();
					} catch (BaseException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
			}
		});
		btn_login.setToolTipText("Go");
		btn_login.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/login.jpeg"));
		btn_login.setBounds(260, 56, 47, 48);
		contentPane.add(btn_login);
		
		JLabel label_pwd = new JLabel("密码：");
		label_pwd.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_pwd.setBounds(202, 174, 39, 16);
		contentPane.add(label_pwd);
		
		JLabel label_users = new JLabel("账号：");
		label_users.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_users.setBounds(202, 141, 39, 16);
		contentPane.add(label_users);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 //StaffLogin stafflogin = new StaffLogin();
				 //stafflogin.dispatchEvent(new WindowEvent(stafflogin,WindowEvent.WINDOW_CLOSING));
				 //setVisible(false);
				Starter starter = new Starter();
				starter.setVisible(true);
				dispose();
			}
		});
		button.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon2.jpeg"));
		button.setBounds(473, 88, 77, 89);
		contentPane.add(button);
		
		passwordField_pwd = new JPasswordField();
		passwordField_pwd.setToolTipText("请输入你的密码");
		passwordField_pwd.setBounds(237, 169, 96, 26);
		contentPane.add(passwordField_pwd);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogin.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblLogin.setBounds(265, 101, 61, 16);
		contentPane.add(lblLogin);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblBack.setBounds(495, 174, 31, 16);
		contentPane.add(lblBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/store.jpeg"));
		label.setBounds(0, 0, 532, 205);
		contentPane.add(label);
	}
}
