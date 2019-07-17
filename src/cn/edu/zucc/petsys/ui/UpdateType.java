package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;

public class UpdateType extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static String oldname;
	private static String oldintro;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateType frame = new UpdateType();
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
	public UpdateType() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(327, 125, 117, 29);
		contentPane.add(button_1);
		
		JLabel label = new JLabel("更新类别名：");
		label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(187, 42, 89, 16);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(270, 37, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("更新类别介绍：");
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(174, 92, 102, 16);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("保持原样");
		textField_1.setBounds(270, 87, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"———类别———"}));
		try {
			for(int i=0;i<PetsysUtil.typeManager.loadAll().size();i++)
			{
				comboBox.addItem("类别名："+PetsysUtil.typeManager.loadAll().get(i).getTname());
				
			}
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if(e.getStateChange() == ItemEvent.SELECTED)
				 {
					 //旧名
					 oldname = comboBox.getSelectedItem().toString().substring(4);
					 textField.setText(oldname);
					 if(!"".equals(oldname))
					 {
						 try {
							 oldintro = PetsysUtil.typeManager.loadOne(oldname).getTintroduction();
						} catch (BaseException e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
							// TODO: handle exception
						}
					 }
				 }
			}
		});
		comboBox.setBounds(6, 19, 160, 27);
		contentPane.add(comboBox);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newname = textField.getText();
				String newintro = textField_1.getText();
				try {
					if(comboBox.getSelectedItem().toString().equals("———类别———"))
					{
						JOptionPane.showMessageDialog(null, "请先选择类别！","提示-选择类别" , JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if(!newintro.equals("保持原样"))
						PetsysUtil.typeManager.updateOneType(newname, newintro, oldname);
					else {
						//默认不修改介绍
						PetsysUtil.typeManager.updateOneType(newname, PetsysUtil.typeManager.loadOne(oldname).getTintroduction(), oldname);
					}
					if(!comboBox.getSelectedItem().toString().equals("———类别———"))
						JOptionPane.showMessageDialog(null, "更新成功！","提示-更新类别" , JOptionPane.INFORMATION_MESSAGE);

				} catch (BaseException e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					// TODO: handle exception
				}
			}
		});
		button.setBounds(198, 125, 117, 29);
		contentPane.add(button);
		
		JLabel label_2 = new JLabel("查看类别：");
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_2.setBounds(48, 6, 71, 16);
		contentPane.add(label_2);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
