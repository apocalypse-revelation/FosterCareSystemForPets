package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Starter extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Starter frame = new Starter();
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
	public Starter() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_staff = new JButton("");
		btn_staff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffLogin staffLogin = new StaffLogin();
				staffLogin.setVisible(true);
				dispose();
			}
		});
		btn_staff.setToolTipText("点击进入操作员界面入口");
		btn_staff.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon1.jpeg"));
		btn_staff.setBounds(546, 0, 61, 59);
		contentPane.add(btn_staff);
		
		JLabel lblNewLabel = new JLabel("Customer");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		lblNewLabel.setBounds(16, 276, 124, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblStaff = new JLabel("Staff ");
		lblStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblStaff.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblStaff.setToolTipText("点击进入操作员界面");
		lblStaff.setBounds(556, 59, 45, 16);
		contentPane.add(lblStaff);
		
		JButton btn_cus = new JButton("");
		btn_cus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerLogin cuslog = new CustomerLogin();
				cuslog.setVisible(true);
				dispose();
			}
		});
		btn_cus.setToolTipText("点击进入用户界面入口");
		btn_cus.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/b4.jpeg"));
		btn_cus.setBounds(6, 214, 66, 66);
		contentPane.add(btn_cus);
		
		JButton btn_back = new JButton("");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn_back.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon2.jpeg"));
		btn_back.setBounds(536, 177, 71, 102);
		contentPane.add(btn_back);
		
		JLabel lblBack = new JLabel("Exit");
		lblBack.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setBounds(562, 276, 45, 16);
		contentPane.add(lblBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/background.jpeg"));
		label.setBounds(0, 0, 607, 309);
		contentPane.add(label);
	}
}
