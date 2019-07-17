package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanCus;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class StaffManageCustomer extends JFrame {

	private JPanel contentPane;
	private static String cusers;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffManageCustomer frame = new StaffManageCustomer();
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
	public StaffManageCustomer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 284, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel label = new JLabel("用户编号：");
		label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label.setBounds(23, 81, 65, 16);
		
		contentPane.add(label);
		
		
		
		JLabel label_2 = new JLabel("用户邮箱：");
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_2.setBounds(23, 218, 65, 16);
		contentPane.add(label_2);
		
		JLabel lblNewLabel = new JLabel("用户账号：");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setBounds(23, 182, 65, 16);
		contentPane.add(lblNewLabel);
		
		JLabel label_3 = new JLabel("真实姓名：");
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_3.setBounds(23, 109, 65, 16);
		contentPane.add(label_3);
		
		JLabel label_1 = new JLabel("联系电话：");
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_1.setBounds(23, 143, 65, 16);
		contentPane.add(label_1);
		
		JLabel label_6 = new JLabel("注册时间：");
		label_6.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_6.setBounds(23, 250, 65, 16);
		contentPane.add(label_6);
		
		JLabel label_cid = new JLabel();
		label_cid.setBounds(127, 81, 136, 16);
		contentPane.add(label_cid);
		
		JLabel label_cname = new JLabel();
		label_cname.setBounds(127, 109, 136, 16);
		contentPane.add(label_cname);
		
		JLabel label_tel = new JLabel();
		label_tel.setBounds(127, 143, 136, 16);
		contentPane.add(label_tel);
		
		JLabel label_cusers = new JLabel();
		label_cusers.setBounds(127, 182, 136, 16);
		contentPane.add(label_cusers);
		
		JLabel label_cemail = new JLabel();
		label_cemail.setBounds(127, 218, 136, 16);
		contentPane.add(label_cemail);
		
		JLabel label_creg = new JLabel();
		label_creg.setHorizontalAlignment(SwingConstants.CENTER);
		label_creg.setBounds(100, 250, 183, 16);
		contentPane.add(label_creg);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 130, 283, 12);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 162, 283, 12);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 200, 283, 12);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 237, 283, 12);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 98, 286, 16);
		contentPane.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 267, 283, 12);
		contentPane.add(separator_5);
		
		JComboBox comboBox = new JComboBox();
	
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"选择下列用户"}));
		comboBox.setBounds(0, 0, 147, 27);
		try {
			for(int i=0;i<PetsysUtil.cusManager.loadAll().size();i++)
			{
				comboBox.addItem("用户账号:"+PetsysUtil.cusManager.loadAll().get(i).getCusers());
				
			}
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		contentPane.add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					//截取item内容的string
					cusers = comboBox.getSelectedItem().toString().substring(5);
					if(!cusers.equals("户"))
					{
					try {
						BeanCus bc = PetsysUtil.cusManager.loadCus(cusers);
						label_cid.setText(Integer.toString(bc.getCid()));
						label_cname.setText(bc.getCname());
						label_tel.setText(bc.getCtelnum());
						label_cemail.setText(bc.getCemail());
						if(null==bc.getCregdate())
							label_creg.setText("时间格式有错");
						else 
							label_creg.setText(bc.getCregdate().toString());
						label_cusers.setText(bc.getCusers());
					} catch (BaseException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						
						// TODO: handle exception
					}
					}
				}
			}
		});
		
		JButton button = new JButton("删除此用户");
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PetsysUtil.cusManager.deleteCus(cusers);
					JOptionPane.showMessageDialog(null, "您已经删除了该用户的所有信息", "提示-删除", JOptionPane.YES_NO_CANCEL_OPTION);
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					
					// TODO: handle exception
				}
			}
		});
		button.setBounds(0, 433, 75, 29);
		contentPane.add(button);
		
		JButton button_back = new JButton("赶紧返回");
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_back.setBounds(166, 431, 117, 29);
		contentPane.add(button_back);
		JLabel label_bg = new JLabel("");
		label_bg.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/shade.jpeg"));
		label_bg.setBounds(0, 0, 283, 462);
		contentPane.add(label_bg);
	}
}
