package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class DelReservation extends JFrame {

	private JPanel contentPane;
	private static String ridtext;
	private static int rid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DelReservation frame = new DelReservation();
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
	public DelReservation() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"选择"}));
		try {
			for(int i=0;i<PetsysUtil.serManager.loadAllRes("拒绝").size();i++)
			{
				comboBox.addItem("预约编号："+PetsysUtil.serManager.loadAllRes("拒绝").get(i).getRid());
			}
		} catch (BaseException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		
		
		try {
			for(int i=0;i<PetsysUtil.serManager.loadParRes("完成","预约成功").size();i++)
			{
				comboBox.addItem("预约编号："+PetsysUtil.serManager.loadParRes("完成", "预约成功").get(i).getRid());
			}
		} catch (BaseException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		comboBox.setBounds(31, 41, 115, 27);
		contentPane.add(comboBox);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(327, 243, 117, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ridtext = comboBox.getSelectedItem().toString();
				if(!ridtext.equals("选择"))
				{
					rid = Integer.parseInt(ridtext.substring(5));
					try {
						PetsysUtil.resManager.deleteRes(rid);
						JOptionPane.showMessageDialog(null, "删除预约单成功","提示-删除预约",JOptionPane.INFORMATION_MESSAGE);
					} catch (BaseException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请先选择再删除！","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		button_1.setBounds(192, 40, 117, 29);
		contentPane.add(button_1);
		
		JLabel label = new JLabel("*温馨提示： 只有您拒绝的预约或者进度已经完成的服务才能删除");
		label.setForeground(Color.RED);
		label.setBounds(31, 13, 413, 16);
		contentPane.add(label);
	}

}
