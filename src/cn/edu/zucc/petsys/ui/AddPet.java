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
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AddPet extends JFrame {

	private JPanel contentPane;
	private JTextField textField_pname;
	private static String psex;
	private static String pbreeds;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPet frame = new AddPet();
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
	public AddPet() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 282, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel label1 = new JLabel("宠物昵称：");
		label1.setBounds(23, 75, 65, 16);
		
		contentPane.add(label1);
		
		JLabel label_3 = new JLabel("宠物性别：");
		label_3.setBounds(23, 109, 65, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("宠物照片：");
		label_4.setBounds(23, 176, 65, 16);
		contentPane.add(label_4);
		
		JLabel label_1 = new JLabel("宠物品种：");
		label_1.setBounds(23, 143, 65, 16);
		contentPane.add(label_1);
		
		JComboBox comboBox_psex = new JComboBox();
		comboBox_psex.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					psex = comboBox_psex.getSelectedItem().toString();
					
				}
			}
		});
		comboBox_psex.setModel(new DefaultComboBoxModel(new String[] {"请选择宠物性别", "♂", "♀"}));
		comboBox_psex.setBounds(110, 105, 142, 27);
		contentPane.add(comboBox_psex);
		
		JComboBox comboBox_pbreeds = new JComboBox();
		comboBox_pbreeds.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					pbreeds = comboBox_pbreeds.getSelectedItem().toString();
				}
			}
		});
		comboBox_pbreeds.setModel(new DefaultComboBoxModel(new String[] {"请选择宠物品种", "小火龙", "杰尼龟", "绿毛虫", "皮卡丘", "可达鸭", "地鼠", "伊布", "小锯鳄", "梦幻", "吼吼鲸", "宝石海星", "小海豹", "迷唇姐", "可然翁", "鬼斯", "象牙猪"}));
		comboBox_pbreeds.setBounds(110, 139, 142, 27);
		contentPane.add(comboBox_pbreeds);
		
		textField_pname = new JTextField();
	
		textField_pname.setBounds(112, 70, 136, 26);
		contentPane.add(textField_pname);
		textField_pname.setColumns(10);
		
		JButton button_img = new JButton("上传");
		button_img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cn.edu.zucc.petsys.ui.FileChoose filechoose = new FileChoose();
				filechoose.setVisible(true);
				
			}
		});
		button_img.setBounds(108, 171, 144, 29);
		contentPane.add(button_img);
		
		JButton button_confirm = new JButton("确定");
		button_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pname = textField_pname.getText();
				//System.out.println(FileChoose.text);
				try {
					PetsysUtil.petManager.addPet(pname, pbreeds, psex, FileChoose.text);
					dispose();
				} catch (BaseException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		button_confirm.setBackground(Color.PINK);
		button_confirm.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		button_confirm.setBounds(85, 287, 117, 77);
		contentPane.add(button_confirm);
		JLabel label = new JLabel("");
		label.setBackground(Color.BLACK);
		label.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/shade.jpeg"));
		label.setBounds(0, 0, 282, 489);
		contentPane.add(label);
		
	}
}
