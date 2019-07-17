package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanRes;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class StaffDisposeRes extends JFrame {

	private JPanel contentPane;
	private static int rid;
	private static String ridtext;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffDisposeRes frame = new StaffDisposeRes();
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
	public StaffDisposeRes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel label_rid = new JLabel();
		label_rid.setBounds(111, 85, 61, 16);
		contentPane.add(label_rid);
		
		JLabel label_pid = new JLabel();
		label_pid.setBounds(111, 123, 61, 16);
		contentPane.add(label_pid);
		
		JLabel label_ibreeds = new JLabel();
		label_ibreeds.setBounds(111, 155, 61, 16);
		contentPane.add(label_ibreeds);
		JComboBox comboBox = new JComboBox();
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"查看"}));
		try {
			for(int i=0;i<PetsysUtil.resManager.loadAllthroughStat("未完成","预约成功").size();i++)
			{
					comboBox.addItem("预约编号："+PetsysUtil.resManager.loadAllthroughStat("未完成","预约成功").get(i).getRid());
			}
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					if(!comboBox.getSelectedItem().toString().equals("查看"))
					{
					ridtext = comboBox.getSelectedItem().toString().substring(5);
					rid = Integer.parseInt(ridtext);
					try {
						BeanRes br = PetsysUtil.resManager.loadOneRes(rid);
						label_rid.setText(Integer.toString(br.getRid()));
						label_pid.setText(Integer.toString(br.getPid()));
						label_ibreeds.setText(PetsysUtil.petManager.loadPet(br.getPid()).getPbreeds());
					} catch (BaseException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
					else
					{
						label_pid.setText("");
						label_rid.setText("");
						label_ibreeds.setText("");
					}
				}
			}
		});
		
		
		comboBox.setBounds(198, 2, 126, 27);
		contentPane.add(comboBox);
		
		JLabel label = new JLabel("查看预约成功但未完成的服务");
		label.setBounds(6, 6, 180, 16);
		contentPane.add(label);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(327, 243, 117, 29);
		contentPane.add(button);
		
		JLabel label_1 = new JLabel("设置服务完成进度");
		label_1.setBounds(6, 52, 139, 16);
		contentPane.add(label_1);
		
		JButton button_1 = new JButton("完成");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBox.getSelectedItem().toString().equals("查看"))
				{
					
					ridtext = comboBox.getSelectedItem().toString().substring(5);
					rid = Integer.parseInt(ridtext);
					try {
						if(PetsysUtil.resManager.loadOneRes(rid).getRstatus().equals("完成"))
						{
							JOptionPane.showMessageDialog(null, "该进度已经完成，不能重复设置！","错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
						PetsysUtil.resManager.updateRes(rid, "完成");
						JOptionPane.showMessageDialog(null, "进度设置成功","完成",JOptionPane.INFORMATION_MESSAGE);
					} catch (BaseException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请先选择预约","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
		});
		button_1.setBounds(111, 47, 117, 29);
		contentPane.add(button_1);
		
		JLabel label_2 = new JLabel("宠物编号：");
		label_2.setBounds(37, 123, 70, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("宠物品种：");
		label_3.setBounds(37, 155, 70, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("预约编号：");
		label_4.setBounds(37, 85, 70, 16);
		contentPane.add(label_4);
	}

}
