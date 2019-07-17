package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddType extends JFrame {

	private JPanel contentPane;
	private JTextField textField_tname;
	private JTextField textField_intro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddType frame = new AddType();
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
	public AddType() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_tname = new JTextField();
		textField_tname.setBounds(143, 83, 130, 26);
		contentPane.add(textField_tname);
		textField_tname.setColumns(10);
		
		textField_intro = new JTextField();
		textField_intro.setBounds(143, 132, 130, 26);
		contentPane.add(textField_intro);
		textField_intro.setColumns(10);
		
		JLabel label = new JLabel("类别名：");
		label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label.setBounds(98, 88, 52, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("（可选）类别介绍：");
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_1.setBounds(34, 137, 117, 16);
		contentPane.add(label_1);
		
		JButton button_confirm = new JButton("确定");
		button_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String tname = textField_tname.getText();
					String tintro = textField_intro.getText();
					PetsysUtil.typeManager.addType(tname, tintro);
					JOptionPane.showMessageDialog(null, "添加成功！","提示-添加类别" , JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					// TODO: handle exception
				}
			}
		});
		button_confirm.setBounds(129, 170, 75, 29);
		contentPane.add(button_confirm);
		
		JButton button_back = new JButton("返回");
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_back.setBounds(211, 170, 75, 29);
		contentPane.add(button_back);
	}
}
