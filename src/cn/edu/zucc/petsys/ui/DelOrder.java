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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class DelOrder extends JFrame {

	private JPanel contentPane;
	private static String oidtext;
	private static int oid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DelOrder frame = new DelOrder();
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
	public DelOrder() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					oidtext = comboBox.getSelectedItem().toString();
					if(!oidtext.equals("查看"))
					{
						oid = Integer.parseInt(oidtext.substring(4));
					}
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"选择"}));
		comboBox.setBounds(146, 3, 117, 27);
		try {
			for(int i=0;i<PetsysUtil.ordManager.loadOrdwithStatus("商家取消").size();i++)
			{
				comboBox.addItem("编号->"+PetsysUtil.ordManager.loadOrdwithStatus("商家取消").get(i).getOid());
			}
			
		} catch (BaseException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		
		
		try {
			for(int i=0;i<PetsysUtil.ordManager.loadOrdwithStatus("已收货").size();i++)
			{
				comboBox.addItem("编号->"+PetsysUtil.ordManager.loadOrdwithStatus("已收货").get(i).getOid());
			}
		} catch (BaseException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		
		
		try {
			for(int i=0;i<PetsysUtil.ordManager.loadOrdwithStatus("用户取消").size();i++)
			{
				comboBox.addItem("编号->"+PetsysUtil.ordManager.loadOrdwithStatus("用户取消").get(i).getOid());
			}
		} catch (BaseException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
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
		button.setBounds(333, 243, 117, 29);
		contentPane.add(button);
		
		JLabel label = new JLabel("查看可以删除的订单：");
		label.setBounds(6, 7, 153, 16);
		contentPane.add(label);
		
		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oidtext = comboBox.getSelectedItem().toString();
				if(!oidtext.equals("选择"))
				{
					oid = Integer.parseInt(oidtext.substring(4));
					try {
						PetsysUtil.ordManager.deleteOrd(oid);
						JOptionPane.showMessageDialog(null, "删除订单成功","提示-删除订单",JOptionPane.INFORMATION_MESSAGE);
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
		button_1.setBounds(146, 67, 117, 29);
		contentPane.add(button_1);
	}

}
