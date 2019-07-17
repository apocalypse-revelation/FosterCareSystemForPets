package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanInfo;
import cn.edu.zucc.petsys.bean.BeanPet;
import cn.edu.zucc.petsys.bean.BeanType;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class UpdateCommodity extends JFrame {

	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_quantity;
	private JTextField textField_price;
	private JTextField textField_inamesearch;
	private JTextField textField_ibrandsearch;
	private static int iid;
	private static String text;
	private JTextField textField_brand;
	private static String tname;
	private static int tid;
	private JTextField textField_tname;
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
			//判断浮点数
			private boolean isDouble(String str) {
				if (null == str || "".equals(str)) {
					return false;
				}
				Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
				return pattern.matcher(str).matches();
			}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCommodity frame = new UpdateCommodity();
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
	public UpdateCommodity() {
		setTitle("更新商品信息");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel_tname = new JLabel();
		JLabel label = new JLabel("商品名称：");
		label.setBounds(212, 28, 65, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("商品库存：");
		label_1.setBounds(212, 73, 76, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("商品单价：");
		label_2.setBounds(212, 122, 76, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("转移类别到：");
		label_3.setBounds(199, 247, 78, 16);
		contentPane.add(label_3);
		
		JComboBox comboBox_type = new JComboBox();
		
		comboBox_type.setModel(new DefaultComboBoxModel(new String[] {"默认不转移"}));
		comboBox_type.setBounds(289, 243, 127, 27);
		try {
			for(int i=0;i<PetsysUtil.typeManager.loadAll().size();i++)
			{
				comboBox_type.addItem(PetsysUtil.typeManager.loadAll().get(i).getTname());
				
			}
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			return;
			// TODO: handle exception
		}
		comboBox_type.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				tname = comboBox_type.getSelectedItem().toString();
				try {
					//排除 字段：“默认不转移”；
					if(!tname.equals("默认不转移"))
						tid = PetsysUtil.typeManager.loadOne(tname).getTid();
					
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					// TODO: handle exception
				}
			}
		});
		
		JLabel label_4 = new JLabel("商品品牌：");
		label_4.setBounds(212, 171, 65, 16);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("商品类别：");
		label_5.setBounds(212, 212, 76, 16);
		contentPane.add(label_5);
		contentPane.add(comboBox_type);
		
		textField_name = new JTextField();
		textField_name.setBounds(286, 23, 130, 26);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_quantity = new JTextField();
		textField_quantity.setBounds(286, 68, 130, 26);
		contentPane.add(textField_quantity);
		textField_quantity.setColumns(10);
		
		textField_price = new JTextField();
		textField_price.setBounds(286, 117, 130, 26);
		contentPane.add(textField_price);
		textField_price.setColumns(10);
		textField_inamesearch = new JTextField();
		textField_inamesearch.setBounds(84, 35, 130, 26);
		contentPane.add(textField_inamesearch);
		textField_inamesearch.setColumns(10);
		
		textField_ibrandsearch = new JTextField();
		textField_ibrandsearch.setBounds(84, 63, 130, 26);
		contentPane.add(textField_ibrandsearch);
		textField_ibrandsearch.setColumns(10);
		
		JLabel label_inamesearch = new JLabel("商品搜索：");
		label_inamesearch.setBounds(6, 40, 84, 16);
		contentPane.add(label_inamesearch);
		
		JCheckBox checkBox = new JCheckBox("模糊查询");
		checkBox.setBounds(47, 89, 84, 23);
		contentPane.add(checkBox);
		
		JLabel label_ibrandsearch = new JLabel("品牌搜索：");
		label_ibrandsearch.setBounds(6, 68, 84, 16);
		contentPane.add(label_ibrandsearch);
		
		JComboBox comboBox_list = new JComboBox();
		
		comboBox_list.setModel(new DefaultComboBoxModel(new String[] {"查看"}));
		comboBox_list.setBounds(6, 118, 104, 27);
		
		
		
		contentPane.add(comboBox_list);
		
		JButton button_search = new JButton("搜索");
		button_search.addActionListener(new ActionListener() {
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
								 iid = Integer.parseInt(m.group());
								 BeanInfo bi = PetsysUtil.comManager.getCommodity(iid);
								 textField_name.setText(bi.getIname());
								 textField_price.setText(Double.toString(bi.getIprice()));
								 textField_quantity.setText(Integer.toString(bi.getIquantity()));
								 textField_brand.setText(bi.getIbrand());
								 //textField_tname.setText(PetsysUtil.typeManager.loadType(bi.getTid()).getTname());
								 lblNewLabel_tname.setText(PetsysUtil.typeManager.loadType(bi.getTid()).getTname());
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
						 textField_brand.setText("");
						 textField_name.setText("");
						 textField_price.setText("");
						 textField_quantity.setText("");
						 textField_tname.setText("");
					}
				}
			}
		});
		button_search.setBounds(143, 88, 50, 29);
		contentPane.add(button_search);
		
		
		
		
		
		
		
		textField_tname = new JTextField();
		textField_tname.setVisible(false);
		textField_tname.setBounds(286, 282, 130, 26);
		contentPane.add(textField_tname);
		textField_tname.setColumns(10);
		
		textField_brand = new JTextField();
		textField_brand.setBounds(286, 166, 130, 26);
		contentPane.add(textField_brand);
		textField_brand.setColumns(10);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboBox_list.getSelectedItem().toString().equals("查看"))
				{
					if(comboBox_type.getSelectedItem().toString().equals("默认不转移"))
						try {
							tid = PetsysUtil.comManager.getCommodity(iid).getTid();
						} catch (BaseException e2) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, "更新成功","提示-更新商品",JOptionPane.INFORMATION_MESSAGE);
						}
					if(isDouble(textField_price.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "价格为浮点数或者不能为空","提示-更新商品",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else if(isNumeric(textField_quantity.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "库存量只能为数字（不能浮点数）或者不能为空","提示-更新商品",JOptionPane.ERROR_MESSAGE);
						return;
					}
				String newname = textField_name.getText();
				String newBrand = textField_brand.getText();
				int newquantity = Integer.parseInt(textField_quantity.getText());
				Double newprice = Double.parseDouble(textField_price.getText());
				try {
					PetsysUtil.comManager.updateOneCommodity(iid, newname, tid, newBrand, newquantity, newprice);
					JOptionPane.showMessageDialog(null, "更新成功","提示-更新商品",JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException e2) 
					{
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					// TODO: handle exception
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"请先选择商品再进行修改","提示-更新商品",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		button.setBounds(46, 347, 117, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(175, 347, 117, 29);
		contentPane.add(button_1);
		
		
		lblNewLabel_tname.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_tname.setBounds(289, 212, 127, 16);
		contentPane.add(lblNewLabel_tname);
	}
}
