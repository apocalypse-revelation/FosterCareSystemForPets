package cn.edu.zucc.booklib.LayoutDemo;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutDemo1 extends JFrame {
	JPanel p = new JPanel();

	public BorderLayoutDemo1() {
		setLayout(new BorderLayout(5, 5));
		setFont(new Font("Helvetica", Font.PLAIN, 14));
		getContentPane().add("North", new JButton("North"));
		getContentPane().add("South", new JButton("South"));
		getContentPane().add("East", new JButton("East"));
		getContentPane().add("West", new JButton("West"));
		// �������Ϊ��ʽ���־�����ʾ������ᡢ�ݼ��Ϊ5������
		p.setLayout(new FlowLayout(1, 5, 5));
		// ʹ��ѭ����Ӱ�ť��ע��ÿ����ӵİ�ť�������ƶ���b
		// ����ťÿ�ξ�����new�����ɵģ����д���ͬ�İ�ť����
		for (int i = 1; i < 10; i++) {
			// String.valueOf(i)��������ת��Ϊ�ַ���
			JButton b = new JButton(String.valueOf(i));
			p.add(b); // ����ť��ӵ������
		}
		getContentPane().add("Center", p); // �������ӵ��м�λ��
	}

	public static void main(String args[]) {
		BorderLayoutDemo1 f = new BorderLayoutDemo1();
		f.setTitle("�߽粼��");
		f.pack(); // �ô�������Ӧ�齨��С
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null); // �ô��������ʾ
	}
}
