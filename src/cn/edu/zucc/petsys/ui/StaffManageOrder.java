package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanOrd;
import cn.edu.zucc.petsys.tool.BaseException;
import cn.edu.zucc.petsys.tool.PetsysUtil;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class StaffManageOrder extends JFrame {

	private JPanel contentPane;
	private static String text;
	private static int oid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffManageOrder frame = new StaffManageOrder();
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
	public StaffManageOrder() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(327, 381, 117, 29);
		contentPane.add(button);
		
		JLabel label = new JLabel("订单号：");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(193, 22, 61, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("购买物：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(193, 63, 61, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("下单人：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(193, 105, 61, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("购买数：");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(193, 140, 61, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("总价：");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(210, 172, 39, 16);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("订单状态：");
		label_5.setBounds(188, 201, 66, 16);
		contentPane.add(label_5);
		
		JLabel label_oid = new JLabel();
		label_oid.setHorizontalAlignment(SwingConstants.CENTER);
		label_oid.setBounds(292, 22, 61, 16);
		contentPane.add(label_oid);
		
		JLabel label_iname = new JLabel();
		label_iname.setHorizontalAlignment(SwingConstants.CENTER);
		label_iname.setBounds(292, 63, 61, 16);
		contentPane.add(label_iname);
		
		JLabel label_cname = new JLabel();
		label_cname.setHorizontalAlignment(SwingConstants.CENTER);
		label_cname.setBounds(292, 105, 61, 16);
		contentPane.add(label_cname);
		
		JLabel label_oquantity = new JLabel();
		label_oquantity.setHorizontalAlignment(SwingConstants.CENTER);
		label_oquantity.setBounds(292, 144, 61, 16);
		contentPane.add(label_oquantity);
		
		JLabel label_numoprice = new JLabel();
		label_numoprice.setHorizontalAlignment(SwingConstants.CENTER);
		label_numoprice.setBounds(292, 172, 61, 16);
		contentPane.add(label_numoprice);
		
		JLabel lblNewLabel_status = new JLabel();
		lblNewLabel_status.setBounds(292, 200, 61, 16);
		contentPane.add(lblNewLabel_status);
		
		JButton button_out = new JButton("确定发货");
		
		button_out.setBounds(221, 242, 96, 29);
		contentPane.add(button_out);
		button_out.setVisible(false);
		
		JButton button_cancel = new JButton("取消订单");
		
		button_cancel.setBounds(348, 242, 96, 29);
		contentPane.add(button_cancel);
		button_cancel.setVisible(false);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(172, 271, 278, 12);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(169, 0, 12, 280);
		contentPane.add(separator_1);
		
		
		JButton button_unfilled = new JButton("查看未发货订单");
		
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"查看订单"}));
		comboBox.setBounds(6, 230, 169, 27);
		
		contentPane.add(comboBox);
		JButton button_undelivery = new JButton("查看所有的订单");
		button_undelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=1;i<comboBox.getItemCount();)
				{
				comboBox.removeItemAt(i);
				}
				//combobox 查看所有订单信息
				try {
					for(int i=0;i<PetsysUtil.ordManager.loadAllOrd().size();i++)
					{
						comboBox.addItem("编号->"+PetsysUtil.ordManager.loadAllOrd().get(i).getOid());
						
					}
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					// TODO: handle exception
				}
			}
		});
		button_undelivery.setBounds(26, 0, 117, 29);
		contentPane.add(button_undelivery);
		button_unfilled.setBounds(26, 39, 117, 29);
		contentPane.add(button_unfilled);
		
		button_unfilled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=1;i<comboBox.getItemCount();)
				{
				comboBox.removeItemAt(i);
				}
				//combobox 查看未审批的订单信息
				try {
					for(int i=0;i<PetsysUtil.ordManager.loadUnfilledOrd().size();i++)
					{
						comboBox.addItem("编号->"+PetsysUtil.ordManager.loadUnfilledOrd().get(i).getOid());
					}
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					// TODO: handle exception
				}
			}
		});
		
		JButton button_confirm = new JButton("查看已发货订单");
		button_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=1;i<comboBox.getItemCount();)
				{
				comboBox.removeItemAt(i);
				}
				//combobox 查看已发货订单信息
				try {
					for(int i=0;i<PetsysUtil.ordManager.loadOrdwithStatus("已发货").size();i++)
					{
						comboBox.addItem("编号->"+PetsysUtil.ordManager.loadOrdwithStatus("已发货").get(i).getOid());
						
					}
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					// TODO: handle exception
				}
			}
		});
		button_confirm.setBounds(26, 80, 117, 29);
		contentPane.add(button_confirm);
		
		JButton button_canceled = new JButton("查看已取消订单");
		button_canceled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=1;i<comboBox.getItemCount();)
				{
				comboBox.removeItemAt(i);
				}
				//combobox 查看已发货订单信息
				try {
					for(int i=0;i<PetsysUtil.ordManager.loadOrdwithStatus("商家取消").size();i++)
					{
						comboBox.addItem("编号->"+PetsysUtil.ordManager.loadOrdwithStatus("商家取消").get(i).getOid());
						
					}
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					// TODO: handle exception
				}
			}
		});
		button_canceled.setBounds(26, 159, 117, 29);
		contentPane.add(button_canceled);
		
		JButton button_received = new JButton("查看已收货订单");
		button_received.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=1;i<comboBox.getItemCount();)
				{
				comboBox.removeItemAt(i);
				}
				//combobox 查看已发货订单信息
				try {
					for(int i=0;i<PetsysUtil.ordManager.loadOrdwithStatus("已收货").size();i++)
					{
						comboBox.addItem("编号->"+PetsysUtil.ordManager.loadOrdwithStatus("已收货").get(i).getOid());
						
					}
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					// TODO: handle exception
				}
			}
		});
		button_received.setBounds(26, 121, 117, 29);
		contentPane.add(button_received);
		
		JButton button_refund = new JButton("查看已退款订单");
		button_refund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=1;i<comboBox.getItemCount();)
				{
				comboBox.removeItemAt(i);
				}
				//combobox 查看已发货订单信息
				try {
					for(int i=0;i<PetsysUtil.ordManager.loadOrdwithStatus("用户取消").size();i++)
					{
						comboBox.addItem("编号->"+PetsysUtil.ordManager.loadOrdwithStatus("用户取消").get(i).getOid());
						
					}
				} catch (BaseException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
					// TODO: handle exception
				}
			}
		});
		button_refund.setBounds(26, 196, 117, 29);
		contentPane.add(button_refund);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					//可得到空白信息或oid订单编号
					text = comboBox.getSelectedItem().toString().substring(4);
					//若取得订单编号
					if(!text.equals(""))
					{
						oid = Integer.parseInt(text);
						try {
							BeanOrd bo = PetsysUtil.ordManager.loadOneOrd(oid);
							label_oid.setText(Integer.toString(bo.getOid()));
							label_cname.setText(PetsysUtil.cusManager.getName(bo.getCid()));
							label_iname.setText(PetsysUtil.comManager.getCommodity(bo.getIid()).getIname());
							label_numoprice.setText(Double.toString(bo.getOprice()));
							label_oquantity.setText(Integer.toString(bo.getOquantity()));
							lblNewLabel_status.setText(bo.getOstatus());
						} catch (BaseException e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
							return;
							// TODO: handle exception
						}
					}
					//若取不到订单编号
					else 
					{
						oid = 0;
						label_oid.setText("");
						label_cname.setText("");
						label_iname.setText("");
						label_numoprice.setText("");
						label_oquantity.setText("");
						lblNewLabel_status.setText("");
					}
					//判断「确认发货和取消订单button」的出现时机
					if(oid!=0)
					{
					try {
						if(PetsysUtil.ordManager.loadOneOrd(oid).getOstatus().equals("审核"))
						{
							button_out.setVisible(true);
							button_cancel.setVisible(true);
						}
						//
						if(PetsysUtil.ordManager.loadOneOrd(oid).getOstatus().equals("商家取消"))
						{
							//已经取消的 什么也不能做
							button_out.setVisible(false);
							button_cancel.setVisible(false);
						}
						//
						if(PetsysUtil.ordManager.loadOneOrd(oid).getOstatus().equals("已收货"))
						{
							//已经收货的 既不能取消也不能发货
							button_out.setVisible(false);
							button_cancel.setVisible(false);
						}
						//
						if(PetsysUtil.ordManager.loadOneOrd(oid).getOstatus().equals("已发货"))
						{
							//已经发货的可以取消
							button_cancel.setVisible(true);
							button_out.setVisible(false);
						}
						if(PetsysUtil.ordManager.loadOneOrd(oid).getOstatus().equals("用户取消"))
						{
							//已经发货的可以取消
							button_cancel.setVisible(false);
							button_out.setVisible(false);
						}
					} catch (BaseException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
					}
					//空白text也触发隐藏
					else if(oid==0)
					{
						button_out.setVisible(false);
						button_cancel.setVisible(false);
					}
				}
			}
		});
		button_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						button_out.setVisible(false);
						button_cancel.setVisible(false);
						PetsysUtil.ordManager.sendOrd(oid);
				} catch (BaseException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		
		button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					button_cancel.setVisible(false);
					button_out.setVisible(false);
					PetsysUtil.ordManager.cancelOrd(oid);
					//恢复库存
					PetsysUtil.ordManager.undoOrd(PetsysUtil.ordManager.loadOneOrd(oid).getIid(), PetsysUtil.ordManager.loadOneOrd(oid).getOquantity());
				} catch (BaseException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		
		
		
		
		
		
		
	}
}
