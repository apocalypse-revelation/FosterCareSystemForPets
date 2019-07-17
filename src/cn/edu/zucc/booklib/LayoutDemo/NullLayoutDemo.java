package cn.edu.zucc.booklib.LayoutDemo;

import java.awt.*;

import javax.swing.*;

public class NullLayoutDemo {
	JFrame fr;
	JButton a, b;
	NullLayoutDemo() {
		fr = new JFrame();
		fr.setBounds(100, 100, 250, 150);

		// ���ô���Ϊ�ղ���
		fr.setLayout(null);
		a = new JButton("��ťa");
		b = new JButton("��ťb");
		fr.getContentPane().add(a);

		// ���ð�ťa�ľ�ȷλ��
		a.setBounds(30, 30, 80, 25);
		fr.getContentPane().add(b);
		b.setBounds(150, 40, 80, 25);
		fr.setTitle("NullLayoutDemo");
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setLocationRelativeTo(null); // �ô��������ʾ
	}

	public static void main(String args[]) {
		new NullLayoutDemo();
	}

}