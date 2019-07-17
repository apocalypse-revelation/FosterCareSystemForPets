package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanCus;
import cn.edu.zucc.petsys.bean.BeanInfo;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class CustomerPurchaseInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textField_inamesearch;
	private JTextField textField_ibrandsearch;
	private static int iid1;
	private static String text;
	private static String tname;
	private static String iname;
	private static int tid;
	private static int iid2;
	private static int count;
	private JTextField textField_amount;
	//判断字符串是否都为数字
	public static boolean isNumeric(String str)
		{
		   for (int i = str.length();--i>=0;)
		   {  
		        if (!Character.isDigit(str.charAt(i)))
		        {
		            return false;
		        }
		   }
		    return true;
		 }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerPurchaseInterface frame = new CustomerPurchaseInterface();
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
	public CustomerPurchaseInterface() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_6 = new JLabel("*请先选择查找方式：");
		label_6.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_6.setForeground(Color.MAGENTA);
		label_6.setBounds(280, 257, 139, 26);
		contentPane.add(label_6);
		JLabel label = new JLabel("商品名称：");
		label.setBounds(237, 28, 65, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("商品库存：");
		label_1.setBounds(237, 73, 76, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("商品单价：");
		label_2.setBounds(237, 122, 76, 16);
		contentPane.add(label_2);
		
		
		JLabel label_4 = new JLabel("商品品牌：");
		label_4.setBounds(237, 171, 65, 16);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("商品类别：");
		label_5.setBounds(237, 212, 76, 16);
		contentPane.add(label_5);
		
		JLabel label_name = new JLabel("");
		label_name.setBounds(309, 28, 61, 16);
		contentPane.add(label_name);
		
		JLabel label_quantity = new JLabel("");
		label_quantity.setBounds(309, 73, 61, 16);
		contentPane.add(label_quantity);
		
		JLabel label_price = new JLabel("");
		label_price.setBounds(309, 122, 61, 16);
		contentPane.add(label_price);
		
		JLabel label_brand = new JLabel("");
		label_brand.setBounds(309, 171, 61, 16);
		contentPane.add(label_brand);
		
		JLabel label_type = new JLabel("");
		label_type.setBounds(309, 212, 61, 16);
		contentPane.add(label_type);
		textField_inamesearch = new JTextField();
		textField_inamesearch.setBounds(69, 12, 130, 26);
		contentPane.add(textField_inamesearch);
		textField_inamesearch.setColumns(10);
		
		textField_ibrandsearch = new JTextField();
		textField_ibrandsearch.setBounds(69, 43, 130, 26);
		contentPane.add(textField_ibrandsearch);
		textField_ibrandsearch.setColumns(10);
		
		JLabel label_3 = new JLabel("*温馨提示:直接点击搜索将列出所有商品");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_3.setBounds(0, 0, 240, 16);
		contentPane.add(label_3);
		
		JLabel label_inamesearch = new JLabel("商品搜索：");
		label_inamesearch.setBounds(0, 17, 65, 16);
		contentPane.add(label_inamesearch);
		
		JCheckBox checkBox = new JCheckBox("模糊查询");
		checkBox.setBounds(0, 73, 84, 23);
		contentPane.add(checkBox);
		
		JLabel label_ibrandsearch = new JLabel("品牌搜索：");
		label_ibrandsearch.setBounds(0, 48, 65, 16);
		contentPane.add(label_ibrandsearch);
		
		JComboBox comboBox_list = new JComboBox();
		
		comboBox_list.setModel(new DefaultComboBoxModel(new String[] {"查看"}));
		comboBox_list.setBounds(0, 98, 139, 27);
		
		
		
		contentPane.add(comboBox_list);
		
		JButton button_search_in = new JButton("搜索");
		button_search_in.setVisible(false);
		button_search_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//删除重复添加的item，但不删除第0行的“查看”
				for(int i=1;i<comboBox_list.getItemCount();)
				{
				comboBox_list.removeItemAt(i);
				}
				String iname = textField_inamesearch.getText();
				String ibrand = textField_ibrandsearch.getText();
				//无论是否选中模糊查询，只要是两个空白框的情况下，点击搜索都列出全部商品
					if("".equals(iname)&&"".equals(ibrand))
					{
						try {
							for(int i=0;i<PetsysUtil.comManager.loadAll().size();i++)
							{
								comboBox_list.addItem("编号:["+PetsysUtil.comManager.loadAll().get(i).getIid()+"],"+"商品：["+PetsysUtil.comManager.loadAll().get(i).getIname()+"],品牌：["+PetsysUtil.comManager.loadAll().get(i).getIbrand()+"]");
							}
							} catch (BaseException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
						// TODO: handle exception
							}
					}
					//选中模糊查询情况下，调用模糊查询方法
				if(checkBox.isSelected())
				{
						//商品非空，品牌空的模糊查询
					if("".equals(ibrand)&&!"".equals(iname))
					{
						try {
							for(int i=0;i<PetsysUtil.comManager.fuzzyNameSearch(iname).size();i++)
							{
								comboBox_list.addItem("编号:["+PetsysUtil.comManager.fuzzyNameSearch(iname).get(i).getIid()+"],"+"商品：["+PetsysUtil.comManager.fuzzyNameSearch(iname).get(i).getIname()+"],品牌：["+PetsysUtil.comManager.fuzzyNameSearch(iname).get(i).getIbrand()+"]");
							}
						} catch (BaseException e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
							// TODO: handle exception
						}
					}
					//品牌空，商品非空
					else if("".equals(iname)&&!"".equals(ibrand))
					{
						try {
							for(int i=0;i<PetsysUtil.comManager.fuzzyBrandSearch(iname).size();i++)
							{
								comboBox_list.addItem("编号:["+PetsysUtil.comManager.fuzzyBrandSearch(iname).get(i).getIid()+"],"+"商品：["+PetsysUtil.comManager.fuzzyBrandSearch(iname).get(i).getIname()+"],品牌：["+PetsysUtil.comManager.fuzzyBrandSearch(iname).get(i).getIbrand()+"]");
							}
						} catch (BaseException e3) {
							JOptionPane.showMessageDialog(null, e3.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
							// TODO: handle exception
						}
					}
					//都非空的模糊查询
					else if(!"".equals(ibrand)&&!"".equals(iname))
					{
						try {
							for(int i=0;i<PetsysUtil.comManager.fuzzyNBSearch(iname,ibrand).size();i++)
							{
								comboBox_list.addItem("编号:["+PetsysUtil.comManager.fuzzyNBSearch(iname,ibrand).get(i).getIid()+"],"+"商品：["+PetsysUtil.comManager.fuzzyNBSearch(iname,ibrand).get(i).getIname()+"],品牌：["+PetsysUtil.comManager.fuzzyNBSearch(iname,ibrand).get(i).getIbrand()+"]");
							}
						} catch (BaseException e3) {
							JOptionPane.showMessageDialog(null, e3.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
							// TODO: handle exception
						}
					}
				}//选中模糊查询
				//精确查询
				else 
				{
					//商品非空，品牌空的精确查询
					if("".equals(ibrand)&&!"".equals(iname))
					{
						try {
							for(int i=0;i<PetsysUtil.comManager.accuracyNameSearch(iname).size();i++)
							{
								comboBox_list.addItem("编号:["+PetsysUtil.comManager.accuracyNameSearch(iname).get(i).getIid()+"],"+"商品：["+PetsysUtil.comManager.accuracyNameSearch(iname).get(i).getIname()+"],品牌：["+PetsysUtil.comManager.accuracyNameSearch(iname).get(i).getIbrand()+"]");
							}
						} catch (BaseException e3) {
							JOptionPane.showMessageDialog(null, e3.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
							// TODO: handle exception
						}
					}
					else if("".equals(iname)&&!"".equals(ibrand))
					{
						try {
							for(int i=0;i<PetsysUtil.comManager.accuracyBrandSearch(ibrand).size();i++)
							{
								comboBox_list.addItem("编号:["+PetsysUtil.comManager.accuracyBrandSearch(ibrand).get(i).getIid()+"],"+"商品：["+PetsysUtil.comManager.accuracyBrandSearch(ibrand).get(i).getIname()+"],品牌：["+PetsysUtil.comManager.accuracyBrandSearch(ibrand).get(i).getIbrand()+"]");
							}
						} catch (BaseException e3) {
							JOptionPane.showMessageDialog(null, e3.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
							// TODO: handle exception
						}
					}
					//都非空的模糊查询
					else if(!"".equals(ibrand)&&!"".equals(iname))
					{
						try {
							for(int i=0;i<PetsysUtil.comManager.accuracyNBSearch(iname,ibrand).size();i++)
							{
								comboBox_list.addItem("编号:["+PetsysUtil.comManager.accuracyNBSearch(iname,ibrand).get(i).getIid()+"],"+"商品：["+PetsysUtil.comManager.accuracyNBSearch(iname,ibrand).get(i).getIname()+"],品牌：["+PetsysUtil.comManager.accuracyNBSearch(iname,ibrand).get(i).getIbrand()+"]");
							}
						} catch (BaseException e3) {
							JOptionPane.showMessageDialog(null, e3.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
							// TODO: handle exception
						}
					}
				}
			}
		});
		comboBox_list.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					iid2 = 0;
					text = comboBox_list.getSelectedItem().toString();
					if(!text.equals("查看")||"".equals(text))
					{
					String regex = "\\d*";
					//String regex = "[^0-9]";
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(text);
					//System.out.println(text);
					
					while(m.find())
					 {
						 if(!"".equals(m.group()))
							 try {
								 iid1 = Integer.parseInt(m.group());
								 BeanInfo bi = PetsysUtil.comManager.getCommodity(iid1);
								 label_name.setText(bi.getIname());
								 label_price.setText(Double.toString(bi.getIprice()));
								 label_quantity.setText(Integer.toString(bi.getIquantity()));
								 label_brand.setText(bi.getIbrand());
								 label_type.setText(PetsysUtil.typeManager.loadType(bi.getTid()).getTname());
								 break;//只取第一个数字就跳出————防止后面的字段也为数字却传入了iid导致混乱
							} catch (BaseException e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
								return;
								// TODO: handle exception
							}
					 }
					}
					else if(text.equals("查看")||"".equals(text))
					{
						 label_brand.setText("");
						 label_name.setText("");
						 label_price.setText("");
						 label_quantity.setText("");
						 label_type.setText("");
					}
				}
			}
		});
		button_search_in.setBounds(96, 73, 93, 29);
		contentPane.add(button_search_in);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(232, 247, 218, 12);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(0, 159, 233, 16);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(228, 0, 12, 430);
		contentPane.add(separator_2);
		
		
		//_______________________索引方式二：选项索引————————————————————————————————————————
		//
		//
		//
		//
		//
		//
		//
		JLabel label_choosetype = new JLabel("选择类别：");
		label_choosetype.setBounds(0, 194, 65, 16);
		contentPane.add(label_choosetype);
		
		JLabel label_8 = new JLabel("选择商品：");
		label_8.setBounds(0, 234, 65, 16);
		contentPane.add(label_8);
				
				JLabel label_7 = new JLabel("选择品牌：");
				label_7.setBounds(0, 274, 65, 16);
				contentPane.add(label_7);
				
				
				////////////////////combox组件/////////////////
				//
				//
				//
				//
				//
				//
		//Name商品combox
				JComboBox comboBox_choiname = new JComboBox();
				comboBox_choiname.setModel(new DefaultComboBoxModel(new String[] {"查看"}));
				comboBox_choiname.setBounds(77, 230, 122, 27);
		//Brand品牌combobox
				JComboBox comboBox_chobrand = new JComboBox();
				comboBox_chobrand.setModel(new DefaultComboBoxModel(new String[] {"查看"}));
				comboBox_chobrand.setBounds(77, 270, 122, 27);
				contentPane.add(comboBox_chobrand);
		//Type类别combox
		JComboBox comboBox_chotype = new JComboBox();
		comboBox_chotype.setModel(new DefaultComboBoxModel(new String[] {"查看"}));
		
		
		//Type——类别combox的item添加
		try {
			for(int i=0;i<PetsysUtil.typeManager.loadAll().size();i++)
			{
				comboBox_chotype.addItem(PetsysUtil.typeManager.loadAll().get(i).getTname());
				
			}
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		
		
		
		
		//Type类别的item监听 &&&&&&& Name的item添加
		comboBox_chotype.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if(e.getStateChange() == ItemEvent.SELECTED)
				 {
					 //商品item个数的校正
					 for(int i=1;i<comboBox_choiname.getItemCount();)
						{
						comboBox_choiname.removeItemAt(i);
						}//校正完毕
					 //选中type 得到type类型名称tname
					 tname = comboBox_chotype.getSelectedItem().toString();
					 if(!comboBox_chotype.getSelectedItem().toString().equals("查看"))
					 {
					 try {
						tid = PetsysUtil.typeManager.loadOne(tname).getTid();
						//多线程刷新 商品名
						new Thread(new Runnable() {
				            @Override
				            public void run() {
				            	 try {
				            		 try {
				            				for(int i=0;i<PetsysUtil.comManager.loadCommodityThroughType(tid).size();i++)
				            				{
				            					//商品名combox的item添加
				            					comboBox_choiname.addItem(PetsysUtil.comManager.loadCommodityThroughType(tid).get(i).getIname());
				            					
				            				}
				            			} catch (BaseException e) {
				            				JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				            				return;
				            				// TODO: handle exception
				            			}
				                         Thread.sleep(1000);    
				                 } catch (InterruptedException e) {
				                     e.printStackTrace();
				                 }	             
				                 
				            }
				        }).start();
					} catch (BaseException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);

						// TODO: handle exception
					}
				 }
				 }
			}
		});
		//商品的item触发
				comboBox_choiname.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						 if(e.getStateChange() == ItemEvent.SELECTED)
						 {
							 //品牌item个数的校正
							 for(int i=1;i<comboBox_chobrand.getItemCount();)
								{
								comboBox_chobrand.removeItemAt(i);
								}
							 //此时得到tid和iname 以此得到ibrand的List
							 iname = comboBox_choiname.getSelectedItem().toString();
							 
							 if(!comboBox_choiname.getSelectedItem().toString().equals("查看"))
							 {
									 new Thread(new Runnable() {
								            @Override
								            public void run() {
								            	 try {
								            		 try {
								            				for(int i=0;i<PetsysUtil.comManager.loadCommodityThroughTypeName(tid, iname).size();i++)
								            				{
								            					//品牌combox的item添加
								            					comboBox_chobrand.addItem(PetsysUtil.comManager.loadCommodityThroughTypeName(tid, iname).get(i).getIbrand());
								            				}
								            			} catch (BaseException e) {
								            				JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
								            				return;
								            				// TODO: handle exception
								            			}
								                         Thread.sleep(1000);    
								                     
								                 } catch (InterruptedException e) {
								                     e.printStackTrace();
								                 }	             
								            }
								        }).start();
								
							 }
						 }
					}
				});
		comboBox_chotype.setBounds(77, 187, 122, 27);
		contentPane.add(comboBox_chotype);
		
		
		
		contentPane.add(comboBox_choiname);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton button_search2 = new JButton("查询");
		button_search2.setVisible(false);
		button_search2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String typename = comboBox_chotype.getSelectedItem().toString();
				String inametext = comboBox_choiname.getSelectedItem().toString();
				String brandtext = comboBox_chobrand.getSelectedItem().toString();
				
				if(typename.equals("查看"))
				{
					JOptionPane.showMessageDialog(null, "类别未选择","错误",JOptionPane.ERROR_MESSAGE);
					return;//防止只选择了一个的情况下还进行了数据库的查询
				}
				if(inametext.equals("查看"))
				{
					JOptionPane.showMessageDialog(null, "商品名未选择","错误",JOptionPane.ERROR_MESSAGE);
					return;//同上
				}
				if(brandtext.equals("查看"))
				{
					JOptionPane.showMessageDialog(null, "品牌未选择","错误",JOptionPane.ERROR_MESSAGE);
					return;//同上
				}
				if(!typename.equals("查看")&&!inametext.equals("查看")&&!brandtext.equals("查看"))
				{
					try {
						BeanInfo bi = PetsysUtil.comManager.loadOneCommodity(tid, inametext, brandtext);
						label_brand.setText(bi.getIbrand());
						label_name.setText(bi.getIname());
						label_price.setText(Double.toString(bi.getIprice()));
						label_quantity.setText(Integer.toString(bi.getIquantity()));
						label_type.setText(PetsysUtil.typeManager.loadType(bi.getTid()).getTname());
						iid1 = 0;
						iid2 = PetsysUtil.comManager.loadOneCommodity(tid, inametext, brandtext).getIid();
						System.out.println(Double.parseDouble(label_price.getText().toString()));
					} catch (BaseException e2) {
						// TODO: handle exception
        				JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button_search2.setBounds(6, 303, 117, 29);
		contentPane.add(button_search2);
		button.setBounds(327, 478, 117, 29);
		contentPane.add(button);
		
		JButton button_inputsearch = new JButton("输入索引");
		button_inputsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label_brand.setText("");
				label_name.setText("");
				label_price.setText("");
				label_quantity.setText("");
				label_type.setText("");
				iid2=0;
				button_search_in.setVisible(true);
				button_search2.setVisible(false);
			}
		});
		
		JButton button_choosesearch = new JButton("选项索引");
		button_choosesearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label_brand.setText("");
				label_name.setText("");
				label_price.setText("");
				label_quantity.setText("");
				label_type.setText("");
				iid1=0;
				button_search_in.setVisible(false);
				button_search2.setVisible(true);
			}
		});
		button_choosesearch.setBounds(297, 350, 91, 47);
		contentPane.add(button_choosesearch);
		button_inputsearch.setBounds(296, 294, 92, 47);
		contentPane.add(button_inputsearch);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(231, 422, 220, 12);
		contentPane.add(separator_3);
		
		JButton button_1 = new JButton("购买");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inametext = label_name.getText();
				String brandtext = label_brand.getText();
				String amount = textField_amount.getText();
				if(BeanCus.currentLoginUser==null)
				{
					JOptionPane.showMessageDialog(null, "居然没登录就能购买","提示—购买",JOptionPane.ERROR_MESSAGE);
					CustomerLogin cl = new CustomerLogin();
					cl.setVisible(true);
					return;
				}
				if(label_name.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "请先查询到具体商品再购买","提示—购买",JOptionPane.ERROR_MESSAGE);
					return;
				}
				//判断字符串是否都是数字
				if(isNumeric(amount)==true)
				{
					if(Integer.parseInt(amount)==0)
					{
						JOptionPane.showMessageDialog(null, "不买就滚","闹着玩呢？",JOptionPane.ERROR_MESSAGE);
						dispose();
						return;
					}
					else
						count = Integer.parseInt(amount);
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "请输入阿拉伯数字","提示—购买数量",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					if(iid2==0)//输入索引的情况下
					{
						PetsysUtil.cusManager.cusPurchase(iid1,count);
						PetsysUtil.ordManager.addOrd(iid1, BeanCus.currentLoginUser.getCid(), count, count*Double.parseDouble(label_price.getText().toString()));
						JOptionPane.showMessageDialog(null, "购买成功！","提示—购买数量",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					else if(iid1==0)//选项索引的情况下
					{
						PetsysUtil.cusManager.cusPurchase(iid2,count);
						PetsysUtil.ordManager.addOrd(iid2, BeanCus.currentLoginUser.getCid(), count, count*Double.parseDouble(label_price.getText().toString()));
						JOptionPane.showMessageDialog(null, "购买成功！","提示—购买数量",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (BaseException e2) {
					// TODO: handle exception
    				JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button_1.setBounds(149, 445, 84, 62);
		contentPane.add(button_1);
		
		textField_amount = new JTextField();
		textField_amount.setHorizontalAlignment(SwingConstants.CENTER);
		textField_amount.setText("1");
		textField_amount.setBounds(6, 449, 65, 26);
		contentPane.add(textField_amount);
		textField_amount.setColumns(10);
		
		JLabel label_9 = new JLabel("购买数量：");
		label_9.setBounds(10, 414, 87, 16);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("——输入索引——");
		label_10.setForeground(Color.BLUE);
		label_10.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_10.setBounds(57, 137, 117, 16);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("——选项索引——");
		label_11.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_11.setForeground(Color.BLUE);
		label_11.setBounds(62, 344, 112, 16);
		contentPane.add(label_11);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.BLACK);
		separator_4.setFont(new Font("Dialog", Font.BOLD, 12));
		separator_4.setBounds(0, 385, 233, 12);
		contentPane.add(separator_4);
		
		
	}
}
        