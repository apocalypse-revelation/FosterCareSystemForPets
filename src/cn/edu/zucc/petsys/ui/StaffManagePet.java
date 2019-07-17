package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanPet;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffManagePet extends JFrame {

	private JPanel contentPane;
	private String text;
	private static int pid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffManagePet frame = new StaffManagePet();
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
	public StaffManagePet() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 286, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel label = new JLabel("宠物编号：");
		label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label.setBounds(23, 75, 65, 16);
		
		contentPane.add(label);
		
		
		
		JLabel label_2 = new JLabel("宠物品种：");
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_2.setBounds(23, 218, 65, 16);
		contentPane.add(label_2);
		
		JLabel lblNewLabel = new JLabel("宠物主人：");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setBounds(23, 182, 65, 16);
		contentPane.add(lblNewLabel);
		
		JLabel label_3 = new JLabel("宠物昵称：");
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_3.setBounds(23, 109, 65, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("宠物照片：");
		label_4.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_4.setBounds(107, 312, 65, 16);
		contentPane.add(label_4);
		
		JLabel label_1 = new JLabel("宠物性别：");
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_1.setBounds(23, 143, 65, 16);
		contentPane.add(label_1);
		
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
		separator_4.setBounds(0, 90, 286, 16);
		contentPane.add(separator_4);
		
		JLabel label_pid = new JLabel("");
		label_pid.setBounds(122, 75, 61, 16);
		contentPane.add(label_pid);
		
		JLabel label_pname = new JLabel("");
		label_pname.setBounds(122, 109, 61, 16);
		contentPane.add(label_pname);
		
		JLabel label_psex = new JLabel("");
		label_psex.setBounds(122, 143, 61, 16);
		contentPane.add(label_psex);
		
		JLabel label_cname = new JLabel("");
		label_cname.setBounds(122, 182, 61, 16);
		contentPane.add(label_cname);
		
		JLabel label_pbreeds = new JLabel("");
		label_pbreeds.setBounds(122, 218, 61, 16);
		contentPane.add(label_pbreeds);
		
		JLabel label_img = new JLabel("");
		label_img.setBounds(60, 351, 161, 139);
		contentPane.add(label_img);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"查看下列所有宠物！"}));
		try {
			for(int i=0;i<PetsysUtil.sysManager.loadAllpet().size();i++)
			{
				comboBox.addItem("编号->"+PetsysUtil.sysManager.loadAllpet().get(i).getPid()+",昵称->"+PetsysUtil.sysManager.loadAllpet().get(i).getPname());
				
			}
		} catch (BaseException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if(e.getStateChange() == ItemEvent.SELECTED)
				 {
					 text  = comboBox.getSelectedItem().toString();
					//正则表达式转换String取得pid 并查询该pid的BeanPet信息
					 String regex = "\\d*";
					 Pattern p = Pattern.compile(regex);
					 Matcher m = p.matcher(text);
					 
					 while(m.find())
					 {
						 if(!"".equals(m.group()))
							 try {
								 
								 pid = Integer.parseInt(m.group());
								 BeanPet bp = PetsysUtil.petManager.loadPet(Integer.parseInt(m.group()));
								 label_pid.setText(Integer.toString(bp.getPid()));
								 label_pname.setText(bp.getPname());
								 label_pbreeds.setText(bp.getPbreeds());
								 label_cname.setText(PetsysUtil.cusManager.getName(bp.getCid()));
								 label_psex.setText(bp.getPsex());
								 label_img.setIcon(new ImageIcon(bp.getPimg()));
	 
							} catch (BaseException e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
								return;
								// TODO: handle exception
							}
					 }
				 }
			}
		});
		comboBox.setBounds(6, 6, 190, 27);
		contentPane.add(comboBox);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(163, 255, 117, 29);
		contentPane.add(button);
		
		JLabel label_bg = new JLabel("");
		label_bg.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/shade.jpeg"));
		label_bg.setBounds(0, 0, 286, 496);
		contentPane.add(label_bg);
	}

}
