package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddStaff extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField_name;
	private JTextField textField_rank;
	private JTextField textField_user;

	//判断是否都为数字
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
					AddStaff frame = new AddStaff();
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
	public AddStaff() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("员工密码：");
		label.setBounds(6, 59, 75, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("员工姓名：");
		label_1.setBounds(6, 99, 75, 16);
		contentPane.add(label_1);
		
		JButton button = new JButton("新建账号");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textField_user.getText();
				String pwd = new String(passwordField.getPassword());
				String name = textField_name.getText();
				String rank = textField_rank.getText();
				if(isNumeric(user)==true)
				{
					JOptionPane.showMessageDialog(null, "管理员账户不能都为数字","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					PetsysUtil.sysManager.addStaff(user,pwd, name, rank);
					button.setVisible(false);
				} catch (BaseException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		button.setBounds(6, 243, 117, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(327, 243, 117, 29);
		contentPane.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(75, 54, 130, 26);
		contentPane.add(passwordField);
		
		textField_name = new JTextField();
		textField_name.setBounds(75, 94, 130, 26);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		JLabel label_2 = new JLabel("员工级别：");
		label_2.setBounds(6, 145, 65, 16);
		contentPane.add(label_2);
		
		textField_rank = new JTextField();
		textField_rank.setBounds(75, 140, 130, 26);
		contentPane.add(textField_rank);
		textField_rank.setColumns(10);
		
		JLabel lblroot = new JLabel("提示：最高级别为“超级管理”，其余级别无法进入root权限");
		lblroot.setForeground(Color.RED);
		lblroot.setFont(new Font("Lucida Grande", Font.BOLD, 8));
		lblroot.setBounds(208, 147, 222, 16);
		contentPane.add(lblroot);
		
		JLabel label_3 = new JLabel("员工账号：");
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_3.setBounds(6, 26, 65, 16);
		contentPane.add(label_3);
		
		textField_user = new JTextField();
		textField_user.setBounds(75, 21, 130, 26);
		contentPane.add(textField_user);
		textField_user.setColumns(10);
	}
}
