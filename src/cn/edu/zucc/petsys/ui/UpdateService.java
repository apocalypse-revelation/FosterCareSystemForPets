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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class UpdateService extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static String newsername;
	private static String oldsername=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateService frame = new UpdateService();
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
	public UpdateService() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("新服务名：");
		label.setBounds(177, 56, 65, 16);
		contentPane.add(label);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(327, 243, 117, 29);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(252, 51, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"查看"}));
		comboBox.setBounds(39, 26, 92, 27);
		try {
			for(int i=0;i<PetsysUtil.serManager.loadAllService().size();i++)
			{
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
					oldsername = comboBox.getSelectedItem().toString();
				}
			}
		});
		contentPane.add(comboBox);
		
		JLabel label_1 = new JLabel("查看所有服务：");
		label_1.setBounds(6, 6, 92, 16);
		contentPane.add(label_1);
		
		JButton button_1 = new JButton("确认");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newsername = textField.getText();
				oldsername = comboBox.getSelectedItem().toString();
				if(oldsername.equals("查看"))
				{
					JOptionPane.showMessageDialog(null,"请先选择服务","提示-更改服务",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
				if(!newsername.equals(""))
				{
					try {
						PetsysUtil.serManager.updateService(oldsername, newsername);
						JOptionPane.showMessageDialog(null, "更新成功！","提示-更改服务",JOptionPane.INFORMATION_MESSAGE);
						button_1.setVisible(false);
					} catch (BaseException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "更改新名不能为空！","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			}
		});
		button_1.setBounds(6, 243, 117, 29);
		contentPane.add(button_1);
	}

}
