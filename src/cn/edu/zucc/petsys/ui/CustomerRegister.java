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
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CustomerRegister extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField password1;
	private JPasswordField password2;
	private JTextField textField_name;
	private JTextField textField_telnum;
	private JTextField textField_ocontact;
	private JTextField textField_email;
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
					CustomerRegister frame = new CustomerRegister();
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
	public CustomerRegister() {
		setTitle("口袋妖精用户注册界面");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_confirm = new JButton("");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cusers = textField.getText();
				String pwd1 = new String(password1.getPassword());
				String pwd2 = new String(password2.getPassword());
				String ctelnum = textField_telnum.getText();
				String cemail = textField_email.getText();
				String cother = textField_ocontact.getText();
				String cname = textField_name.getText();
				if(isNumeric(cusers)==true)
				{
					JOptionPane.showMessageDialog(null, "账户不能全为数字或为空","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(isContainNumber(cname)==true)
				{
					JOptionPane.showMessageDialog(null, "用户名不能含有数字","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(isNumeric(ctelnum)==false)
				{
					JOptionPane.showMessageDialog(null, "联系电话只能全是数字","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					BeanCus bc = PetsysUtil.cusManager.reg(cusers, pwd1, pwd2, cname, ctelnum, cemail,cother);
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
		btn_confirm.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon0.jpeg"));
		btn_confirm.setBounds(187, 229, 72, 68);
		contentPane.add(btn_confirm);
		
		JLabel label_confirm = new JLabel("Confirm");
		label_confirm.setHorizontalAlignment(SwingConstants.RIGHT);
		label_confirm.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_confirm.setBounds(187, 297, 61, 16);
		contentPane.add(label_confirm);
		
		textField = new JTextField();
		textField.setToolTipText("请输入你喜欢的用户名");
		textField.setBounds(0, 116, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		password1 = new JPasswordField();
		password1.setToolTipText("请输入你的密码");
		password1.setBounds(0, 162, 130, 26);
		contentPane.add(password1);
		
		password2 = new JPasswordField();
		password2.setToolTipText("为了安全，请再次输入你的密码");
		password2.setBounds(0, 200, 130, 26);
		contentPane.add(password2);
		
		JLabel label_newuser = new JLabel("*新的账户：                    *用户姓名：");
		label_newuser.setForeground(Color.BLACK);
		label_newuser.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_newuser.setBounds(34, 101, 278, 16);
		contentPane.add(label_newuser);
		
		JLabel label_renewpwd = new JLabel("*确认密码：                     其他联系：");
		label_renewpwd.setForeground(Color.BLACK);
		label_renewpwd.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_renewpwd.setBounds(34, 185, 225, 16);
		contentPane.add(label_renewpwd);
		
		JLabel label_newpwd = new JLabel("*新的密码：                    *联系电话：");
		label_newpwd.setForeground(Color.BLACK);
		label_newpwd.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_newpwd.setBounds(34, 146, 230, 16);
		contentPane.add(label_newpwd);
		
		textField_name = new JTextField();
		textField_name.setBounds(158, 116, 130, 26);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_ocontact = new JTextField();
		textField_ocontact.setBounds(158, 200, 130, 26);
		contentPane.add(textField_ocontact);
		textField_ocontact.setColumns(10);
		
		JLabel label_back = new JLabel("Back");
		label_back.setHorizontalAlignment(SwingConstants.CENTER);
		label_back.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_back.setBounds(476, 268, 36, 16);
		contentPane.add(label_back);
		
		JButton btn_back = new JButton("");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerLogin cuslogin = new CustomerLogin();
				cuslogin.setVisible(true);
				dispose();
			}
		});
		btn_back.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon2.jpeg"));
		btn_back.setBounds(441, 185, 72, 83);
		contentPane.add(btn_back);
		
		textField_telnum = new JTextField();
		textField_telnum.setBounds(158, 159, 130, 26);
		contentPane.add(textField_telnum);
		textField_telnum.setColumns(10);
		
		textField_email = new JTextField();
		textField_email.setBounds(0, 242, 130, 26);
		contentPane.add(textField_email);
		textField_email.setColumns(10);
		
		JLabel label_1 = new JLabel("温馨提示：*代表必填项");
		label_1.setForeground(Color.RED);
		label_1.setBounds(6, 25, 167, 16);
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel("电子邮箱：");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setBounds(40, 229, 78, 16);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/img1.jpeg"));
		label.setBounds(0, 0, 561, 330);
		contentPane.add(label);
	}
}
