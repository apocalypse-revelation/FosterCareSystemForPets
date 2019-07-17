package cn.edu.zucc.booklib.LayoutDemo;

import javax.swing.*;
import java.awt.*;

public class FlowLayoutDemo extends JFrame {
	public FlowLayoutDemo() {
		// ���ô���Ϊ��ʽ���֣��޲���Ĭ��Ϊ���ж���
		setLayout(new FlowLayout());
		// ���ô�������ʾ��������ʽ
		setFont(new Font("Helvetica", Font.PLAIN, 14));
		// ����ť��ӵ�������
		getContentPane().add(new JButton("Button 1"));
		getContentPane().add(new JButton("Button 2"));
		getContentPane().add(new JButton("Button 3"));
		getContentPane().add(new JButton("Button 4"));
	}

	public static void main(String args[]) {
		FlowLayoutDemo window = new FlowLayoutDemo();
		window.setTitle("��ʽ����");
		// �ô������ݷ��õ�����趨���ڵĴ�Сʹ֮��������������õ��������
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null); // �ô��������ʾ
	}
}
