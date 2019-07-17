package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanPet;
import cn.edu.zucc.petsys.bean.BeanRes;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

public class StaffManageReservation extends JFrame {

	private JPanel contentPane;
	private static String text;
	private static int rid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffManageReservation frame = new StaffManageReservation();
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
	public StaffManageReservation() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel label_retime = new JLabel();
		label_retime.setHorizontalAlignment(SwingConstants.CENTER);
		label_retime.setBounds(268, 56, 176, 16);
		contentPane.add(label_retime);
		JLabel label_sername = new JLabel("");
		label_sername.setBounds(317, 28, 61, 16);
		contentPane.add(label_sername);
		
		JButton button_1 = new JButton("同意预约");
		button_1.setVisible(false);
		
		
		JButton button_2 = new JButton("拒绝预约");
		button_2.setVisible(false);
		JLabel label_pbreeds = new JLabel();
		label_pbreeds.setBounds(317, 96, 61, 16);
		contentPane.add(label_pbreeds);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"查看"}));
		comboBox.setBounds(160, 2, 114, 27);
		try {
			for(int i=0;i<PetsysUtil.serManager.loadAllRes("申请中").size();i++)
			{
				comboBox.addItem("预约编号："+PetsysUtil.serManager.loadAllRes("申请中").get(i).getRid());
			}
		} catch (BaseException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				text = comboBox.getSelectedItem().toString();
				if(!text.equals("查看"))
				{
				rid = Integer.parseInt(text.substring(5));
				try {
					BeanRes br = PetsysUtil.resManager.loadOneRes(rid);
					label_pbreeds.setText(PetsysUtil.petManager.loadPet(br.getPid()).getPbreeds());
					label_retime.setText(br.getRtime().toString());
					label_sername.setText(br.getRsername());
					if(br.getRapply().equals("预约成功"))
					{
						button_1.setVisible(false);
						button_2.setVisible(false);
					}
					else if(br.getRapply().equals("申请中"))
					{
						button_1.setVisible(true);
						button_2.setVisible(true);
					}
					else if(br.getRapply().equals("拒绝"))
					{
						button_1.setVisible(false);
						button_2.setVisible(false);
					}
				} catch (BaseException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				}
			}
		});
		
		
		
		JLabel label = new JLabel("查看申请中的服务预约：");
		label.setBounds(6, 6, 152, 16);
		contentPane.add(label);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(333, 243, 117, 29);
		contentPane.add(button);
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text = comboBox.getSelectedItem().toString();
				if(!text.equals("查看")) 
				{
				try {
					rid = Integer.parseInt(text.substring(5));
					if(PetsysUtil.resManager.loadOneRes(rid).getRapply().equals("预约成功"))
					{
						JOptionPane.showMessageDialog(null,"您已同意了该预约，不能重复预约","错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else if(PetsysUtil.resManager.loadOneRes(rid).getRapply().equals("拒绝"))
					{
						JOptionPane.showMessageDialog(null,"您已拒绝了该预约，不能同意预约了","错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
					PetsysUtil.resManager.alterApply(rid,"预约成功");
					JOptionPane.showMessageDialog(null, "成功！您已同意该用户的预约","错误",JOptionPane.INFORMATION_MESSAGE);
					
				} catch (BaseException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请先选择预约编号","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		button_1.setBounds(6, 51, 117, 29);
		contentPane.add(button_1);
		
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text = comboBox.getSelectedItem().toString();
				if(!text.equals("查看")) 
				{
				try {
					if(PetsysUtil.resManager.loadOneRes(rid).getRapply().equals("预约成功"))
					{
						JOptionPane.showMessageDialog(null,"您已同意了该预约，不能拒绝了","错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else if(PetsysUtil.resManager.loadOneRes(rid).getRapply().equals("拒绝"))
					{
						JOptionPane.showMessageDialog(null,"您拒绝了该预约，不能再拒绝了","错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
					rid = Integer.parseInt(text.substring(5));
					PetsysUtil.resManager.alterApply(rid, "拒绝");
					JOptionPane.showMessageDialog(null, "成功！您已拒绝该用户的预约","错误",JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请先选择预约编号","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		button_2.setBounds(6, 91, 117, 29);
		contentPane.add(button_2);
		
		JLabel label_1 = new JLabel("预约时间:");
		label_1.setBounds(186, 56, 70, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("宠物品种：");
		label_2.setBounds(186, 96, 70, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("服务类型：");
		label_3.setBounds(186, 28, 70, 16);
		contentPane.add(label_3);
		
		contentPane.add(comboBox);
	}
}
