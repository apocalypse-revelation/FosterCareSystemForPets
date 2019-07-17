package cn.edu.zucc.petsys.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerInterface extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerInterface frame = new CustomerInterface();
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
	public CustomerInterface() {
		setTitle("口袋妖精用户自助界面");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 270, 172);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("个人服务");
		mnNewMenu.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon8.jpeg"));
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem_2 = new JMenuItem("购买商品");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerPurchaseInterface purchaseif = new CustomerPurchaseInterface();
				purchaseif.setVisible(true);
			}
		});
		menuItem_2.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon7.jpeg"));
		mnNewMenu.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("管理订单");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerManageOrder cmo = new CustomerManageOrder();
				cmo.setVisible(true);
			}
		});
		menuItem_3.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon7.jpeg"));
		mnNewMenu.add(menuItem_3);
		
		JMenu menu = new JMenu("宠物服务");
		menu.setHorizontalAlignment(SwingConstants.CENTER);
		menu.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon6.jpeg"));
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("预约与注销");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PetsChoice petschoice = new PetsChoice();
				petschoice.setVisible(true);
			}
		});
		
		JMenuItem menuItem_1 = new JMenuItem("登记宠物");
		menu.add(menuItem_1);
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPet add = new AddPet();
				add.setVisible(true);
			}
		});
		menuItem_1.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon7.jpeg"));
		menuItem.setIcon(new ImageIcon("/Users/cw/Downloads/db/image/icon7.jpeg"));
		menu.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
