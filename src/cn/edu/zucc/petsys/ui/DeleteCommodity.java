package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanInfo;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ItemEvent;

public class DeleteCommodity extends JFrame {

	private JPanel contentPane;
	private JTextField textField_inamesearch;
	private JTextField textField_ibrandsearch;
	private static int iid;
	private static String text;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteCommodity frame = new DeleteCommodity();
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
	public DeleteCommodity() {
		setTitle("商品删除界面");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		comboBox_list.setBounds(226, 36, 104, 27);
		
		
		
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
					while(m.find())
					 {
						 if(!"".equals(m.group()))
							 try {
								 iid = Integer.parseInt(m.group());
								 BeanInfo bi = PetsysUtil.comManager.getCommodity(iid);
								 break;//只取第一个数字就跳出————防止后面的字段也为数字却传入了iid导致混乱
							} catch (BaseException e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
								return;
								// TODO: handle exception
							}
					 }
					}
				}
			}
		});
		button_search.setBounds(143, 88, 50, 29);
		contentPane.add(button_search);
		
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_list.getSelectedItem().toString().equals("查看"))
				{
					JOptionPane.showMessageDialog(null, "请选择要删除的商品！","提示-选择删除的商品" , JOptionPane.WARNING_MESSAGE);
					return;
				}
				else
					try {
						PetsysUtil.comManager.deleteOneCommodity(iid);
						JOptionPane.showMessageDialog(null, "删除成功","提示-选择删除的商品" , JOptionPane.WARNING_MESSAGE);
						button_2.setVisible(false);
					} catch (BaseException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);

					}
			}
		});
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(327, 230, 117, 29);
		contentPane.add(button_1);
		button_2.setBounds(6, 230, 59, 29);
		contentPane.add(button_2);
	}
}
