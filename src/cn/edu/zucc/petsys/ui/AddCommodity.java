package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.util.regex.Pattern;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCommodity extends JFrame {

	private JPanel contentPane;
	private JTextField textField_iname;
	private JTextField textField_iprice;
	private JTextField textField_iquantity;
	private JTextField textField_ibrand;
	private static String tname;
	private static int tid;
	private static Double iprice;
	private static int iquantity;
	/**
	 * Launch the application.
	 */
	
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
					AddCommodity frame = new AddCommodity();
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
	public AddCommodity() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"选择加入的类别"}));
		comboBox.setBounds(0, 0, 147, 27);
		try {
			for(int i=0;i<PetsysUtil.typeManager.loadAll().size();i++)
			{
				comboBox.addItem(PetsysUtil.typeManager.loadAll().get(i).getTname());
				
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
					tname = comboBox.getSelectedItem().toString();
					if(!tname.equals("选择加入的类别"))
					{
						try {
							tid = PetsysUtil.typeManager.loadOne(tname).getTid();
						} catch (BaseException e2) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
			}
		});
		contentPane.add(comboBox);
		
		JLabel label = new JLabel("商品名：");
		label.setBounds(168, 50, 61, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("商标：");
		label_1.setBounds(181, 90, 39, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("库存量：");
		label_2.setBounds(168, 162, 61, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("单价：");
		label_3.setBounds(181, 127, 39, 16);
		contentPane.add(label_3);
		
		textField_iname = new JTextField();
		textField_iname.setBounds(241, 45, 130, 26);
		contentPane.add(textField_iname);
		textField_iname.setColumns(10);
		
		textField_iprice = new JTextField();
		textField_iprice.setBounds(241, 122, 130, 26);
		contentPane.add(textField_iprice);
		textField_iprice.setColumns(10);
		
		textField_iquantity = new JTextField();
		textField_iquantity.setBounds(241, 157, 130, 26);
		contentPane.add(textField_iquantity);
		textField_iquantity.setColumns(10);
		
		textField_ibrand = new JTextField();
		textField_ibrand.setBounds(241, 85, 130, 26);
		contentPane.add(textField_ibrand);
		textField_ibrand.setColumns(10);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iname = textField_iname.getText();
//				System.out.println(iname);
				String ibrand = textField_ibrand.getText();
				//判断库存量和单价未输入的情况
				if(isNumeric(textField_iquantity.getText())==false)
				{
					JOptionPane.showMessageDialog(null, "库存量只能为数字","提示-添加商品",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if(isDouble(textField_iprice.getText())==false)
				{
					JOptionPane.showMessageDialog(null, "价格只能为浮点数或者整型","提示-添加商品",JOptionPane.ERROR_MESSAGE);
					return;
				}
				if("".equals(textField_iprice.getText()))
					iprice = 0.0;
				else
					iprice = Double.parseDouble(textField_iprice.getText());
				if("".equals(textField_iquantity.getText()))
					iquantity = 0;
				else
					iquantity = Integer.parseInt(textField_iquantity.getText());
				try {
					if(!comboBox.getSelectedItem().toString().equals("选择加入的类别"))
					{
					PetsysUtil.comManager.addCommodity(iname, tid, ibrand, iprice, iquantity);
					JOptionPane.showMessageDialog(null, "商品添加成功！","提示-添加商品",JOptionPane.INFORMATION_MESSAGE);
					return;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "请先选择添加类别","提示-添加商品",JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (BaseException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				} 
			}
		});
		button.setBounds(178, 190, 117, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(327, 190, 117, 29);
		contentPane.add(button_1);
	}
}
