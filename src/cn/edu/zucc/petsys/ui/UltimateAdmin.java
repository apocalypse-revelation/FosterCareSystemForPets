package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UltimateAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UltimateAdmin frame = new UltimateAdmin();
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
	public UltimateAdmin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"查看所有管理员"}));
		comboBox.setBounds(6, 33, 157, 27);
		try {
			for(int i=0;i<PetsysUtil.sysManager.loadAllStaff().size();i++)
			{
				comboBox.addItem("编号：["+PetsysUtil.sysManager.loadAllStaff().get(i).getSid()+"],姓名：["+PetsysUtil.sysManager.loadAllStaff().get(i).getSname()+"],级别：["+PetsysUtil.sysManager.loadAllStaff().get(i).getSrank()+"]");
				
			}
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		contentPane.add(comboBox);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(143, 243, 117, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("点击进入添加管理员界面");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStaff as = new AddStaff();
				as.setVisible(true);
			}
		});
		button_1.setBounds(143, 126, 235, 29);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("点击进入删除管理员界面");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteStaff ds = new DeleteStaff();
				ds.setVisible(true);
			}
		});
		button_2.setBounds(143, 167, 230, 29);
		contentPane.add(button_2);
		
		JLabel label = new JLabel("查看所有管理员信息");
		label.setBounds(6, 6, 157, 16);
		contentPane.add(label);
	}

}
