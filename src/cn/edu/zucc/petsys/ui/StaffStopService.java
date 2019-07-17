package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffStopService extends JFrame {

	private JPanel contentPane;
	private static String combosername;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffStopService frame = new StaffStopService();
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
	public StaffStopService() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("查看未停办的服务");
		label.setBounds(6, 17, 119, 16);
		contentPane.add(label);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"查看"}));
		comboBox.setBounds(6, 37, 111, 27);
		try {
			for(int i=0;i<PetsysUtil.serManager.loadAllService().size();i++)
			{
				//只显示可以预约的
				if(PetsysUtil.serManager.loadAllService().get(i).getSerstatus().equals("可预约"))
					comboBox.addItem(PetsysUtil.serManager.loadAllService().get(i).getSername());
				
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
					combosername = comboBox.getSelectedItem().toString();
					
				}
			}
		});
		contentPane.add(comboBox);
		
		JButton button = new JButton("停办");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBox.getSelectedItem().toString().equals("查看"))
				{
					try {
						PetsysUtil.serManager.stopService(combosername);
						JOptionPane.showMessageDialog(null, "停办成功！","提示-停办",JOptionPane.INFORMATION_MESSAGE);
					} catch (BaseException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请先选择服务","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		button.setBounds(139, 36, 117, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(327, 243, 117, 29);
		contentPane.add(button_1);
	}

}
