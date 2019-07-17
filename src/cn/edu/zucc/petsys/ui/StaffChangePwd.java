package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffChangePwd extends JFrame {

	private JPanel contentPane;
	private JPasswordField oldpassword;
	private JPasswordField newpassword;
	private JButton button;
	private JButton button_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffChangePwd frame = new StaffChangePwd();
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
	public StaffChangePwd() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("原密码：");
		label_1.setBounds(71, 119, 61, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("新密码：");
		label_2.setBounds(71, 156, 61, 16);
		contentPane.add(label_2);
		
		button = new JButton("返回");
		button.setBounds(333, 249, 117, 29);
		contentPane.add(button);
		
		button_1 = new JButton("确认");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button_1.setBounds(6, 249, 117, 29);
		contentPane.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(169, 114, 130, 26);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(169, 151, 130, 26);
		contentPane.add(passwordField_1);
	}

}
