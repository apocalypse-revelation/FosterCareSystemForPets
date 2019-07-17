package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanInfo;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteStaff extends JFrame {

	private JPanel contentPane;
	private static String combo;
	private static int sid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteStaff frame = new DeleteStaff();
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
	public DeleteStaff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"查看"}));
		comboBox.setBounds(26, 18, 117, 27);
		try {
			for(int i=0;i<PetsysUtil.sysManager.loadAllStaff().size();i++)
			{
				//不能删除1号创始人
				if(PetsysUtil.sysManager.loadAllStaff().get(i).getSid()!=1)
					comboBox.addItem("编号：["+PetsysUtil.sysManager.loadAllStaff().get(i).getSid()+"],姓名：["+PetsysUtil.sysManager.loadAllStaff().get(i).getSname()+"],级别：["+PetsysUtil.sysManager.loadAllStaff().get(i).getSrank()+"]");
				
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
					combo = comboBox.getSelectedItem().toString();
					if(!combo.equals("查看"))
					{
					String regex = "\\d*";
					//String regex = "[^0-9]";
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(combo);
					//System.out.println(text);
					
					while(m.find())
					 {
						 if(!"".equals(m.group()))
						 {
								 sid = Integer.parseInt(m.group());
								 break;//只取第一个数字就跳出————防止后面的字段也为数字却传入了iid导致混乱
						 }
					 }
					}
				}
			}
		});
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
				if(comboBox.getSelectedItem().toString().equals("查看"))
				{
					JOptionPane.showMessageDialog(null, "请先选择账号","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					try {
						PetsysUtil.sysManager.deletestaff(sid);
						button_1.setVisible(false);
					} catch (BaseException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		button_1.setBounds(26, 243, 117, 29);
		contentPane.add(button_1);
	}

}
