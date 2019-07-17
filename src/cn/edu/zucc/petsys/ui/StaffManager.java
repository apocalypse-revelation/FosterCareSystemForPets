package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.zucc.petsys.bean.BeanStaff;
import cn.edu.zucc.petsys.function.ServiceManager;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffManager extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffManager frame = new StaffManager();
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
	public StaffManager() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("用户管理");
		menuBar.add(menu);
		
		JMenuItem menuItem_15 = new JMenuItem("查看用户");
		menuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchCustomer sc = new SearchCustomer();
				sc.setVisible(true);
			}
		});
		menu.add(menuItem_15);
		
		JMenuItem menuItem = new JMenuItem("删除用户");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffManageCustomer smc = new StaffManageCustomer();
				smc.setVisible(true);
			}
		});
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("宠物管理");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("宠物查看");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffManagePet smp = new StaffManagePet();
				smp.setVisible(true);
			}
		});
		menu_1.add(menuItem_1);
		
		JMenu menu_3 = new JMenu("商品管理");
		menuBar.add(menu_3);
		
		JMenu menu_4 = new JMenu("添加");
		menu_3.add(menu_4);
		
		JMenuItem menuItem_3 = new JMenuItem("类别");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AddType addtype = new AddType();
			addtype.setVisible(true);
			}
		});
		menu_4.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("商品");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCommodity addcom = new AddCommodity();
				addcom.setVisible(true);
			}
		});
		menu_4.add(menuItem_4);
		
		JMenu menu_5 = new JMenu("更新");
		menu_3.add(menu_5);
		
		JMenuItem menuItem_5 = new JMenuItem("类别");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateType ut = new UpdateType();
				ut.setVisible(true);
			}
		});
		menu_5.add(menuItem_5);
		
		JMenuItem menuItem_8 = new JMenuItem("商品");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateCommodity uc = new UpdateCommodity();
				uc.setVisible(true);
			}
		});
		menu_5.add(menuItem_8);
		
		JMenu menu_6 = new JMenu("删除");
		menu_3.add(menu_6);
		
		JMenuItem menuItem_6 = new JMenuItem("类别");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteType dt = new DeleteType();
				dt.setVisible(true);
			}
		});
		menu_6.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("商品");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteCommodity dc = new DeleteCommodity();
				dc.setVisible(true);
			}
		});
		menu_6.add(menuItem_7);
		
		JMenu menu_8 = new JMenu("服务预约");
		menuBar.add(menu_8);
		
		JMenuItem menuItem_10 = new JMenuItem("添加服务");
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddService as = new AddService();
				as.setVisible(true);
			}
		});
		menu_8.add(menuItem_10);
		
		JMenuItem menuItem_11 = new JMenuItem("更改服务");
		menuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateService us = new UpdateService();
				us.setVisible(true);
			}
		});
		menu_8.add(menuItem_11);
		
		JMenuItem menuItem_12 = new JMenuItem("停办服务");
		menuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffStopService sss = new StaffStopService();
				sss.setVisible(true);
			}
		});
		menu_8.add(menuItem_12);
		
		JMenuItem menuItem_13 = new JMenuItem("重办服务");
		menuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BootUpService bus = new BootUpService();
				bus.setVisible(true);
			}
		});
		menu_8.add(menuItem_13);
		
		JMenuItem menuItem_9 = new JMenuItem("预约申请处理");
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffManageReservation smr = new StaffManageReservation();
				smr.setVisible(true);
				
			}
		});
		menu_8.add(menuItem_9);
		
		JMenuItem menuItem_14 = new JMenuItem("预约进度设置");
		menuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffDisposeRes sdr = new StaffDisposeRes();
				sdr.setVisible(true);
			}
		});
		menu_8.add(menuItem_14);
		
		JMenuItem menuItem_17 = new JMenuItem("删除预约");
		menuItem_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelReservation dr = new DelReservation();
				dr.setVisible(true);
			}
		});
		menu_8.add(menuItem_17);
		
		JMenu menu_2 = new JMenu("订单管理");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_2 = new JMenuItem("处理订单");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffManageOrder smo = new StaffManageOrder();
				smo.setVisible(true);
			}
		});
		menu_2.add(menuItem_2);
		
		JMenuItem menuItem_16 = new JMenuItem("删除订单");
		menuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelOrder dor = new DelOrder();
				dor.setVisible(true);
			}
		});
		menu_2.add(menuItem_16);
		
		JMenu menu_7 = new JMenu("系统管理");
		menuBar.add(menu_7);
		
		JMenuItem mntmRoot = new JMenuItem("Root管理");
		mntmRoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UltimateAdmin ua = new UltimateAdmin();
				ua.setVisible(true);
			}
		});
		menu_7.add(mntmRoot);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		if(!BeanStaff.currentLoginStaff.getSrank().equals("超级管理")) {
			mntmRoot.setVisible(false);
		}
	}
	
}
