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
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CustomerLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txt_users;
	private JPasswordField passwordField;

	//判断字符串是否包含数字
	public static boolean isContainNumber(String company) {

        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(company);
        if (m.find()) {
            return true;
        }
        return false;
    }
	//判断字符串是否都为数字
		public static boolean isNumeric(String str)
			{
			   for (int i = str.length();--i>=0;)
			   {  
			        if (!Character.isDigit(str.charAt(i)))
			        {
			            return false;
			        }
			   }
			    return true;
			 }
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerLogin frame = new CustomerLogin();
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
	public CustomerLogin() {
		setTitle("口袋妖精用户登录界面");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 313, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_users = new JLabel("账户：");
		label_users.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_users.setBounds(77, 160, 39, 16);
		contentPane.add(label_users);
		
		JLabel label_pwd = new JLabel("密码：");
		label_pwd.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_pwd.setBounds(77, 204, 39, 16);
		contentPane.add(label_pwd);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("输入你的密码");
		passwordField.setBounds(116, 199, 79, 26);
		contentPane.add(passwordField);
		
		txt_users = new JTextField();
		txt_users.setForeground(Color.BLACK);
		txt_users.setToolTipText("输入你的账户名");
		txt_users.setBounds(116, 155, 79, 26);
		contentPane.add(txt_users);
		txt_users.setColumns(10);
		
		JButton btn_signup = new JButton("");
		btn_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerRegister cusreg = new CustomerRegister();
				cusreg.setVisible(true);
				dispose();
			}
		});
		btn_signup.setToolTipText("注册一个新账号！");
		btn_signup.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon0.jpeg"));
		btn_signup.setBounds(256, 0, 65, 70);
		contentPane.add(btn_signup);
		
		JLabel label_signup = new JLabel("SignUp");
		label_signup.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_signup.setHorizontalAlignment(SwingConstants.CENTER);
		label_signup.setBounds(257, 68, 61, 16);
		contentPane.add(label_signup);
		
		JButton button_back = new JButton("");
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Starter starter = new Starter();
				starter.setVisible(true);
				dispose();
			}
		});
		button_back.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon2.jpeg"));
		button_back.setBounds(242, 279, 79, 84);
		contentPane.add(button_back);
		
		JLabel label_back = new JLabel("Back");
		label_back.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_back.setHorizontalAlignment(SwingConstants.LEFT);
		label_back.setBounds(278, 364, 31, 16);
		contentPane.add(label_back);
		
		JButton button_login = new JButton("");
		button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cusers = txt_users.getText();
				String cpwd = new String(passwordField.getPassword());
				if(isNumeric(txt_users.getText())==true)
				{
					JOptionPane.showMessageDialog(null, "用户名不能全是数字","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					
					BeanCus.currentLoginUser = PetsysUtil.cusManager.login(cusers, cpwd);//保存当前login信息
					CustomerInterface cusif = new CustomerInterface();
					cusif.setVisible(true);
					dispose();
				} catch (BaseException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
		});
		button_login.setToolTipText("登录！");
		button_login.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/login.jpeg"));
		button_login.setBounds(273, 156, 39, 47);
		contentPane.add(button_login);
		
		JLabel label_login = new JLabel("Login");
		label_login.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_login.setHorizontalAlignment(SwingConstants.RIGHT);
		label_login.setBounds(264, 195, 46, 26);
		contentPane.add(label_login);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CustomerChangePwd cuschangepwd = new CustomerChangePwd();
				cuschangepwd.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon1.jpeg"));
		button.setBounds(0, 0, 61, 60);
		contentPane.add(button);
		
		JLabel lblChange = new JLabel("ChangePwd");
		lblChange.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblChange.setBounds(0, 60, 90, 16);
		contentPane.add(lblChange);
		
		JLabel label_background = new JLabel("");
		label_background.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/customerloginbg.jpeg"));
		label_background.setBounds(0, 0, 314, 421);
		contentPane.add(label_background);
	}
}
