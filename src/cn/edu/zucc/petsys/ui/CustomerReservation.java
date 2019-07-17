package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanRes;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class CustomerReservation extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton button_confirm;
	private JLabel label_1;
	private JComboBox comboBox;
	private static String servicename;
	private static String retime;
	private JLabel lblxxxxxxxx;
	private JLabel label_2;

	//判断字符串是否为日期格式
	public static boolean isValidDate(String str) {
		        boolean convertSuccess=true;
		        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		         try {
// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
		            format.setLenient(false);
		            format.parse(str);
		         } catch (ParseException e) {
		           // e.printStackTrace();
		 // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
		            convertSuccess=false;
		        } 
		        return convertSuccess;
		 }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerReservation frame = new CustomerReservation();
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
	public CustomerReservation() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("预约时间：");
		label.setBounds(76, 62, 73, 16);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(161, 57, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button_back = new JButton("返回");
		button_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_back.setBounds(327, 243, 117, 29);
		contentPane.add(button_back);
		
		label_1 = new JLabel("服务选择：");
		label_1.setBounds(76, 107, 73, 16);
		contentPane.add(label_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"选择"}));
		
		comboBox.setBounds(194, 103, 83, 27);
		
		try {
			for(int i=0;i<PetsysUtil.serManager.loadAllService().size();i++)
			{
				if(PetsysUtil.serManager.loadAllService().get(i).getSerstatus().equals("可预约"))
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
					servicename = comboBox.getSelectedItem().toString();
				}
			}
		});
		contentPane.add(comboBox);
		
		button_confirm = new JButton("确定");
		button_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retime = textField.getText();//用户输入的预约时间
				try {
					if(!comboBox.getSelectedItem().toString().equals("选择"))
					{
						if(retime.equals(""))
						{
							JOptionPane.showMessageDialog(null, "预约时间不能为空","错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(!isValidDate(retime))
						{
							JOptionPane.showMessageDialog(null, "预约时间格式错误！","错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(PetsChoice.pid==0)
						{
							JOptionPane.showMessageDialog(null, "得不到该宠物信息","错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
							
						//判断同一时间的同一服务类型，此情况不能重复预约
						for(int i=0;i<PetsysUtil.resManager.loadAllthroughPid(PetsChoice.pid).size();i++)
						{
							if(PetsysUtil.resManager.loadAllthroughPid(PetsChoice.pid).get(i).getRsername().equals(servicename)&&PetsysUtil.resManager.loadAllthroughPid(PetsChoice.pid).get(i).getRtime().toString().equals(retime))
							{
								JOptionPane.showMessageDialog(null, "该宠物的预约已经存在，请换个服务或者换个时间","错误",JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						
							int rid = PetsysUtil.serManager.cusReserve(PetsChoice.pid, retime, servicename);
							JOptionPane.showMessageDialog(null, "已申请，请等待商家确认！","提示—预约",JOptionPane.INFORMATION_MESSAGE);
							dispose();
							button_confirm.setVisible(false);
						if(PetsysUtil.resManager.loadOneRes(rid).getRapply().equals("预约成功"))
						{
							JOptionPane.showMessageDialog(null, "商家已经确认预约了，不能再次预约","错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
						else if(PetsysUtil.resManager.loadOneRes(rid).getRstatus().equals("完成"))
						{
							JOptionPane.showMessageDialog(null, "该预约已经被确认完成了，不能再次预约","错误",JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "预约服务类型未选！","错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (BaseException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		button_confirm.setBounds(22, 243, 117, 29);
		contentPane.add(button_confirm);
		
		lblxxxxxxxx = new JLabel("格式：xxxx-xx-xx");
		lblxxxxxxxx.setForeground(Color.RED);
		lblxxxxxxxx.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblxxxxxxxx.setBounds(296, 62, 136, 16);
		contentPane.add(lblxxxxxxxx);
		
		label_2 = new JLabel("*预约不可线上取消，若要强行取消请致电服务中心；");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label_2.setBounds(22, 22, 305, 16);
		contentPane.add(label_2);
		
		
	}
}
