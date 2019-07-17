package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanPet;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PetsChoice extends JFrame {

	private JPanel contentPane;
	private String text;
	public static int pid;
	private static long timediff;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetsChoice frame = new PetsChoice();
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
	public PetsChoice() {
		setTitle("查看-宠物信息");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 282, 511);
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
		label_4.setBounds(105, 272, 65, 16);
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
		separator_4.setBounds(0, 92, 283, 12);
		contentPane.add(separator_4);
		
		//定义聪数据库传来的宠物信息
		JLabel label_pbreeds = new JLabel();
		label_pbreeds.setBounds(122, 218, 61, 16);
		contentPane.add(label_pbreeds);
		
		JLabel label_cname = new JLabel();
		label_cname.setBounds(122, 182, 61, 16);
		contentPane.add(label_cname);
		
		JLabel label_pname = new JLabel();
		label_pname.setBounds(122, 109, 61, 16);
		contentPane.add(label_pname);
		
		JLabel label_pid = new JLabel();
		label_pid.setBounds(122, 75, 61, 16);
		contentPane.add(label_pid);
		
		JLabel label_sex = new JLabel();
		label_sex.setBounds(122, 144, 61, 16);
		contentPane.add(label_sex);
		
		JLabel label_img = new JLabel();
		
		label_img.setBounds(79, 324, 120, 120);
		contentPane.add(label_img);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"请选择您的宠物!"}));
		try {
			for(int i=0;i<PetsysUtil.petManager.loadAll().size();i++)
			{
				comboBox.addItem("编号->"+PetsysUtil.petManager.loadAll().get(i).getPid()+",昵称->"+PetsysUtil.petManager.loadAll().get(i).getPname());
				
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
					 if(!comboBox.getSelectedItem().toString().equals("请选择您的宠物!"))
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
										 label_sex.setText(bp.getPsex());
										 label_img.setIcon(new ImageIcon(bp.getPimg()));
//										 label_reserve.setText(bp.getPreserve());
										 /**
										 if(bp.getPfoster().equals("否"))
											 label_icon.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/no.jpeg"));
										 else if(bp.getPfoster().equals("是"))
											 label_icon.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/yes.jpeg"));
										 */
									} catch (BaseException e2) {
										JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
										return;
										// TODO: handle exception
									}
							 }
					 }
					 else
					 {
						 label_pid.setText("");
						 label_pname.setText("");
						 label_pbreeds.setText("");
						 label_cname.setText("");
						 label_sex.setText("");
						 label_img.setIcon(new ImageIcon(""));
					 }
				 }
				 
				 
			}
		});
		
		JButton button_foster = new JButton("开始寄养");
		button_foster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(!comboBox.getSelectedItem().toString().equals("请选择您的宠物！"))
					{
						if(!PetsysUtil.petManager.loadPet(pid).getPreserve().equals("预约成功"))
						{
							JOptionPane.showMessageDialog(null, "您的宠物还没有预约成功，无法开始寄养","错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
						else if(!PetsysUtil.petManager.loadPet(pid).getSername().equals("寄养"))
						{
							JOptionPane.showMessageDialog(null, "您的宠物还没有预约的服务不是“寄养”，无法开始寄养","错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
							
						PetsysUtil.petManager.UpdatePetFoster(pid);
						//线程刷新
//						new Thread(new Runnable() {
//							@Override
//							public void run() {
//								try {
////									label_icon.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/yes.jpeg"));
//									Thread.sleep(1000);  
//								} catch (InterruptedException e) {
//									e.printStackTrace();
//								}	             
//							}
//						}).start();
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "请先选择宠物！","错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					// TODO: handle exception
				}
				
			}
		});
		button_foster.setBounds(166, -1, 117, 29);
		contentPane.add(button_foster);
		
		JButton button_undofoster = new JButton("取消寄养");
		button_foster.setVisible(false);
		button_undofoster.setVisible(false);
		button_undofoster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					timediff = PetsysUtil.petManager.EndPetFoster(pid);
					JOptionPane.showMessageDialog(null, " 您已寄养了"+timediff+"天，请于当天来店取回您的宠物。", "提示-寄养天数", JOptionPane.INFORMATION_MESSAGE);
//					new Thread(new Runnable() {
//			            @Override
//			            public void run() {
//			            	 try {
//			                     
////			                         label_icon.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/no.jpeg"));
//			                         Thread.sleep(1000);    
//			                     
//			                 } catch (InterruptedException e) {
//			                     e.printStackTrace();
//			                 }	             
//			                 
//			            }
//			        }).start();
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					// TODO: handle exception
				}
			}
		});
		button_undofoster.setBounds(166, 34, 117, 29);
		contentPane.add(button_undofoster);
		
		JButton button_2 = new JButton("申请预约");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerReservation cr = new CustomerReservation();
				cr.setVisible(true);
			}
		});
		button_2.setBounds(105, 460, 94, 29);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("注销该宠物信息");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!comboBox.getSelectedItem().toString().equals("请选择您的宠物！"))
				{
					text  = comboBox.getSelectedItem().toString();
					//正则表达式转换String取得pid 并查询该pid的BeanPet信息
					String regex = "\\d*";
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(text);
					while(m.find())
					{
						if(!"".equals(m.group()))
						{
							pid = Integer.parseInt(m.group());
							break;
						}
					}
					try {
						for(int i=0;i<PetsysUtil.resManager.loadAllthroughPid(pid).size();i++)
						{
							if(!PetsysUtil.resManager.loadAllthroughPid(pid).get(i).getRapply().equals("申请中")||(PetsysUtil.resManager.loadAllthroughPid(pid).get(i).getRapply().equals("预约成功")&&PetsysUtil.resManager.loadAllthroughPid(pid).get(i).getRstatus().equals("未完成")))
							{
								JOptionPane.showMessageDialog(null, "不能注销，您的该宠物存在正在预约或预约成功但服务未完成的情况！","错误",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						PetsysUtil.petManager.deletePet(pid);
						JOptionPane.showMessageDialog(null, "注销成功","提示-注销宠物",JOptionPane.INFORMATION_MESSAGE);
					} catch (BaseException e3) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e3.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请先选择宠物再注销","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		button_3.setBounds(0, 460, 117, 29);
		contentPane.add(button_3);
		
		comboBox.setBounds(0, 0, 160, 27);
		contentPane.add(comboBox);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(192, 460, 91, 29);
		contentPane.add(button);
//		label_6.setVisible(false);
		
		JLabel label_bg = new JLabel();
		label_bg.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/shade.jpeg"));
		label_bg.setBounds(0, 0, 283, 489);
		contentPane.add(label_bg);
		
		
		
	}
}
