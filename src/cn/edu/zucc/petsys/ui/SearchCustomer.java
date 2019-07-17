package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanOrd;
import cn.edu.zucc.petsys.bean.BeanPet;
import cn.edu.zucc.petsys.bean.BeanRes;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SearchCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static int cid;
	private static String cusers;
	private static String cidtext;
	private static int pid;
	private static String pidtext;
	private static String oidtext;
	private static int oid;
	private static String ridtext;
	private static int rid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchCustomer frame = new SearchCustomer();
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
	public SearchCustomer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel label_pid = new JLabel();
		label_pid.setBounds(89, 198, 61, 16);
		contentPane.add(label_pid);
		
		JLabel label_pname = new JLabel();
		label_pname.setBounds(89, 238, 61, 16);
		contentPane.add(label_pname);
		
		JLabel label_pbreeds = new JLabel();
		label_pbreeds.setBounds(89, 277, 61, 16);
		contentPane.add(label_pbreeds);
		
		JLabel label_psex = new JLabel();
		label_psex.setBounds(89, 317, 61, 16);
		contentPane.add(label_psex);
		
		JLabel label_oid = new JLabel();
		label_oid.setBounds(306, 198, 61, 16);
		contentPane.add(label_oid);
		
		JLabel label_iname = new JLabel();
		label_iname.setBounds(306, 238, 61, 16);
		contentPane.add(label_iname);
		
		JLabel label_oquantity = new JLabel();
		label_oquantity.setBounds(306, 277, 61, 16);
		contentPane.add(label_oquantity);
		
		JLabel label_oprice = new JLabel();
		label_oprice.setBounds(306, 317, 61, 16);
		contentPane.add(label_oprice);
		
		JLabel label_otime = new JLabel();
		label_otime.setHorizontalAlignment(SwingConstants.CENTER);
		label_otime.setBounds(306, 358, 127, 16);
		contentPane.add(label_otime);
		
		JLabel label_ostatus = new JLabel();
		label_ostatus.setBounds(306, 400, 61, 16);
		contentPane.add(label_ostatus);
		
		JLabel label_rid = new JLabel();
		label_rid.setBounds(541, 198, 61, 16);
		contentPane.add(label_rid);
		
		JLabel label_res_pname = new JLabel();
		label_res_pname.setBounds(541, 238, 61, 16);
		contentPane.add(label_res_pname);
		
		JLabel label_sername = new JLabel();
		label_sername.setBounds(541, 277, 61, 16);
		contentPane.add(label_sername);
		
		JLabel label_rapply = new JLabel();
		label_rapply.setBounds(541, 317, 61, 16);
		contentPane.add(label_rapply);
		
		JLabel label_rstatus = new JLabel();
		label_rstatus.setBounds(541, 358, 61, 16);
		contentPane.add(label_rstatus);
		
		JLabel label_rtime = new JLabel();
		label_rtime.setHorizontalAlignment(SwingConstants.CENTER);
		label_rtime.setBounds(541, 400, 138, 16);
		contentPane.add(label_rtime);
		
		JLabel label_rrealfinished = new JLabel();
		label_rrealfinished.setHorizontalAlignment(SwingConstants.CENTER);
		label_rrealfinished.setBounds(541, 439, 138, 16);
		contentPane.add(label_rrealfinished);
		
		JLabel label_18 = new JLabel("宠物照片：");
		label_18.setBounds(72, 358, 71, 16);
		contentPane.add(label_18);
		
		JLabel label_pgraphics = new JLabel("");
		label_pgraphics.setBounds(40, 386, 130, 108);
		contentPane.add(label_pgraphics);
		JComboBox comboBox_pet = new JComboBox();
		JComboBox comboBox_user = new JComboBox();
		JComboBox comboBox_order = new JComboBox();
		JComboBox comboBox_res = new JComboBox();
		comboBox_res.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					ridtext = comboBox_res.getSelectedItem().toString();
					if(!ridtext.equals("选择预约信息"))
					{
						rid = Integer.parseInt(ridtext.substring(5));
						try {
							BeanRes br = PetsysUtil.resManager.loadOneRes(rid);
							label_rid.setText(Integer.toString(br.getRid()));
							label_rapply.setText(br.getRapply());
							if(br.getRrealfinished()!=null)
								label_rrealfinished.setText(br.getRrealfinished().toString());
							else
								label_rrealfinished.setText("未完成");

							label_rstatus.setText(br.getRstatus());
							label_rtime.setText(br.getRtime().toString());
							label_res_pname.setText(PetsysUtil.petManager.loadPet(br.getPid()).getPname());
							label_sername.setText(br.getRsername());
						} catch (BaseException e2) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					else
					{
						label_rid.setText("");
						label_rapply.setText("");
						label_rrealfinished.setText("");
						label_rstatus.setText("");
						label_rtime.setText("");
						label_res_pname.setText("");
						label_sername.setText("");
					}
				}
			}
		});
		comboBox_pet.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					pidtext = comboBox_pet.getSelectedItem().toString();
					if(!pidtext.equals("选择宠物信息"))
					{
						pid = Integer.parseInt(pidtext.substring(5));
						try {
							BeanPet bp = PetsysUtil.petManager.loadPet(pid);
							label_pid.setText(Integer.toString(bp.getPid()));
							label_pbreeds.setText(bp.getPbreeds());
							label_pgraphics.setIcon(new ImageIcon(bp.getPimg()));
							label_pname.setText(bp.getPname());
							label_psex.setText(bp.getPsex());
							
							///
							///										///
							///										///
							///										///	
							///			预约服务信息item添加			///
							for(int i=1;i<comboBox_res.getItemCount();)
							{
							comboBox_res.removeItemAt(i);
							
							}//校正完毕
							new Thread(new Runnable() {
					            @Override
					            public void run() {
					            	 try {
					            		 try {
					            			 //宠物信息 pid
												for(int i=0;i<PetsysUtil.resManager.loadAllthroughPid(pid).size();i++)	
													comboBox_res.addItem("预约编号："+PetsysUtil.resManager.loadAllthroughPid(pid).get(i).getRid());
										} catch (BaseException e2) {
											// TODO: handle exception
											JOptionPane.showMessageDialog(null,	e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
											return;
										}
					                         Thread.sleep(1000);    
					                 } catch (InterruptedException e) {
					                     e.printStackTrace();
					                 }	             
					                 
					            }
					        }).start();
						} catch (BaseException e2) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					else
					{
						label_pid.setText("");
						label_pbreeds.setText("");
						label_pgraphics.setIcon(null);
						label_pname.setText("");
						label_psex.setText("");

					}
					
				}
			}
		});
		
		comboBox_order.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					oidtext = comboBox_order.getSelectedItem().toString();
					if(!oidtext.equals("选择订单信息"))
					{
						oid = Integer.parseInt(oidtext.substring(5));
						try {
							BeanOrd bo = PetsysUtil.ordManager.loadOneOrd(oid);
							label_oid.setText(Integer.toString(bo.getOid()));
							label_oprice.setText(Double.toString(bo.getOprice()));
							label_oquantity.setText(Integer.toString(bo.getOquantity()));
							label_ostatus.setText(bo.getOstatus());
							label_otime.setText(bo.getOtime().toString());
							label_iname.setText(PetsysUtil.comManager.getCommodity(bo.getIid()).getIname());
						} catch (BaseException e2) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					else
					{
						label_oid.setText("");
						label_oprice.setText("");
						label_oquantity.setText("");
						label_ostatus.setText("");
						label_otime.setText("");
						label_iname.setText("");

					}
				}
			}
		});
		try {
			for(int i=0;i<PetsysUtil.cusManager.loadAll().size();i++)
			{
				comboBox_user.addItem("用户账号:"+PetsysUtil.cusManager.loadAll().get(i).getCusers());
				
			}
		} catch (BaseException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		comboBox_user.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					 for(int i=1;i<comboBox_pet.getItemCount();)
						{
						comboBox_pet.removeItemAt(i);
						}//校正完毕
					 for(int i=1;i<comboBox_order.getItemCount();)
						{
						comboBox_order.removeItemAt(i);
						}//校正完毕
					 
					cidtext = comboBox_user.getSelectedItem().toString();
					if(!cidtext.equals("选择用户信息"))
					{
						try {
							cusers = cidtext.substring(3);
							cid = PetsysUtil.cusManager.loadCus(cusers).getCid();
							new Thread(new Runnable() {
					            @Override
					            public void run() {
					            	 try {
					            		 try {
					            			 //宠物信息 pid
												for(int i=0;i<PetsysUtil.petManager.loadAllthroughCid(cid).size();i++)	
													comboBox_pet.addItem("宠物编号："+PetsysUtil.petManager.loadAllthroughCid(cid).get(i).getPid());
										} catch (BaseException e2) {
											// TODO: handle exception
											JOptionPane.showMessageDialog(null,	e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
											return;
										}
					                         Thread.sleep(1000);    
					                 } catch (InterruptedException e) {
					                     e.printStackTrace();
					                 }	             
					                 
					            }
					        }).start();
							new Thread(new Runnable() {
								@Override
								public void run() {
									try {
										try {
											//订单信息 oid
											for(int i=0;i<PetsysUtil.ordManager.loadcusAllord(cid).size();i++)	
												comboBox_order.addItem("订单编号："+PetsysUtil.ordManager.loadcusAllord(cid).get(i).getOid());
										} catch (BaseException e2) {
											// TODO: handle exception
											JOptionPane.showMessageDialog(null,	e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
											return;
										}
										Thread.sleep(1000);    
									} catch (InterruptedException e) {
										e.printStackTrace();
									}	             
								}
							}).start();
						} catch (BaseException e2) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null,	e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
			}
		});
		comboBox_user.setModel(new DefaultComboBoxModel(new String[] {"选择用户信息"}));
		comboBox_user.setBounds(284, 26, 149, 27);
		contentPane.add(comboBox_user);
		
		JLabel label = new JLabel("模糊查询（用户账号）：");
		label.setBounds(0, 30, 143, 16);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(142, 25, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("搜索");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=1;i<comboBox_user.getItemCount();)
				{
				comboBox_user.removeItemAt(i);
				}	
				String fuzzycuser = textField.getText();
				if(fuzzycuser.equals(""))
				{
					JOptionPane.showMessageDialog(null,	"用户账号输入不能为空！","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					
						for(int i=0;i<PetsysUtil.cusManager.fuzzySearch(fuzzycuser).size();i++)
						{
							comboBox_user.addItem("账号:"+PetsysUtil.cusManager.fuzzySearch(fuzzycuser).get(i).getCusers());
						}
						JOptionPane.showMessageDialog(null, "查找成功","提示-查找用户账号",JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		button.setBounds(79, 61, 117, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(516, 25, 117, 29);
		contentPane.add(button_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 94, 685, 12);
		contentPane.add(separator);
		
		
		comboBox_pet.setModel(new DefaultComboBoxModel(new String[] {"选择宠物信息"}));
		comboBox_pet.setBounds(40, 135, 130, 27);
		contentPane.add(comboBox_pet);
		
		
		comboBox_order.setModel(new DefaultComboBoxModel(new String[] {"选择订单信息"}));
		comboBox_order.setBounds(265, 135, 130, 27);
		contentPane.add(comboBox_order);
		
		
		comboBox_res.setModel(new DefaultComboBoxModel(new String[] {"选择预约信息"}));
		comboBox_res.setBounds(503, 135, 130, 27);
		
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(208, 97, 17, 438);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(440, 97, 6, 438);
		contentPane.add(separator_2);
		
		JLabel label_1 = new JLabel("宠物编号：");
		label_1.setBounds(6, 198, 71, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("宠物昵称：");
		label_2.setBounds(6, 238, 65, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("宠物品种：");
		label_3.setBounds(6, 277, 71, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("宠物性别：");
		label_4.setBounds(6, 317, 71, 16);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("订单编号：");
		label_5.setBounds(223, 198, 71, 16);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("商品名称：");
		label_6.setBounds(223, 238, 71, 16);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("购买数量：");
		label_7.setBounds(223, 277, 71, 16);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("订单总价：");
		label_8.setBounds(223, 317, 71, 16);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("下单日期：");
		label_9.setBounds(223, 358, 71, 16);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("订单状态：");
		label_10.setBounds(223, 400, 71, 16);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("预约编号：");
		label_11.setBounds(458, 198, 71, 16);
		contentPane.add(label_11);
		
		JLabel label_12 = new JLabel("预约宠物：");
		label_12.setBounds(458, 238, 71, 16);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("预约服务：");
		label_13.setBounds(458, 277, 71, 16);
		contentPane.add(label_13);
		
		JLabel label_14 = new JLabel("预约状态：");
		label_14.setBounds(458, 317, 71, 16);
		contentPane.add(label_14);
		
		JLabel label_15 = new JLabel("服务进度：");
		label_15.setBounds(458, 358, 71, 16);
		contentPane.add(label_15);
		
		JLabel label_16 = new JLabel("预约时间：");
		label_16.setBounds(458, 400, 71, 16);
		contentPane.add(label_16);
		
		JLabel label_17 = new JLabel("完成时间：");
		label_17.setBounds(458, 439, 71, 16);
		contentPane.add(label_17);
		contentPane.add(comboBox_res);
		
	}

}
