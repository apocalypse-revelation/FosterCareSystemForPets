package cn.edu.zucc.booklib.LayoutDemo;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutDemo extends JFrame {
	public BorderLayoutDemo() { // ���캯������ʼ������ֵ
		// ����Ϊ�߽粼�֣����������������Ϊ5����
		setLayout(new BorderLayout(5, 5));
		setFont(new Font("Helvetica", Font.PLAIN, 14));
		getContentPane().add("North", new JButton("North")); // ����ť��ӵ�������
		getContentPane().add("South", new JButton("South"));
		getContentPane().add("East", new JButton("East"));
		getContentPane().add("West", new JButton("West"));
		getContentPane().add("Center", new JButton("Center"));
	}

	public static void main(String args[]) {
		BorderLayoutDemo f = new BorderLayoutDemo();
		f.setTitle("�߽粼��");
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null); // �ô��������ʾ
	}
}